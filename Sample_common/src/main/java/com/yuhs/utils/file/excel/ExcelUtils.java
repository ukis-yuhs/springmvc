package com.yuhs.utils.file.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yuhaisheng on 2019/5/18.
 */
public class ExcelUtils {

    /**
     * 创建一个简单Excel文件
     * @param filePath
     */
    public static void createExcelFile(String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);

            HSSFWorkbook wb = new HSSFWorkbook();// 创建工作薄
            HSSFSheet sheet = wb.createSheet();// 创建工作表
            wb.setSheetName(0, "sheet0");// 设置工作表名

            HSSFRow row = null;
            HSSFCell cell = null;
            for (int i = 0; i < 10; i++) {
                row = sheet.createRow(i);// 新增一行
                cell = row.createCell(0);// 新增一列
                cell.setCellValue("第" + i + "行");
            }
            wb.write(fos);
            fos.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
