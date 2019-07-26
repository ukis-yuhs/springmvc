package com.yuhs.utils.bytes;

import com.yuhs.utils.string.StringUtils;

import java.io.*;

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

    /**
     * 将对象转换位byte数组
     * @param obj 对象
     * @return
     */
    public static byte[] toByteArray(Object obj) {

        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }

    /**
     * 将byte数组转换为对象
     * @param bytes byte数组
     * @return
     */
    public static Object toObject(byte[] bytes) {

        Object obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
}
