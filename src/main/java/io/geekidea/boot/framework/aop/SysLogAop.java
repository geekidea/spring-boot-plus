package io.geekidea.boot.framework.aop;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import io.geekidea.boot.auth.util.LoginUtil;
import io.geekidea.boot.config.properties.LogAopProperties;
import io.geekidea.boot.framework.annotation.Log;
import io.geekidea.boot.framework.constant.AspectConstant;
import io.geekidea.boot.framework.constant.CommonConstant;
import io.geekidea.boot.framework.exception.GlobalExceptionHandler;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.framework.util.HttpRequestUtil;
import io.geekidea.boot.framework.util.IpUtil;
import io.geekidea.boot.system.entity.SysLog;
import io.geekidea.boot.system.mapper.SysLogMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * 日志AOP
 *
 * @author geekidea
 * @date 2022/6/9
 **/
@Slf4j
@Data
@Aspect
@Component
@Order(1)
@EnableConfigurationProperties(LogAopProperties.class)
@ConditionalOnProperty(name = "log-aop.enable", havingValue = "true", matchIfMissing = true)
public class SysLogAop {

    private static final String LOG_ID = "logId";
    private static final String USERNAME = "username";
    private static final String REFERER = "Referer";
    private static final String USER_AGENT = "User-Agent";
    private static final String REQUEST_ORIGION = "Request-Origion";
    private static final String SWAGGER_UI = "swagger-ui";
    private static final String POSTMAN = "postman";
    /**
     * 本地线程变量，保存操作日志到当前线程中
     */
    private static final ThreadLocal<SysLog> LOCAL_LOG = new ThreadLocal<>();

    @Autowired
    private LogAopProperties logAopProperties;

    private List<String> excludeUrls;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SysLogMapper sysLogMapper;

    @Around(AspectConstant.COMMON_POINTCUT)
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 执行方法之前处理
        handleBefore(joinPoint);
        Object result;
        try {
            // 执行目标方法,获得返回值
            result = joinPoint.proceed();
            // 执行方法之后处理
            handleAfter(result);
        } catch (Throwable e) {
            // 执行方法异常处理
            handleException(e);
            throw e;
        } finally {
            // 执行方法结束后
            handleFinally();
        }
        return result;
    }

    /**
     * 执行方法之前
     *
     * @param joinPoint
     * @throws Exception
     */
    private void handleBefore(ProceedingJoinPoint joinPoint) throws Exception {
        try {
            HttpServletRequest request = HttpRequestUtil.getRequest();
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            // 系统日志
            SysLog sysLog = new SysLog();
            LOCAL_LOG.set(sysLog);
            String requestTime = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN);
            sysLog.setRequestTime(requestTime);
            Long logId = IdWorker.getId();
            // 设置日志链路ID
            MDC.put(LOG_ID, String.valueOf(logId));
            sysLog.setId(logId);
            String ip = IpUtil.getRequestIp();
            sysLog.setRequestIp(ip);
            String contextPath = request.getContextPath();
            // 请求全路径
            String requestUrl = request.getRequestURI();
            sysLog.setRequestUrl(requestUrl);
            if (StringUtils.isNotBlank(contextPath)) {
                requestUrl = requestUrl.substring(contextPath.length());
            }
            String contentType = request.getContentType();
            sysLog.setContentType(contentType);
            // 日志名称 获取ApiOperation的value
            Operation apiOperation = method.getAnnotation(Operation.class);
            String apiOperationName = null;
            if (apiOperation != null) {
                apiOperationName = apiOperation.summary();
                sysLog.setLogName(apiOperationName);
            }
            // 日志类型
            Log log = method.getAnnotation(Log.class);
            if (log != null) {
                Integer logType = log.type().getCode();
                sysLog.setLogType(logType);
            }
            // 请求方式，GET/POST
            sysLog.setRequestMethod(request.getMethod());
            // 判断是否是JSON参数请求
            Annotation[][] annotations = method.getParameterAnnotations();
            boolean isRequestBody = isRequestBody(annotations);
            // 是否是JSON请求映射参数
            sysLog.setIsRequestBody(isRequestBody);
            String requestBodyString = handleRequestParam(request, sysLog, isRequestBody);
            // 来源地址
            String referer = request.getHeader(REFERER);
            sysLog.setReferer(referer);
            // 用户环境
            String userAgent = request.getHeader(USER_AGENT);
            sysLog.setUserAgent(userAgent);
            // 用户来源
            String requestOrigion = request.getHeader(REQUEST_ORIGION);
            if (StringUtils.isBlank(requestOrigion)) {
                if (StringUtils.isNotBlank(referer) && referer.contains(SWAGGER_UI)) {
                    sysLog.setRequestSource(SWAGGER_UI);
                }
            } else {
                sysLog.setRequestSource(requestOrigion);
            }
            if (StringUtils.isNotBlank(userAgent)) {
                if (userAgent.toLowerCase().contains(POSTMAN)) {
                    sysLog.setRequestSource(POSTMAN);
                }
            }
            // 处理登录人信息
            handleLoginUser(sysLog, requestUrl, requestBodyString);
            // 打印请求头
            printHeadLog(request);
            // 打印请求日志
            printLog(sysLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 执行方法之后
     *
     * @param result
     */
    private void handleAfter(Object result) {
        try {
            if (result != null) {
                SysLog sysLog = LOCAL_LOG.get();
                ApiResult apiResult = (ApiResult) result;
                if (apiResult != null) {
                    boolean success = apiResult.isSuccess();
                    sysLog.setResponseSuccess(success);
                    sysLog.setResponseCode(apiResult.getCode());
                    sysLog.setResponseMessage(apiResult.getMsg());
                    Object responseData = apiResult.getData();
                    if (responseData != null) {
                        String responseDataString = JSON.toJSONString(responseData);
                        sysLog.setResponseData(responseDataString);
                    }
                }
                printLog("response：" + JSON.toJSONString(result));
            } else {
                printLog("response：");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 执行方法异常
     *
     * @param e
     */
    private void handleException(Throwable e) {
        try {
            ApiResult apiResult = GlobalExceptionHandler.handle(e);
            SysLog sysLog = LOCAL_LOG.get();
            sysLog.setResponseSuccess(false);
            sysLog.setResponseCode(apiResult.getCode());
            sysLog.setResponseMessage(apiResult.getMsg());
            String exceptionClassName = e.getClass().getSimpleName();
            sysLog.setExceptionName(exceptionClassName);
            String exceptionMessage = e.getMessage();
            sysLog.setExceptionMessage(exceptionMessage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 执行方法结束后处理
     */
    private void handleFinally() {
        try {
            SysLog sysLog = LOCAL_LOG.get();
            LOCAL_LOG.remove();
            String requestTime = sysLog.getRequestTime();
            Date requestDate = DateUtil.parse(requestTime, DatePattern.NORM_DATETIME_MS_PATTERN);
            long startTime = requestDate.getTime();
            Date responseDate = new Date();
            long nowTime = responseDate.getTime();
            long diffTime = nowTime - startTime;
            String responseTime = DateUtil.format(responseDate, DatePattern.NORM_DATETIME_MS_PATTERN);
            sysLog.setResponseTime(responseTime);
            sysLog.setDiffTime(diffTime);
            // 保存日志
            saveSysLog(sysLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String handleRequestParam(HttpServletRequest request, SysLog sysLog, boolean isRequestBody) {
        String requestBodyString = null;
        String parameterMapString = null;
        if (isRequestBody) {
            requestBodyString = (String) request.getAttribute(CommonConstant.REQUEST_PARAM_BODY_STRING);
        }
        Map<String, String[]> paramsMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(paramsMap)) {
            JSONObject paramObject = getJsonForParamMap(paramsMap);
            parameterMapString = JSON.toJSONString(paramObject);
        }
        String requestParam = null;
        if (StringUtils.isNotBlank(requestBodyString) && StringUtils.isNotBlank(parameterMapString)) {
            requestParam = "paramMap:" + parameterMapString + ",requestBody:" + requestBodyString;
        } else if (StringUtils.isNotBlank(requestBodyString)) {
            requestParam = requestBodyString;
        } else if (StringUtils.isNotBlank(parameterMapString)) {
            requestParam = parameterMapString;
        }
        // 请求参数
        sysLog.setRequestParam(requestParam);
        return requestBodyString;
    }

    /**
     * 打印请求头
     *
     * @param request
     */
    private void printHeadLog(HttpServletRequest request) {
        if (logAopProperties.isPrintHeadLog()) {
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headName = headerNames.nextElement();
                String headerValue = request.getHeader(headName);
                printLog(headName + "：" + headerValue);
            }
        }
    }

    /**
     * 处理登录人信息
     *
     * @param sysLog
     * @param requestUrl
     * @param requestBodyString
     * @throws Exception
     */
    private void handleLoginUser(SysLog sysLog, String requestUrl, String requestBodyString) throws Exception {
        try {
            String loginUrl = logAopProperties.getLoginUrl();
            if (requestUrl.equals(loginUrl)) {
                // 获取请求参数中的用户名称
                if (StringUtils.isNotBlank(requestBodyString)) {
                    JSONObject jsonObject = JSON.parseObject(requestBodyString);
                    String username = jsonObject.getString(USERNAME);
                    sysLog.setUsername(username);
                }
            } else {
                sysLog.setUserId(LoginUtil.getUserId());
                sysLog.setUsername(LoginUtil.getUsername());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步保存操作日志
     *
     * @param sysLog
     */
    @Async
    public void saveSysLog(SysLog sysLog) {
        try {
            if (sysLog != null) {
                Integer logType = sysLog.getLogType();
                if (logType != null) {
                    int result = sysLogMapper.insert(sysLog);
                    if (result != 1) {
                        log.error("保存操作日志错误" + sysLog);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印日志
     *
     * @param sysLog
     */
    private void printLog(SysLog sysLog) {
        if (logAopProperties.isPrintLog()) {
            if (sysLog != null) {
                String requestLog = sysLog.getRequestMethod() + "," + sysLog.getRequestUrl() + "," + sysLog.getContentType() + ",isRequestBody:" + sysLog.getIsRequestBody() + "," + sysLog.getRequestParam();
                printLog(requestLog);
            }
        }
    }

    /**
     * 打印日志
     *
     * @param msg
     */
    private void printLog(String msg) {
        if (logAopProperties.isPrintLog()) {
            log.info(msg);
        }
    }

    /**
     * 是否是json参数请求
     *
     * @param annotationArrays
     * @return
     */
    private boolean isRequestBody(Annotation[][] annotationArrays) {
        if (annotationArrays == null) {
            return false;
        }
        for (Annotation[] annotationArray : annotationArrays) {
            if (ArrayUtils.isNotEmpty(annotationArray)) {
                for (Annotation annotation : annotationArray) {
                    if (annotation instanceof RequestBody) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 转换表单参数为json对象
     *
     * @param paramsMap
     * @return
     */
    private JSONObject getJsonForParamMap(Map<String, String[]> paramsMap) {
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, String[]> kv : paramsMap.entrySet()) {
            String key = kv.getKey();
            String[] values = kv.getValue();
            if (values == null) {
                // 没有值
                jsonObject.put(key, null);
            } else if (values.length == 1) {
                // 一个值
                jsonObject.put(key, values[0]);
            } else { // 多个值
                jsonObject.put(key, values);
            }
        }
        return jsonObject;
    }

}
