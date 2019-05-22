package com.yuhs.utils.zip;

import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/5/17.
 */
public class ZipUtilsTest {
    /**
     * 解压zip
     */
    @Test
    public void testUnzip() {
        String zipFilePath = "/Users/yuhaisheng/Desktop/file/";
        String unzipPath = "/Users/yuhaisheng/Desktop/file/";
        ZipUtils.unzip(zipFilePath,unzipPath);
    }
}
