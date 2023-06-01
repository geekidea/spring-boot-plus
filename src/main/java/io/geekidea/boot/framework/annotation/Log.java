package io.geekidea.boot.framework.annotation;

import io.geekidea.boot.framework.enums.SysLogEnum;

/**
 * @author geekidea
 * @date 2022/8/3
 **/
public @interface Log {

    /**
     * 描述
     *
     * @return
     */
    String value() default "";


    SysLogEnum type() default SysLogEnum.OTHER;

}
