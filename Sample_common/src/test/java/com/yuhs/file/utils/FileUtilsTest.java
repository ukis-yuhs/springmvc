package com.yuhs.file.utils;

import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/4/23.
 */
public class FileUtilsTest {
    
    @Test
    public void testGetFileType() throws Exception {
        String fileType = FileUtils.getFileType("/Users/yuhaisheng/Documents/ideaProjects/Server工程导入指南.xlsx");
        System.out.print("fileType = " + fileType);
    }
}
