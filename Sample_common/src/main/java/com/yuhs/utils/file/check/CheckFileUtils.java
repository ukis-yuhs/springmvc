package com.yuhs.utils.file.check;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by yuhaisheng on 2019/5/9.
 */
public class CheckFileUtils {
    //文件头信息
    public static final HashMap<String, String> FILE_HEAD_TYPE = new HashMap<String, String>();
    static {
        // images
        FILE_HEAD_TYPE.put("FFD8FF", "jpg");
        FILE_HEAD_TYPE.put("89504E47", "png");
        FILE_HEAD_TYPE.put("47494638", "gif");
        FILE_HEAD_TYPE.put("49492A00", "tif");
        FILE_HEAD_TYPE.put("424D", "bmp");
        FILE_HEAD_TYPE.put("41433130", "dwg");
        FILE_HEAD_TYPE.put("38425053", "psd");
        FILE_HEAD_TYPE.put("7B5C727466", "rtf");
        FILE_HEAD_TYPE.put("3C3F786D6C", "xml");
        FILE_HEAD_TYPE.put("68746D6C3E", "html");
        FILE_HEAD_TYPE.put("44656C69766572792D646174653A", "eml");
        FILE_HEAD_TYPE.put("D0CF11E0", "doc");
        FILE_HEAD_TYPE.put("D0CF11E0", "xls");
        FILE_HEAD_TYPE.put("5374616E64617264204A", "mdb");
        FILE_HEAD_TYPE.put("252150532D41646F6265", "ps");
        FILE_HEAD_TYPE.put("255044462D312E", "pdf");
        FILE_HEAD_TYPE.put("504B0304", "docx");
        FILE_HEAD_TYPE.put("504B0304", "xlsx");
        FILE_HEAD_TYPE.put("52617221", "rar");
        FILE_HEAD_TYPE.put("57415645", "wav");
        FILE_HEAD_TYPE.put("41564920", "avi");
        FILE_HEAD_TYPE.put("2E524D46", "rm");
        FILE_HEAD_TYPE.put("000001BA", "mpg");
        FILE_HEAD_TYPE.put("000001B3", "mpg");
        FILE_HEAD_TYPE.put("6D6F6F76", "mov");
        FILE_HEAD_TYPE.put("3026B2758E66CF11", "asf");
        FILE_HEAD_TYPE.put("4D546864", "mid");
        FILE_HEAD_TYPE.put("1F8B08", "gz");
    }

    /**
     * 根据文件路径获取文件头信息
     * @param filePath
     * @return
     */
    public static String getFileType(String filePath) {
        return FILE_HEAD_TYPE.get(getFileHeader(filePath));
    }

    /**
     * 取得文件头信息
     * @param filePath
     * @return
     */
    public static String getFileHeader(String filePath) {
        FileInputStream is = null;
        String value = null;
        try {
            is = new FileInputStream(filePath);
            byte[] b = new byte[4];
            /*
             * int read() 从此输入流中读取一个数据字节。
             * int read(byte[] b) 从此输入流中将最多 b.length个字节的数据读入一个 byte 数组中。
             * int read(byte[] b, int off, int len)从此输入流中将最多len个字节的数据读入一个byte数组中。
             */
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    /**
     * 将要读取文件头信息的文件的byte数组转换成string类型表示
     * @param src
     * @return
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }
}
