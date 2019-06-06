package com.yuhs.utils.file.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.awt.*;
import java.io.*;

/**
 * Created by yuhaisheng on 2019/5/28.
 */
public class ExcelTemplate {

    /**
     * 判断文件的sheet是否存在
     *
     * @param filePath  文件路径
     * @param sheetName 表格索引名
     * @return
     */
    public static boolean sheetExist(String filePath, String sheetName) {
        boolean flag = false;
        File file = new File(filePath);
        //文件存在
        if (file.exists()) {
            //创建workbook
            try {
                HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
                //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
                HSSFSheet sheet = workbook.getSheet(sheetName);
                if (sheet != null) {
                    flag = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //文件不存在
            flag = false;
        }
        return flag;
    }

    /**
     * (2003 xls后缀 导出)
     *
     * @param importFilePath
     * @param exportFilePath
     * @throws IOException
     */
    public static void createXLS(String importFilePath, String exportFilePath) throws IOException {
        try {
            //excel模板路径
            File file = new File(importFilePath);
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(file));
            //读取excel模板
            HSSFWorkbook wb = new HSSFWorkbook(poifsFileSystem);
            //读取了模板内所有sheet内容
            HSSFSheet sheet = wb.getSheetAt(0);
            //如果这行没有了，整个公式都不会有自动计算的效果的
            sheet.setForceFormulaRecalculation(true);
            //TODO 举例说明 模版未初始化的单元格不能获取
            //在相应的单元格进行赋值
            //第2行 第1列
            HSSFCell cell = sheet.getRow(1).getCell(0);
            cell.setCellValue("一");
            HSSFCell cell2 = sheet.getRow(2).getCell(1);
            cell2.setCellValue("二");
            sheet.getRow(3).getCell(2).setCellValue("三");
            //TODO 举例说明
            //修改模板内容导出新模板
            FileOutputStream out = new FileOutputStream(exportFilePath);
            wb.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("文件读取错误!");
        }

    }

    /**
     * (2007 xlsx后缀 导出)
     *
     * @param importFilePath
     * @param exportFilePath
     * @throws IOException
     */
    public static void createXLSX(String importFilePath, String exportFilePath) throws IOException {
        //excel模板路径
        File file = new File(importFilePath);
        InputStream inputStream = new FileInputStream(file);
        //读取excel模板
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        //读取了模板内所有sheet内容
        XSSFSheet sheet = wb.getSheetAt(0);
        //如果这行没有了，整个公式都不会有自动计算的效果的
        sheet.setForceFormulaRecalculation(true);
        //在相应的单元格进行赋值
        //TODO 举例说明
        //第2行 第1列
        XSSFCell cell = sheet.getRow(1).getCell(0);
        cell.setCellValue("壹");
        XSSFCell cell2 = sheet.getRow(2).getCell(1);
        cell2.setCellValue("贰");
        sheet.getRow(3).getCell(2).setCellValue("叁");
        //TODO 举例说明
        //修改模板内容导出新模板
        FileOutputStream out = new FileOutputStream(exportFilePath);
        wb.write(out);
        out.close();
    }

    /**
     * 列数据信息单元格样式
     *
     * @param workbook
     * @return
     */
    public static XSSFCellStyle getStyle(XSSFWorkbook workbook) {
        // 设置字体
        XSSFFont font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 10);
        // 字体加粗
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        XSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(new XSSFColor(Color.BLACK));
        // 设置左边框;
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(new XSSFColor(Color.BLACK));
        // 设置右边框;
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(new XSSFColor(Color.BLACK));
        // 设置顶边框;
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(new XSSFColor(Color.BLACK));
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 设置单元格背景颜色
        style.setFillForegroundColor(new XSSFColor(new Color(189, 215, 238)));
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        return style;
    }

    /**
     * 给指定的区域设定样式
     * @param sheet
     * @param region
     * @param cs
     */
    public static void setRegionStyle(XSSFSheet sheet, CellRangeAddress region, XSSFCellStyle cs) {
        for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
            XSSFRow row = sheet.getRow(i);
            if (row == null)
                row = sheet.createRow(i);
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                XSSFCell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                    cell.setCellValue("");
                }
                cell.setCellStyle(cs);

            }
        }
    }
}