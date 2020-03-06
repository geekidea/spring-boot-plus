package io.geekidea.springbootplus.test;

import io.geekidea.springbootplus.framework.util.PhoneUtil;
import org.junit.Test;

/**
 * 手机号码工具类测试
 *
 * @author geekidea
 * @date 2020/2/26
 **/
public class PhoneUtilTest {

    /**
     * 手机号码脱敏测试
     */
    @Test
    public void test() {
        String phone = PhoneUtil.desensitize("18812345678");
        System.out.println("phone = " + phone);
    }

}
