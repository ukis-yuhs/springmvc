package com.yuhs.utils.file.read;

import java.io.*;

/**
 * Created by yuhaisheng on 2019/4/23.
 */
public class ReadFileUtils {

    /**
     * 读取本地文件(按行读取)
     * @param filePath
     * @return
     */
    public static String readLocalFileByLine(String filePath) {
        File file = new File(filePath);
        String content = new String();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer buffer = new StringBuffer();
            // 用来保存每行读取的内容
            String line;
            // 读取第一行
            line = reader.readLine();
            // 如果 line 为空说明读完了
            while (line != null) {
                // 将读到的内容添加到 buffer 中
                buffer.append(line);
                // 添加换行符
                buffer.append(" ");
                // 读取下一行
                line = reader.readLine();
            }
            content = buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return content;
    }

    /**
     * 获得指定文件的byte数组
     * @param filePath
     * @return
     */
    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            File file = new File(filePath);
            fileInputStream = new FileInputStream(file);
            byteArrayOutputStream = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fileInputStream.read(b)) != -1) {
                byteArrayOutputStream.write(b, 0, n);
            }
            buffer = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (null != byteArrayOutputStream) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return buffer;
    }

}
