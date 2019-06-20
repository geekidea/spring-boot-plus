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

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器
 * 
 * @author geekidea
 * @since 2018-11-08
 */
@Slf4j
public class PermissionInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

//		// 如果访问的不是控制器,则跳出,继续执行下一个拦截器
//		if (!(handler instanceof HandlerMethod)) {
//			return true;
//		}
//
//		// 获取token
//		String token = request.getHeader(CommonConstant.TOKEN);
//		boolean hastoken = false;
//		if (StringUtils.isNotBlank(token)){
//			hastoken = true;
//		}
//		if (hastoken == false){
//			token = request.getParameter(CommonConstant.TOKEN);
//			if (StringUtils.isNotBlank(token)){
//				hastoken = true;
//			}
//		}
//		if (hastoken == false){
//			// 请传入token
//			ApiResult apiResult = new ApiResult();
//			apiResult.setCode(ResponseCode.NOT_PERMISSION);
//			apiResult.setMsg("请传入有效的token");
//			HttpServletResponseUtil.printJSON(response,apiResult);
//			return false;
//		}
//
//		// 判断是否登陆
////		LoginSysUserVo loginSysUserVo = (LoginSysUserVo) redisTemplate.opsForValue().get(token);
////		if (loginSysUserVo == null){
////			ResponseResult responseResult = new ResponseResult();
////			responseResult.setCode(ResponseCode.NOT_LOGIN);
////			responseResult.setMsg("token无效或已过期,请重新登录");
////			HttpServletResponseUtil.printJSON(response,responseResult);
////			return false;
////		}
//
//		// 判断是否有权限
////		String path = request.getServletPath();
//		// 获取到权限路径
//		// TODO 需判断第一次初始化信息
////		List<String> allFuncActions = loginSysUserVo.getAllFuncActions();
////		if (CollectionUtils.isEmpty(allFuncActions)){
////			HttpServletResponseUtil.printJSON(response,new ResponseResult(ResponseCode.NOT_PERMISSION,"该用户没有任何权限功能"));
////			return false;
////		}
//
////		// 排除不需要权限验证的路径
////		if (isExistsPath(path)){
////			// 将当前登录用户保存到request中
////			request.setAttribute(CommonConstant.LOGIN_SYS_USER, loginSysUserVo);
////			return true;
////		}
////
////		// 验证是否有操作权限
////		if (!allFuncActions.contains(path)){
////			HttpServletResponseUtil.printJSON(response,new ResponseResult(ResponseCode.NOT_PERMISSION,"该用户没有此操作权限"));
////			return false;
////		}
//
//		// 将当前登录用户保存到request中
////		request.setAttribute(CommonConstant.LOGIN_SYS_USER, loginSysUserVo);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

//	public boolean isExistsPath(String path){
//		if (functionExcludePaths == null || functionExcludePaths.length == 0){
//			return false;
//		}
//		for (String functionExcludePath : functionExcludePaths){
//			if (path.startsWith(functionExcludePath)){
//				return true;
//			}
//		}
//		return false;
//	}
}