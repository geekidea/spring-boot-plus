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

package io.geekidea.springbootplus.common.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.geekidea.springbootplus.common.api.ApiCode;
import io.geekidea.springbootplus.common.api.ApiResult;
import io.geekidea.springbootplus.shiro.util.JwtTokenUtil;
import io.geekidea.springbootplus.util.AnsiUtil;
import io.geekidea.springbootplus.util.DateUtil;
import io.geekidea.springbootplus.util.IpUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.fusesource.jansi.Ansi;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * Controller Aop
 * 获取响应结果信息
 * </p>
 *
 * @author geekidea
 * @date 2018-11-08
 */
@Data
@Slf4j
@Aspect
public class LogAop {

    /**
     * 切点
     */
    private static final String POINTCUT = "execution(public * io.geekidea.springbootplus.*.web.controller..*.*(..))";
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
     * 请求日志是否格式化输出
     */
    private boolean requestLogFormat;

    /**
     * 响应日期是否格式化输出
     */
    private boolean responseLogFormat;

    @Around(POINTCUT)
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        // 获取请求相关信息
        try {
            // 获取当前的HttpServletRequest对象
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();


            Map<String,Object> map = new LinkedHashMap<>();

            // 获取请求类名和方法名称
            Signature signature = joinPoint.getSignature();

            // 获取真实的方法对象
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();

            // 请求全路径
            String url = request.getRequestURI();
            map.put("path",url);
            // IP地址
            String ip = IpUtil.getRequestIp();
            map.put("ip",ip);

            // 获取请求方式
            String requestMethod = request.getMethod();
            map.put("requestMethod",requestMethod);

            // 获取请求内容类型
            String contentType = request.getContentType();
            map.put("contentType",contentType);

            // 判断控制器方法参数中是否有RequestBody注解
            Annotation[][] annotations = method.getParameterAnnotations();
            boolean isRequestBody = isRequestBody(annotations);
            map.put("isRequestBody",isRequestBody);
            // 设置请求参数
            Object requestParamJson = getRequestParamJsonString(joinPoint, request, requestMethod, contentType, isRequestBody);
            map.put("param",requestParamJson);
            map.put("time", DateUtil.getYYYYMMDDHHMMSS(new Date()));

            // 获取请求头token
            map.put("token",request.getHeader(JwtTokenUtil.getTokenName()));

            String requestInfo = null;
            try {
                if (requestLogFormat){
                    requestInfo = "\n" + JSON.toJSONString(map,true);
                }else{
                    requestInfo = JSON.toJSONString(map);
                }
            } catch (Exception e) {

            }
            log.info(AnsiUtil.getAnsi(Ansi.Color.GREEN,"requestInfo:" + requestInfo));

        } catch (Exception e) {
            e.printStackTrace();
        }



        // 执行目标方法,获得返回值
        Object result = joinPoint.proceed();
        try{
            if (result != null && result instanceof ApiResult){
                ApiResult apiResult = (ApiResult) result;
                int code = apiResult.getCode();
                String responseResultInfo = "responseResult:";
                if (responseLogFormat){
                    responseResultInfo += "\n" + JSON.toJSONString(apiResult,true);
                }else{
                    responseResultInfo += JSON.toJSONString(apiResult);
                }
                if (code == ApiCode.SUCCESS.getCode()){
                    log.info(AnsiUtil.getAnsi(Ansi.Color.BLUE,responseResultInfo));
                }else{
                    log.error(AnsiUtil.getAnsi(Ansi.Color.RED,responseResultInfo));
                }

            }
        }catch (Exception e){
            log.error("处理响应结果异常",e);
        }
        return result;
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
    private Object getRequestParamJsonString(ProceedingJoinPoint joinPoint, HttpServletRequest request, String requestMethod, String contentType, boolean isRequestBody) {
        /**
         * 判断请求内容类型
         * 通常有3中请求内容类型
         * 1.发送get请求时,contentType为null
         * 2.发送post请求时,contentType为application/x-www-form-urlencoded
         * 3.发送post json请求,contentType为application/json
         * 4.发送post json请求并有RequestBody注解,contentType为application/json
         */
        Object paramObject = null;
        int requestType = 0;
        if (GET.equals(requestMethod)) {
            requestType = 1;
        } else if (POST.equals(requestMethod)) {
            if (contentType == null) {
                requestType = 5;
            } else if (contentType.startsWith(APPLICATION_X_WWW_FORM_URLENCODED)) {
                requestType = 2;
            } else if (contentType.startsWith(APPLICATION_JSON)) {
                if (isRequestBody) {
                    requestType = 4;
                } else {
                    requestType = 3;
                }
            }
        }

        // 1,2,3中类型时,获取getParameterMap中所有的值,处理后序列化成JSON字符串
        if (requestType == 1 || requestType == 2 || requestType == 3 || requestType == 5) {
            Map<String, String[]> paramsMap = request.getParameterMap();
            paramObject = getJsonForParamMap(paramsMap);
        } else if (requestType == 4) { // POST,application/json,RequestBody的类型,简单判断,然后序列化成JSON字符串
            Object[] args = joinPoint.getArgs();
            paramObject = argsArrayToJsonString(args);
        }

        return paramObject;
    }

    /**
     * 判断控制器方法参数中是否有RequestBody注解
     *
     * @param annotations
     * @return
     */
    private boolean isRequestBody(Annotation[][] annotations) {
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
    private Object argsArrayToJsonString(Object[] args) {
        if (args == null) {
            return null;
        }
        if (args.length == 1) {
            return args[0];
        } else {
            return args;
        }
    }


    /**
     * 获取参数Map的JSON字符串
     *
     * @param paramsMap
     * @return
     */
    public JSONObject getJsonForParamMap(Map<String, String[]> paramsMap) {
        int paramSize = paramsMap.size();
        if (paramsMap == null || paramSize == 0) {
            return null;
        }
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, String[]> kv : paramsMap.entrySet()) {
            String key = kv.getKey();
            String[] values = kv.getValue();
            if (values == null) { // 没有值
                jsonObject.put(key, null);
            } else if (values.length == 1) { // 一个值
                jsonObject.put(key, values[0]);
            } else { // 多个值
                jsonObject.put(key, values);
            }
        }
        return jsonObject;
    }


}
