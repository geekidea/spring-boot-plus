package io.geekidea.boot.util;

import com.github.yitter.idgen.YitIdHelper;

/**
 * @author geekidea
 * @date 2024/2/12
 **/
public class IdUtil {

    /**
     * 获取短的雪花算法ID
     *
     * @return
     */
    public static long getId() {
        return YitIdHelper.nextId();
    }

}
