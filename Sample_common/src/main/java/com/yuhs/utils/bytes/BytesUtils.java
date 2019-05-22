package com.yuhs.utils.bytes;

import com.yuhs.utils.string.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by yuhaisheng on 2019/5/21.
 */
public class BytesUtils {
    /**
     * 判断字节数组是否为空
     * @param target
     * @return
     */
    public static boolean isEmpty(byte[] target){
        return null == target || 0 == target.length;
    }

    /**
     * 字节数组转为字符串(该方法默认以ISO-8859-1转码)
     * @param data
     * @return
     */
    public static String getString(byte[] data){
        return getString(data, "ISO-8859-1");
    }

    /**
     * 字节数组转为字符串
     * @param data
     * @param charset
     * @return
     */
    public static String getString(byte[] data, String charset){
        if(isEmpty(data)){
            return "";
        }
        if(StringUtils.isEmpty(charset)){
            return new String(data);
        }
        try {
            return new String(data, charset);
        } catch (UnsupportedEncodingException e) {
            // 系统不支持该字符集
            return new String(data);
        }
    }
}
