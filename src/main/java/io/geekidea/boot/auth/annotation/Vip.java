package io.geekidea.boot.auth.annotation;

import java.lang.annotation.*;

/**
 * VIP级别注解
 *
 * @author geekidea
 * @date 2023/11/22
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Login
public @interface Vip {

    /**
     * VIP级别描述
     *
     * @return
     */
    String description() default "";

    /**
     * 等于某个VIP级别
     *
     * @return
     */
    int eq() default 0;

    /**
     * 大于某个VIP级别
     *
     * @return
     */
    int gt() default 0;

    /**
     * 小于某个VIP级别
     *
     * @return
     */
    int lt() default 0;

    /**
     * 大于等于某个VIP级别
     *
     * @return
     */
    int ge() default 0;

    /**
     * 小于等于某个VIP级别
     *
     * @return
     */
    int le() default 0;

    /**
     * 包含那些VIP级别
     *
     * @return
     */
    int[] levels() default {};

}
