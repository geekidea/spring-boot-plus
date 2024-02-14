package io.geekidea.boot.generator.exception;

import io.geekidea.boot.framework.exception.BusinessException;

/**
 * @author geekidea
 * @date 2024/1/15
 **/
public class GeneratorException extends BusinessException {

    public GeneratorException(String message) {
        super(message);
    }

}
