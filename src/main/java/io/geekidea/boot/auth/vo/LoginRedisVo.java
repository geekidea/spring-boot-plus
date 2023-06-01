package io.geekidea.boot.auth.vo;

import lombok.Data;

import java.util.List;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Data
public class LoginRedisVo {

    /**
     * 登录用户对象
     */
    private LoginVo loginVo;

    /**
     * 当前登录用户对应的后台方法权限集合
     */
    private List<String> permissions;

}
