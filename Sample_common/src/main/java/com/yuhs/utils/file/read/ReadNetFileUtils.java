package com.yuhs.utils.file.read;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by yuhaisheng on 2019/5/8.
 */
public class ReadNetFileUtils {

    /**
     * 读取网络文件返回字节数组
     * @param filePath
     * @return
     */
    public static byte[] readFileByte(String filePath) {
        int HttpResult; // 服务器返回的状态
        byte[] bytes = new byte[100];
        try {

            URL url = new URL(filePath); // 创建URL
            URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
            urlconn.connect();
            HttpURLConnection httpconn = (HttpURLConnection) urlconn;
            HttpResult = httpconn.getResponseCode();
            if (HttpResult != HttpURLConnection.HTTP_OK) {
                // 无法连接到
                throw new IOException("无法连接");
            } else {
                urlconn.getInputStream();
                InputStream inputStream = urlconn.getInputStream();
                ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
                int ch;
                while ((ch = inputStream.read()) != -1) {
                    swapStream.write(ch);
                }
                bytes = swapStream.toByteArray();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    /**
     * 读取网络文件返回文本内容
     * @param filePath
     * @return
     */
    public static String readFileString(String filePath) {
        // 服务器返回的状态
        int HttpResult;
        String fileContent = new String();
        try {
            // 创建URL
            URL url = new URL(filePath);
            // 试图连接并取得返回状态码
            URLConnection urlconn = url.openConnection();
            urlconn.connect();
            HttpURLConnection httpconn = (HttpURLConnection) urlconn;
            HttpResult = httpconn.getResponseCode();
            // 不等于HTTP_OK说明连接不成功
            if (HttpResult != HttpURLConnection.HTTP_OK)
                // 无法连接到
                throw new IOException("无法连接");
            else {
                InputStreamReader isReader = new InputStreamReader(urlconn.getInputStream());
                BufferedReader reader = new BufferedReader(isReader);
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
                fileContent = buffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    /**
     * 判断给定url路径文件是否存在
     * @param httpPath
     * @return
     */
    public static Boolean existHttpPath(String httpPath) {
        URL httpurl = null;
        try {
            httpurl = new URL(httpPath);
            URLConnection rulConnection = httpurl.openConnection();
            rulConnection.getInputStream();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
