/**
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.security.interceptor;

import io.geekidea.springbootplus.common.api.ApiCode;
import io.geekidea.springbootplus.common.api.ApiResult;
import io.geekidea.springbootplus.security.service.LoginService;
import io.geekidea.springbootplus.security.util.JwtUtil;
import io.geekidea.springbootplus.util.HttpServletResponseUtil;
import io.geekidea.springbootplus.util.LoginUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  判断jwt token是否有效
 * </p>
 * @author geekidea
 * @date 2019-05-22
 **/
@Slf4j
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果访问的不是控制器,则跳出,继续执行下一个拦截器
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 获取token
        String token = LoginUtil.getToken(request);

        // 如果token为空，则s输出提示并返回
        if (StringUtils.isBlank(token)){
            ApiResult apiResult = ApiResult.result(ApiCode.UNAUTHORIZED);
            log.error("token is empty");
            HttpServletResponseUtil.printJSON(response,apiResult);
            return false;
        }

        log.debug("token:{}",token);

        // 验证token是否有效
        Jws<Claims> jws = JwtUtil.verify(token);
        log.debug("token verify:{}",jws);
        if (jws == null){
            ApiResult apiResult = ApiResult.result(ApiCode.UNAUTHORIZED);
            log.error("token verify failed");
            HttpServletResponseUtil.printJSON(response,apiResult);
            return false;
        }

        // 刷新token
        loginService.refreshToken(response, jws);

        // 存储jws对象到request对象中
        request.setAttribute("jws",jws);

        log.debug("token verify success");
        return true;
    }

}
