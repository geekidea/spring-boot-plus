package io.geekidea.boot.framework.exception;

/**
 * 没有权限异常
 *
 * @author geekidea
 * @date 2018-11-08
 */
public class NotAuthException extends BusinessException {

    public NotAuthException(String message) {
        super(message);
    }

}
