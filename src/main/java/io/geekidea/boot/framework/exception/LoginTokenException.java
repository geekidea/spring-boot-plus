package io.geekidea.boot.framework.exception;

/**
 * 登录token异常
 *
 * @author geekidea
 * @date 2023-06-23
 */
public class LoginTokenException extends LoginException {

    public LoginTokenException(String message) {
        super(message);
    }

}
