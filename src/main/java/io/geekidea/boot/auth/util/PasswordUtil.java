package io.geekidea.boot.auth.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author geekidea
 * @date 2022/7/13
 **/
public class PasswordUtil {

    public static String encrypt(String password, String salt) throws Exception {
        if (StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("密码不能为空");
        }
        if (StringUtils.isBlank(salt)) {
            salt = "";
        }
        String encryptPassword = DigestUtils.md5Hex(password + salt);
        return encryptPassword;
    }

    public static void main(String[] args) throws Exception {
        String initPassword = PasswordUtil.encrypt("123456", "666");
        System.out.println(initPassword);
    }

}
