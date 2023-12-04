package io.geekidea.boot.system;

import io.geekidea.boot.util.PasswordUtil;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 初始化密码测试
 *
 * @author geekidea
 * @date 2023/6/26
 **/
public class PasswordUtilTest {

    public static void main(String[] args) throws Exception {
        // 初始化密码
        String password = "123456";
        password = DigestUtils.md5Hex(password);
        System.out.println("md5:" + password);
        String initPassword = PasswordUtil.encrypt(password, "666" );
        System.out.println("initPassword:" + initPassword);
    }

}
