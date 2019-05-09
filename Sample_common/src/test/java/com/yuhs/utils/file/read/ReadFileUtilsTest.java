package com.yuhs.utils.file.read;

import com.yuhs.utils.file.edit.EditFileUtils;
import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/4/23.
 */
public class ReadFileUtilsTest {

    /**
     * 读取本地文件内容
     */
    @Test
    public void testReadLocalFile() {
        String localFilePath = "/Users/yuhaisheng/Documents/sts4/记事本";
        String fileContent = ReadFileUtils.readLocalFileByLine(localFilePath);
        System.err.println(fileContent);
    }

    @Test
    public void testGetBytes() {
        String localFilePath = "/Users/yuhaisheng/Documents/sts4/记事本";
        byte [] contet = ReadFileUtils.getBytes(localFilePath);
        EditFileUtils.createFile(contet, "/Users/yuhaisheng/Documents/sts4/", "test2");
    }

}
