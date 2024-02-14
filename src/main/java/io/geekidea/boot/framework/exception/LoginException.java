package io.geekidea.boot.framework.exception;

/**
 * 登录异常
 *
 * @author geekidea
 * @date 2018-11-08
 */
public class LoginException extends RuntimeException {

    public LoginException(String message) {
        super(message);
    }

}
