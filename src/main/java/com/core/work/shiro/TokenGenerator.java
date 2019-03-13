package com.core.work.shiro;

import com.core.work.exception.BaseException;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * @Description: 生成token
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/3/13 0013 上午 10:57
 */
public class TokenGenerator {

    public static String generateValue() {
        return generateValue(UUID.randomUUID().toString());
    }

    private static final char[] HEX_CODE = "0123456789abcdef".toCharArray();

    public static String toHexString(byte[] data) {
        if (data == null) {
            return null;
        }
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(HEX_CODE[(b >> 4) & 0xF]);
            r.append(HEX_CODE[(b & 0xF)]);
        }
        return r.toString();
    }

    public static String generateValue(String param) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(param.getBytes());
            byte[] messageDigest = algorithm.digest();
            return toHexString(messageDigest);
        } catch (Exception e) {
            throw new BaseException("生成Token失败", e);
        }
    }
}
