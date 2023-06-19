package io.geekidea.boot.framework.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * 判断对象中的属性是否有值
 *
 * @author geekidea
 * @date 2023/6/20
 **/
public class ObjectValueUtil {

    private static String SERIAL_VERSION_UID = "serialVersionUID";

    /**
     * 对象的属性是否没有值
     *
     * @param object
     * @return
     */
    public static boolean isNotHaveValue(Object object) {
        return !isHaveValue(object);
    }

    /**
     * 对象的属性是否有值
     *
     * @param object
     * @return
     */
    public static boolean isHaveValue(Object object) {
        if (object == null) {
            return false;
        }
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            if (SERIAL_VERSION_UID.equals(name)) {
                continue;
            }
            try {
                Object value = field.get(object);
                Class<?> type = field.getType();
                if (type == String.class) {
                    String string = (String) value;
                    if (StringUtils.isNotBlank(string)) {
                        return true;
                    }
                } else {
                    if (value != null) {
                        return true;
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
