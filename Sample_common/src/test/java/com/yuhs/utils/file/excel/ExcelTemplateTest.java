package com.yuhs.utils.file.excel;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yuhaisheng on 2019/5/29.
 */
public class ExcelTemplateTest {

    private String filePath = "";
    private String path = "";
    private String pathx = "";
    private String outputXLSPath = "";
    private String outputXLSXPath = "";

    @Before
    public void testBefore() {
        filePath = "/Users/yuhaisheng/Documents/ideaProjects/springmvc/Sample_common/src/test/java/com/yuhs/utils/file/excel/123.xlsx";
        path = "/Users/yuhaisheng/Documents/ideaProjects/springmvc/Sample_common/src/test/java/com/yuhs/utils/file/excel/test.xls";
        pathx = "/Users/yuhaisheng/Documents/ideaProjects/springmvc/Sample_common/src/test/java/com/yuhs/utils/file/excel/test.xlsx";
        outputXLSPath = "/Users/yuhaisheng/Documents/ideaProjects/springmvc/Sample_common/src/test/java/com/yuhs/utils/file/excel/outputXLS.xls";
        outputXLSXPath = "/Users/yuhaisheng/Documents/ideaProjects/springmvc/Sample_common/src/test/java/com/yuhs/utils/file/excel/outputXLSX.xlsx";
    }

    /**
     * 检查指定sheet是否存在
     */
    @Test
    public void testSheetExist() {
        boolean checkExist = ExcelTemplate.sheetExist(path,"Test_SheetName");
        System.out.println("ExcelTemplate.sheetExist = " + checkExist);

    }

    /**
     * xls模版
     */
    @Test
    public void testCreateXLS() {
        try {
            ExcelTemplate.createXLS(path, outputXLSPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * xlsx模版
     */
    @Test
    public void testCreateXLSX() {
        try {
            ExcelTemplate.createXLSX(pathx, outputXLSXPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设定样式
     */
    @Test
    public void testSetStyle() {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            XSSFWorkbook wb = new XSSFWorkbook();// 创建工作薄
            XSSFSheet sheet = wb.createSheet();// 创建工作表
            wb.setSheetName(0, "sheet0");// 设置工作表名

            XSSFCellStyle columnTopStyle = ExcelTemplate.getStyle(wb);

            XSSFRow row = null;
            XSSFCell cell = null;
            for (int i = 0; i < 10; i++) {
                row = sheet.createRow(i);// 新增一行
                cell = row.createCell(0);// 新增一列
                cell.setCellValue("第" + i + "行");
                cell.setCellStyle(columnTopStyle);//设置样式
            }
            //指定区域设定样式
            CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 1, 4);
            sheet.addMergedRegion(cellRangeAddress);
            ExcelTemplate.setRegionStyle(sheet, cellRangeAddress, columnTopStyle);

            wb.write(fos);
            fos.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void test() {

    }
}
