package com.yuhs.utils.file.read;

import com.yuhs.utils.file.edit.EditFileUtils;
import org.junit.Test;

import java.io.File;

/**
 * 从网络获取文件
 * Created by yuhaisheng on 2019/5/8.
 */
public class ReadNetReadFileUtilsTest {

    /**
     * 读取网络文件保存文件
     */
    @Test
    public void testReadFileByte1() {
        String netFilePath = "http://xxxx";
        byte[] bytes = ReadNetFileUtils.readFileByte(netFilePath);
        EditFileUtils.createFile(bytes, "/Users/yuhaisheng/Desktop/读书笔记/","test.html");
        System.err.println(bytes);
    }

    /**
     * 读取网络文件保存文本
     */
    @Test
    public void testReadFileByte2() {
        String netFilePath = "http://xxxx";
        byte[] bytes = ReadNetFileUtils.readFileByte(netFilePath);
        System.err.println(new String(bytes));
    }

    /**
     * 读取网络文件内容
     */
    @Test
    public void testReadFileString() {
        String netFilePath = "http://xxxx";
        String content = ReadNetFileUtils.readFileString(netFilePath);
        System.err.println(content);
    }

    /**
     * 判断给定url路径文件是否存在
     */
    @Test
    public void testExistHttpPath() {
        String image ="http://xxxx";
        Boolean boolen = ReadNetFileUtils.existHttpPath(image);
        System.err.println(boolen);
    }

    /**
     *
     */
    @Test
    public void testFile() {
        String image ="http://xxxx.txt";//这种通过直接实例化文件的方式，url所指应为具体文件，不需要发起http请求
        File file = new File(image);
        System.err.println(file.getName());
    }
}
