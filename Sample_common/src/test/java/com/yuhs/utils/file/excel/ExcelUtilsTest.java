package com.yuhs.utils.file.excel;

import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/5/18.
 */
public class ExcelUtilsTest {

    /**
     * 创建一个简单Excel文件
     */
    @Test
    public void testCreateExcelFile() {
        String excelFilePath = "/Users/yuhaisheng/Desktop/fileutils/myExcel.xls";
        ExcelUtils.createExcelFile(excelFilePath);
    }
}
