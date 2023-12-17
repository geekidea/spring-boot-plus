package io.geekidea.boot.util;

/**
 * @author geekidea
 * @date 2023/12/16
 **/
public class DateDiffUtil {

    /**
     * 计算相差的时间天到秒
     * xx天xx小时xx分钟xx秒
     *
     * @param diffTime
     * @return
     */
    public static String getDiffDaySecond(long diffTime) {
        // 计算相差的秒数
        long diff = diffTime / 1000;
        // 计算天
        long day = diff / (24 * 60 * 60);
        // 计算小时数
        long hour = diff / (60 * 60);
        // 计算分钟数
        long minute = (diff % (60 * 60)) / 60;
        // 计算秒数
        long second = diff % 60;
        String desc = day + "天" + hour + "小时" + minute + "分钟" + second + "秒";
        return desc;
    }

}
