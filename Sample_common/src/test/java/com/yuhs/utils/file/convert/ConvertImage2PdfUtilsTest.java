package com.yuhs.utils.file.convert;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by yuhaisheng on 2019/7/4.
 */
public class ConvertImage2PdfUtilsTest {

    @Test
    public void testImage2Pdf() {
        try {
            ConvertImage2PdfUtils.img2Pdf(
                    "/Users/yuhaisheng/Desktop/读书笔记/pic/5.png",
                    "/Users/yuhaisheng/Documents/ideaProjects/springmvc/Sample_common/src/test/java/com/yuhs/utils/file/convert/1.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
