package com.yuhs.utils.file.convert;

import org.junit.Test;

import java.net.URL;

/**
 * Created by yuhaisheng on 2019/5/8.
 */
public class ConvertReadFileUtilsTest {

    /**
     * 测试使用jar包版本是否相同
     *
     */
    @Test
    public void testPoiVersion() {
        ClassLoader classloader = org.apache.poi.POIXMLDocument.class.getClassLoader();
        URL res = classloader.getResource("org/apache/poi/POIXMLDocument.class");
        String path = res.getPath();
        System.out.println("POI OOXML came from " + path);

        classloader = org.apache.poi.xssf.usermodel.XSSFWorkbook.class.getClassLoader();
        res = classloader.getResource("org.apache.poi.xssf.usermodel.XSSFWorkbook.class");
        if(res != null)
            path = res.getPath();
        System.out.println("work path is        " + path);
    }


    /**
     * 将docx文件保存为html文件
     * @throws Exception
     */
    @Test
    public void testDocxToHtmlFile() throws Exception {
        String docxFileName = "/Users/yuhaisheng/Desktop/读书笔记/JDK.docx";
        String htmlFilePath = ConvertFileUtils.docxToHtmlFile(docxFileName,"/Users/yuhaisheng/Desktop");
        System.err.println(htmlFilePath);
    }

    /**
     * 将docx文件转换为html文本
     * @throws Exception
     */
    @Test
    public void testDocxToHtmlString() throws Exception {
        String docxFileName = "/Users/yuhaisheng/Desktop/读书笔记/JDK.docx";
        String htmlString = ConvertFileUtils.docxToHtmlString(docxFileName);
        System.err.println(htmlString);
    }

    /**
     * 将docx文件转换为适合UEditorhtml文本
     * @throws Exception
     */
    @Test
    public void testDocxToHtmlForUEditorStringString() throws Exception {
        String docxFileName = "/Users/yuhaisheng/Desktop/读书笔记/JDK.docx";
        String htmlString = ConvertFileUtils.docxToHtmlForUEditorString(docxFileName);
        System.err.println(htmlString);
    }

    /**
     * 将doc文件保存为html文件
     * @throws Exception
     */
    @Test
    public void testDocToHtmlFile() throws Exception {
        String docFileName = "/Users/yuhaisheng/Desktop/读书笔记/简历/于海胜.doc";
        String htmlFilePath = ConvertFileUtils.docToHtmlFile(docFileName,"/Users/yuhaisheng/Desktop");
        System.err.println(htmlFilePath);
    }

    /**
     * 将doc文件转换为html文本
     * @throws Exception
     */
    @Test
    public void testDocToHtmlString() throws Exception {
        String docFileName = "/Users/yuhaisheng/Desktop/读书笔记/简历/于海胜.doc";
        String htmlString = ConvertFileUtils.docToHtmlString(docFileName);
        System.err.println(htmlString);
    }

    /**
     * 将doc文件转换为适合UEditorhtml文本
     * @throws Exception
     */
    @Test
    public void testDocToHtmlForUEditorStringString() throws Exception {
        String docFileName = "/Users/yuhaisheng/Desktop/读书笔记/简历/于海胜.doc";
        String htmlString = ConvertFileUtils.docToHtmlForUEditorString(docFileName);
        System.err.println(htmlString);
    }
}
