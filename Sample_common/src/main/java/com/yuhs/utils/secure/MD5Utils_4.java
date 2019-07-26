package com.yuhs.utils.secure;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yuhaisheng on 2019/6/18.
 */
public class MD5Utils_4 {
    private static final String HEX_DIGITS[] = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F"
    };

    private static MD5Utils_4 fiveClass;

    private MD5Utils_4() {
    }

    public static synchronized MD5Utils_4 getInstance() {
        if (fiveClass == null) {
            fiveClass = new MD5Utils_4();
        }
        return fiveClass;
    }

    private String byteArrayToHexString(byte b[]) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            result.append(byteToHexString(b[i]));
        }
        return result.toString().toUpperCase();
    }

    private String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return (new StringBuilder(String.valueOf(HEX_DIGITS[d1]))).append(HEX_DIGITS[d2]).toString();
    }

    private byte[] md5Digest(byte src[]) throws NoSuchAlgorithmException {
        MessageDigest alg = MessageDigest.getInstance("MD5");
        return alg.digest(src);
    }

    public String encodePassword(String string) {
        return encodePassword(string, null);
    }

    public String encodePassword(String string, String expStr) {
        String resultString = null;
        if (expStr == null) {
            resultString = new String(string);
        } else {
            resultString = new String(string + expStr);
        }
        try {
            resultString = byteArrayToHexString(md5Digest(resultString.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString.toLowerCase();
    }
}
