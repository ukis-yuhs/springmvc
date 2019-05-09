package com.yuhs.utils.download;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by yuhaisheng on 2019/5/9.
 */
public class DownLoadUtils {
    /**
     * 将list内容以文件的形式下载
     *
     * @param list     下载内容
     * @param fileName 下载文件名
     * @param request  http请求
     * @param response http相应
     * @throws IOException
     */
    public static void downloadFile(ArrayList<String> list, String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 输出文件
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            byte[] bytes = new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
            os.write(bytes);
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i);
                byte[] b = str.getBytes("UTF-8");
                os.write(b);
                os.write("\r\n".getBytes("UTF-8"));
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            os.close();
        }
    }
}
