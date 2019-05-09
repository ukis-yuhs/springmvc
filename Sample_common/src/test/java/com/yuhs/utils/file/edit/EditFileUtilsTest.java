package com.yuhs.utils.file.edit;

import com.yuhs.utils.file.read.ReadFileUtils;
import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/5/9.
 */
public class EditFileUtilsTest {

    @Test
    public void testGetBytes() {
        String localFilePath = "/Users/yuhaisheng/Documents/sts4/记事本";
        byte [] contet = ReadFileUtils.getBytes(localFilePath);
        EditFileUtils.createFile(contet, "/Users/yuhaisheng/Documents/sts4/", "test2");
    }
}
