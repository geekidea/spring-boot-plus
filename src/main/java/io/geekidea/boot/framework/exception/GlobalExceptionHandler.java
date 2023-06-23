package io.geekidea.boot.framework.exception;

import io.geekidea.boot.auth.exception.LoginException;
import io.geekidea.boot.auth.exception.LoginTokenException;
import io.geekidea.boot.framework.response.ApiCode;
import io.geekidea.boot.framework.response.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

/**
 * 全局异常处理
 *
 * @author geekidea
 * @date 2021-4-13
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     *
     * @param exception
     * @return
     */
    public static ApiResult handle(Throwable exception) {
        if (exception instanceof BusinessException || exception instanceof LoginException) {
            return ApiResult.fail(exception.getMessage());
        } else if (exception instanceof LoginTokenException) {
            return ApiResult.fail(ApiCode.TOKEN_EXCEPTION, exception.getMessage());
        } else if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) exception;
            BindingResult bindingResult = ex.getBindingResult();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            FieldError fieldError = fieldErrors.get(0);
            String errorMessage = fieldError.getDefaultMessage();
            log.error("参数校验错误" + "：" + errorMessage);
            return ApiResult.fail(errorMessage);
        } else if (exception instanceof HttpMessageNotReadableException) {
            return ApiResult.fail("请求参数解析异常");
        } else if (exception instanceof MethodArgumentTypeMismatchException) {
            return ApiResult.fail("请求参数数据类型错误");
        } else if (exception instanceof DuplicateKeyException) {
            return ApiResult.fail("数据违反唯一约束");
        } else if (exception instanceof DataIntegrityViolationException) {
            return ApiResult.fail("数据完整性异常");
        }
        return ApiResult.fail();
    }

    /**
     * 全局异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult exceptionHandle(Exception exception) {
        log.error("exception:", exception);
        return handle(exception);
    }
}
