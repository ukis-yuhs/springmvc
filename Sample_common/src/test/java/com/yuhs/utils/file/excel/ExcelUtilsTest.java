package com.yuhs.utils.file.excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuhaisheng on 2019/5/18.
 */
public class ExcelUtilsTest {

    ExcelUtils excelUtils = null;

    @Before
    public void testBefore() {
        String path = "/Users/yuhaisheng/Documents/ideaProjects/springmvc/Sample_common/src/test/java/com/yuhs/utils/file/excel/test.xls";
        excelUtils = new ExcelUtils(path);
    }
    /**
     * 创建一个简单Excel文件
     */
    @Test
    public void testCreateExcelFile() {
        String excelFilePath = "/Users/yuhaisheng/Desktop/fileutils/myExcel.xls";
        ExcelUtils.createExcelFile(excelFilePath);
    }

    /**
     * 创建一个Excel
     */
    @Test
    public void testMakeExcel() {
        String sheetName = "Test_SheetName";
        String[] fieldName = {"一列","二列","三列","四列","五列","六列","七列","八列","九列","十列"};
        List<Object[]> data = new ArrayList<Object[]>();
        Object[] obj = null;
        obj = new Object[]{1,2,3,4,5,6,7,8,9,10};
        data.add(obj);
        obj = new Object[]{11,22,33,44,55,66,77,88,99,100};
        data.add(obj);
        obj = new Object[]{111,222,333,444,555,666,777,888,999,1000};
        data.add(obj);
        obj = new Object[]{1111,2222,3333,4444,5555,6666,7777,8888,9999,10000};
        data.add(obj);
        try {
            excelUtils.makeExcel(sheetName, fieldName, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取使用的最大行数
     */
    @Test
    public void testGetSheetLastRowNum() {
        try {
            int maxRowNum = excelUtils.getSheetLastRowNum(0);
            System.out.println("maxRowNum = " + maxRowNum);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写单元格内容,并读取出来
     */
    @Test
    public void testWrite() {
        try {
            excelUtils.write(0,5,5,"测试写单元格内容");
            String context = excelUtils.read(0,5,5);
            System.out.println("Context = " + context);
            //读出指定sheet内容
            List<Object[]> readContent = excelUtils.read(0);
            for (Object[] obj : readContent) {
                for (int i = 0; i < obj.length; i++) {
                    System.out.println("index = " + i + " context = " + obj[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
