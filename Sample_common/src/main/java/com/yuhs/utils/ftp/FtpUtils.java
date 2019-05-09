package com.yuhs.utils.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
/**
 * Created by yuhaisheng on 2019/5/9.
 */
public class FtpUtils {
    private FTPClient ftp;

    /**
     * FTP连接
     * @param path 上传到ftp服务器哪个路径下
     * @param addr 地址
     * @param port 端口号
     * @param username 用户名
     * @param password 密码
     * @return
     * @throws Exception
     */
    public boolean connect(String path, String addr, String port, String username, String password) throws Exception {
        boolean result = false;
        ftp = new FTPClient();
        int reply;
        ftp.connect(addr, Integer.valueOf(port));
        ftp.login(username, password);
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            return result;
        }
        if (!ftp.changeWorkingDirectory(path)) {
            ftp.makeDirectory(path);
            ftp.changeWorkingDirectory(path);
        }
        result = true;
        return result;
    }

    /**
     * 上传文件流
     * @param fileName 保存的文件名
     * @param inputStream 文件流
     * @throws Exception
     */
    public void uploadFile(String fileName, InputStream inputStream) throws Exception {
        ftp.enterLocalPassiveMode();
        ftp.storeFile(fileName, inputStream);
        inputStream.close();
    }

    /**
     * 上传文件
     * @param file 上传文件
     * @throws Exception
     */
    public void uploadFold(File file) throws Exception {
        ftp.makeDirectory(file.getName());
        ftp.changeWorkingDirectory(file.getName());
        String[] files = file.list();
        for (int i = 0; i < files.length; i++) {
            File file1 = new File(file.getPath() + "\\" + files[i]);
            if (file1.isDirectory()) {
                uploadFold(file1);
                ftp.changeToParentDirectory();
            } else {
                File file2 = new File(file.getPath() + "\\" + files[i]);
                FileInputStream input = new FileInputStream(file2);
                ftp.storeFile(file2.getName(), input);
                input.close();
            }
        }
    }

    /**
     * 断开连接
     * @throws Exception
     */
    public void disconnect() throws Exception {
        ftp.disconnect();
    }
}
