package com.yuhs.utils.file.edit;

import com.yuhs.utils.file.read.ReadFileUtils;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;

/**
 * Created by yuhaisheng on 2019/5/9.
 */
public class EditFileUtilsTest {

    /**
     * 根据给出路径自动选择删除文件或整个文件夹
     */
    @Test
    public void testDeleteFiles() {
        String inputFilePath = "/Users/yuhaisheng/Desktop/file/2";
        EditFileUtils.deleteFiles(inputFilePath);
    }
    /**
     * 根据给出路径自动选择复制文件或整个文件夹
     */
    @Test
    public void testCopyFiles(){
        String inputFilePath = "/Users/yuhaisheng/Desktop/file/1";
        String outputFilePath = "/Users/yuhaisheng/Desktop/file/2";
        EditFileUtils.copyFiles(inputFilePath,outputFilePath);
    }

    /**
     * 将文件输入流写入文件
     */
    @Test
    public void testWriteFileFromInputStream() {
        String inputFilePath = "/Users/yuhaisheng/Desktop/file/test.txt";
        String outputFilePath = "/Users/yuhaisheng/Desktop/file/output.txt";
        InputStream inputStream = ReadFileUtils.readFileToInputStream(inputFilePath);
        EditFileUtils.writeFileFromInputStream(inputStream,outputFilePath);
    }

    /**
     * 向文件写入内容
     */
    @Test
    public void testWriteContent() {
        String inputFilePath = "/Users/yuhaisheng/Desktop/file/test.txt";
        EditFileUtils.writeContent(inputFilePath,"11111111111111");
        EditFileUtils.writeContent(inputFilePath,"AAAAAAAAAAAAAA");
        EditFileUtils.writeContent(inputFilePath,"测试测试测试测试测试");
    }
    /**
     * 创建文件，若文件夹不存在则自动创建文件夹，若文件存在则删除旧文件
     */
    @Test
    public void testCreateFile() {
        String filePath = "/Users/yuhaisheng/Desktop/file/test.txt";
        File file = EditFileUtils.createFile(filePath);
        System.out.println(file.getName());
    }

    /**
     * 创建文件，若文件夹不存在则自动创建文件夹，若文件存在则重命名
     */
    @Test
    public void testCreateFileGenerateFileName() {
        String filePath = "/Users/yuhaisheng/Desktop/file/test.txt";
        File file = EditFileUtils.createFileGenerateFileName(filePath);
        System.out.println(file.getName());
    }

    /**
     * 创建文件（根据byte[]）
     */
    @Test
    public void testGetBytes() {
        String localFilePath = "/Users/yuhaisheng/Documents/sts4/记事本";
        byte [] contet = ReadFileUtils.getBytes(localFilePath);
        EditFileUtils.createFile(contet, "/Users/yuhaisheng/Documents/sts4/", "test2");
    }
}
