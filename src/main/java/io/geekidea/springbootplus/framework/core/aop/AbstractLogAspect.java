/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.framework.core.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.geekidea.springbootplus.framework.common.api.ApiCode;
import io.geekidea.springbootplus.framework.common.api.ApiResult;
import io.geekidea.springbootplus.framework.core.properties.SpringBootPlusAopProperties;
import io.geekidea.springbootplus.framework.shiro.util.JwtTokenUtil;
import io.geekidea.springbootplus.framework.util.AnsiUtil;
import io.geekidea.springbootplus.framework.util.DateUtil;
import io.geekidea.springbootplus.framework.util.IpUtil;
import io.geekidea.springbootplus.framework.util.Jackson;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.fusesource.jansi.Ansi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <p>
 * Controller Aop 抽象类
 * 获取响应结果信息
 * <p>
 * 日志输出类型：print-type
 * 1. 请求和响应分开，按照执行顺序打印
 * 2. ThreadLocal线程绑定，方法执行结束时，连续打印请求和响应日志
 * 3. ThreadLocal线程绑定，方法执行结束时，同时打印请求和响应日志
 * </p>
 *
 * @author geekidea
 * @date 2018-11-08
 */
@Data
@Slf4j
public abstract class AbstractLogAspect {

    /**
     * 本地线程变量，保存请求参数信息到当前线程中
     */
    protected static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 默认的请求内容类型,表单提交
     **/
    private static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";
    /**
     * JSON请求内容类型
     **/
    private static final String APPLICATION_JSON = "application/json";
    /**
     * GET请求
     **/
    private static final String GET = "GET";
    /**
     * POST请求
     **/
    private static final String POST = "POST";

    /**
     * AOP配置
     */
    protected SpringBootPlusAopProperties.LogAopConfig logAopConfig;

    @Autowired
    public void setSpringBootPlusAopProperties(SpringBootPlusAopProperties springBootPlusAopProperties) {
        logAopConfig = springBootPlusAopProperties.getLog();
    }

    /**
     * 环绕通知
     * 方法执行前打印请求参数信息
     * 方法执行后打印响应结果信息
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    public abstract Object doAround(ProceedingJoinPoint joinPoint) throws Throwable;

    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取请求相关信息
        try {
            // 获取当前的HttpServletRequest对象
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();


            Map<String, Object> map = new LinkedHashMap<>();

            // 获取请求类名和方法名称
            Signature signature = joinPoint.getSignature();

            // 获取真实的方法对象
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();

            // 请求全路径
            String url = request.getRequestURI();
            map.put("path", url);
            // IP地址
            String ip = IpUtil.getRequestIp();
            map.put("ip", ip);

            // 获取请求方式
            String requestMethod = request.getMethod();
            map.put("requestMethod", requestMethod);

            // 获取请求内容类型
            String contentType = request.getContentType();
            map.put("contentType", contentType);

            // 判断控制器方法参数中是否有RequestBody注解
            Annotation[][] annotations = method.getParameterAnnotations();
            boolean isRequestBody = isRequestBody(annotations);
            map.put("isRequestBody", isRequestBody);

            AnnotatedType[] annotatedTypes = method.getAnnotatedParameterTypes();

            // 获取Shiro注解值，并记录到map中
            handleShiroAnnotationValue(map, method);

            // 设置请求参数
            Object requestParamJson = getRequestParamJsonString(joinPoint, request, requestMethod, contentType, isRequestBody);
            map.put("param", requestParamJson);
            map.put("time", DateUtil.getDateTimeString(new Date()));

            // 获取请求头token
            map.put("token", request.getHeader(JwtTokenUtil.getTokenName()));

            // 处理请求参数
            handleRequestInfo(map);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 执行目标方法,获得返回值
        Object result = joinPoint.proceed();
        try {
            handleResponseInfo(result);
        } catch (Exception e) {
            log.error("处理响应结果异常", e);
        }
        return result;
    }

    /**
     * 获取Shiro注解值，并记录到map中
     *
     * @param map
     * @param method
     */
    protected void handleShiroAnnotationValue(Map<String, Object> map, Method method) {
        RequiresRoles requiresRoles = method.getAnnotation(RequiresRoles.class);
        if (requiresRoles != null) {
            String[] requiresRolesValues = requiresRoles.value();
            if (ArrayUtils.isNotEmpty(requiresRolesValues)) {
                String requiresRolesString = Arrays.toString(requiresRolesValues);
                map.put("requiresRoles", requiresRolesString);
            }
        }

        RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
        if (requiresPermissions != null) {
            String[] requiresPermissionsValues = requiresPermissions.value();
            if (ArrayUtils.isNotEmpty(requiresPermissionsValues)) {
                String requiresPermissionsString = Arrays.toString(requiresPermissionsValues);
                map.put("requiresPermissions", requiresPermissionsString);
            }
        }

        RequiresAuthentication requiresAuthentication = method.getAnnotation(RequiresAuthentication.class);
        if (requiresAuthentication != null) {
            map.put("requiresAuthentication", true);
        }

        RequiresUser requiresUser = method.getAnnotation(RequiresUser.class);
        if (requiresUser != null) {
            map.put("requiresUser", true);
        }

        RequiresGuest requiresGuest = method.getAnnotation(RequiresGuest.class);
        if (requiresGuest != null) {
            map.put("requiresGuest", true);
        }

    }

    /**
     * 处理请求参数
     *
     * @param map
     */
    protected void handleRequestInfo(Map<String, Object> map) {
        // 获取请求信息
        String requestInfo = formatRequestInfo(map);
        // 如果打印方式为顺序打印，则直接打印，否则，保存的threadLocal中
        if (logAopConfig.getPrintType() == 1) {
            printRequestInfo(requestInfo);
        } else {
            threadLocal.set(requestInfo);
        }
    }

    /**
     * 处理响应结果
     *
     * @param result
     */
    protected void handleResponseInfo(Object result) {
        if (result != null && result instanceof ApiResult) {
            ApiResult apiResult = (ApiResult) result;
            int code = apiResult.getCode();
            // 获取格式化后的响应结果
            String responseResultInfo = formatResponseInfo(apiResult);
            if (logAopConfig.getPrintType() == SpringBootPlusAopProperties.LogAopConfig.PRINT_TYPE_ORDER) {
                printResponseInfo(code, responseResultInfo);
            } else {
                // 从threadLocal中获取线程请求信息
                String requestInfo = threadLocal.get();
                threadLocal.remove();
                // 如果是连续打印，则先打印请求参数，再打印响应结果
                if (logAopConfig.getPrintType() == SpringBootPlusAopProperties.LogAopConfig.PRINT_TYPE_CONTINUITY) {
                    printRequestInfo(requestInfo);
                    printResponseInfo(code, responseResultInfo);
                } else if (logAopConfig.getPrintType() == SpringBootPlusAopProperties.LogAopConfig.PRINT_TYPE_FINAL) {
                    printRequestResponseInfo(code, requestInfo, responseResultInfo);
                }
            }
        }
    }

    /**
     * 同时打印请求和响应信息
     *
     * @param code
     * @param requestInfo
     * @param responseResultInfo
     */
    protected void printRequestResponseInfo(int code, String requestInfo, String responseResultInfo) {
        if (code == ApiCode.SUCCESS.getCode()) {
            log.info(requestInfo + "\n" + responseResultInfo);
        } else {
            log.error(requestInfo + "\n" + responseResultInfo);
        }
    }


    /**
     * 格式化请求信息
     *
     * @param map
     * @return
     */
    protected String formatRequestInfo(Map<String, Object> map) {
        String requestInfo = null;
        try {
            if (logAopConfig.isRequestLogFormat()) {
                requestInfo = "\n" + JSON.toJSONString(map, true);
            } else {
                requestInfo = Jackson.toJsonString(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AnsiUtil.getAnsi(Ansi.Color.GREEN, "requestInfo:" + requestInfo);
    }

    /**
     * 打印请求信息
     *
     * @param requestInfo
     */
    protected void printRequestInfo(String requestInfo) {
        log.info(requestInfo);
    }

    /**
     * 格式化响应信息
     *
     * @param apiResult
     * @return
     */
    protected String formatResponseInfo(ApiResult apiResult) {
        String responseResultInfo = "responseResult:";
        try {
            if (logAopConfig.isResponseLogFormat()) {
                responseResultInfo += "\n" + Jackson.toJsonString(apiResult, true);
            } else {
                responseResultInfo += Jackson.toJsonString(apiResult);
            }
            int code = apiResult.getCode();
            if (code == ApiCode.SUCCESS.getCode()) {
                return AnsiUtil.getAnsi(Ansi.Color.BLUE, responseResultInfo);
            } else {
                return AnsiUtil.getAnsi(Ansi.Color.RED, responseResultInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseResultInfo;
    }

    /**
     * 打印响应信息
     *
     * @param code
     * @param responseResultInfo
     */
    protected void printResponseInfo(int code, String responseResultInfo) {
        if (code == ApiCode.SUCCESS.getCode()) {
            log.info(responseResultInfo);
        } else {
            log.error(responseResultInfo);
        }
    }

    /**
     * 获取请求参数JSON字符串
     *
     * @param joinPoint
     * @param request
     * @param requestMethod
     * @param contentType
     * @param isRequestBody
     */
    protected Object getRequestParamJsonString(ProceedingJoinPoint joinPoint, HttpServletRequest request, String requestMethod, String contentType, boolean isRequestBody) {
        Object paramObject = null;
        if (isRequestBody){
            // POST,application/json,RequestBody的类型,简单判断,然后序列化成JSON字符串
            Object[] args = joinPoint.getArgs();
            paramObject = argsArrayToJsonString(args);
        }else{
            // 获取getParameterMap中所有的值,处理后序列化成JSON字符串
            Map<String, String[]> paramsMap = request.getParameterMap();
            paramObject = getJsonForParamMap(paramsMap);
        }
        return paramObject;
    }

    /**
     * 判断控制器方法参数中是否有RequestBody注解
     *
     * @param annotations
     * @return
     */
    protected boolean isRequestBody(Annotation[][] annotations) {
        boolean isRequestBody = false;
        for (Annotation[] annotationArray : annotations) {
            for (Annotation annotation : annotationArray) {
                if (annotation instanceof RequestBody) {
                    isRequestBody = true;
                }
            }
        }
        return isRequestBody;
    }

    /**
     * 请求参数拼装
     *
     * @param args
     * @return
     */
    protected Object argsArrayToJsonString(Object[] args) {
        if (args == null) {
            return null;
        }
        // 去掉HttpServletRequest和HttpServletResponse
        List<Object> realArgs = new ArrayList<>();
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                continue;
            }
            if (arg instanceof HttpServletResponse) {
                continue;
            }
            if (arg instanceof MultipartFile) {
                continue;
            }
            if (arg instanceof ModelAndView) {
                continue;
            }
            realArgs.add(arg);
        }
        if (realArgs.size() == 1) {
            return realArgs.get(0);
        } else {
            return realArgs;
        }
    }


    /**
     * 获取参数Map的JSON字符串
     *
     * @param paramsMap
     * @return
     */
    protected JSONObject getJsonForParamMap(Map<String, String[]> paramsMap) {
        int paramSize = paramsMap.size();
        if (paramsMap == null || paramSize == 0) {
            return null;
        }
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, String[]> kv : paramsMap.entrySet()) {
            String key = kv.getKey();
            String[] values = kv.getValue();
            // 没有值
            if (values == null) {
                jsonObject.put(key, null);
            } else if (values.length == 1) {
                // 一个值
                jsonObject.put(key, values[0]);
            } else {
                // 多个值
                jsonObject.put(key, values);
            }
        }
        return jsonObject;
    }


}
