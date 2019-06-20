/**
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

package io.geekidea.springbootplus.common.web.interceptor;

import io.geekidea.springbootplus.common.constant.CommonConstant;
import io.geekidea.springbootplus.util.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * Token超时拦截器
 * 
 * @author geekidea
 * @date 2018-11-08
 */
@Slf4j
public class TokenTimeoutInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 如果访问的不是控制器,则跳出,继续执行下一个拦截器
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		// 获取token
		String token = request.getHeader(CommonConstant.TOKEN);
		boolean hastoken = false;
		if (StringUtils.isNotBlank(token)){
			hastoken = true;
		}
		if (hastoken == false){
			token = request.getParameter(CommonConstant.TOKEN);
			if (StringUtils.isNotBlank(token)){
				hastoken = true;
			}
		}

		// TODO 排除自动刷新的路径
		if (hastoken == true){
			// 如果有token,延长时间
			redisTemplate.expire(token,LoginUtil.TOKEN_VALID_TIME_MINUTE, TimeUnit.MINUTES);
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}
}