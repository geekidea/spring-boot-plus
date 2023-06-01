package io.geekidea.boot.framework.util;

import java.util.UUID;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
public class UUIDUtil {

    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
