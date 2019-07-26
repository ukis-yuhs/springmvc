package com.yuhs.utils.secure;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by yuhaisheng on 2019/5/9.
 */
public class MD5Utils_3 {
    private static final String CHARSET_UTF8 = "UTF-8";

    /**
     * MD5签名
     * @param text
     * @return
     */
    public static String sign(String text) {
        return DigestUtils.md5Hex(getContentBytes(text, CHARSET_UTF8));
    }

    /**
     * MD5签名
     * @param text
     * @param charset
     * @return
     */
    public static String sign(String text, String charset) {
        return DigestUtils.md5Hex(getContentBytes(text, charset));
    }

    /**
     *
     * @param content
     * @param charset
     * @return
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("The charset is wrong, charset=" + charset);
        }
    }
}
