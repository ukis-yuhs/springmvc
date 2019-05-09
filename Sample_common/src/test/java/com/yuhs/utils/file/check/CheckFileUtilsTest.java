package com.yuhs.utils.file.check;

import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/5/9.
 */
public class CheckFileUtilsTest {
    /**
     * 获取文件类型
     * @throws Exception
     */
    @Test
    public void testGetFileType() throws Exception {
        String fileType = CheckFileUtils.getFileType("/Users/yuhaisheng/Documents/sts4/记事本");
        System.out.print("fileType = " + fileType);
    }
}
