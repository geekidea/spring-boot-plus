package io.geekidea.boot.auth.annotation;

import java.lang.annotation.*;

/**
 * 数据权限范围注解
 *
 * @author geekidea
 * @date 2023/12/02
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    // TODO 如果是商城，则可新增商家表别名，和对应的筛选列，如果有部门数据权限，则新增部门表和列别名

    /**
     * 用户表别名
     *
     * @return
     */
    String userAlias() default "";

    /**
     * 筛选的userId列
     *
     * @return
     */
    String userIdColumn() default "id";

}
