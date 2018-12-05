package com.endofmaster.commons.util.sign;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

/**
 * @author HJ.Yu
 */
public class PasswordUtils {
    /**
     * 验证密码
     *
     * @param password  传入密码
     * @param dataValue 库中存储的加密后密码
     * @param salt      盐
     */
    public static boolean verifyPassword(String password, String dataValue, String salt) {
        String newPassword = new HmacUtils(HmacAlgorithms.HMAC_MD5, salt).hmacHex(password);
        return newPassword.equals(dataValue);
    }

    /**
     * 创建加盐密码
     *
     * @param password 明文密码
     * @param salt     盐
     */
    public static String buildPassword(String password, String salt) {
        return new HmacUtils(HmacAlgorithms.HMAC_MD5, salt).hmacHex(password);
    }
}
