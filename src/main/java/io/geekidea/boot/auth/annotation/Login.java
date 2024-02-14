package io.geekidea.boot.auth.annotation;

import java.lang.annotation.*;

/**
 * 需要登录的注解
 *
 * @author geekidea
 * @date 2023/11/22
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {

}
