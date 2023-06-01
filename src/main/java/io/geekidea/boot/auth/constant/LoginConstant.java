package io.geekidea.boot.auth.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author geekidea
 * @date 2022/7/12
 **/
public interface LoginConstant {

    /**
     * 默认的token名称
     */
    String TOKEN_NAME = "token";

    /**
     * 默认的token过期时间 30分钟
     */
    Integer DEFAULT_TOKEN_EXPIRE_MINUTES = 30;

    /**
     * 管理员角色编码
     */
    String ADMIN_ROLE_CODE = "admin";

    /**
     * 管理员admin角色ID
     */
    List<Long> ADMIN_ROLE_ID_LIST = Arrays.asList(1L);

}
