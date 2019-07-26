package com.yuhs.utils.file.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yuhaisheng on 2019/6/18.
 */
public class ReadExcelUtils {

    /**
     * 读取Excel(2003或2007)
     *
     * @param file excel文件
     * @return
     * @throws IOException
     */
    public static List<List<Object>> readExcel(File file) throws IOException {
        String fileName = file.getName();
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
                .substring(fileName.lastIndexOf(".") + 1);
        if ("xls".equals(extension)) {
            return read2003Excel(file);
        } else if ("xlsx".equals(extension)) {
            return read2007Excel(file);
        } else {
            throw new IOException("不支持的文件类型");
        }
    }

    /**
     * 读入2003excel文件
     *
     * @param file 2003excel文件
     * @return
     * @throws IOException
     */
    private static List<List<Object>> read2003Excel(File file) throws IOException {
        List<List<Object>> list = new LinkedList<List<Object>>();
        HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet sheet = hwb.getSheetAt(0);
        Object value = null;
        HSSFRow row = null;
        HSSFCell cell = null;
        int counter = 0;
        for (int i = sheet.getFirstRowNum(); counter < sheet
                .getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            } else {
                counter++;
            }

            // 检查是否为空行
            // 如果前3列的值都为空，则认为是空行，跳过
            if (row.getFirstCellNum() > -1 && row.getLastCellNum() > 2) {
                if ((null == row.getCell(0) || "".equals(row.getCell(0).toString()))
                        && (null == row.getCell(1) || "".equals(row.getCell(1).toString()))
                        && (null == row.getCell(2) || "".equals(row.getCell(2).toString()))) {

                    System.out.println("=====空行======");
                    continue;
                }
            } else {
                System.out.println("=====空行======");
                continue;
            }

            List<Object> linked = new LinkedList<Object>();
            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (cell == null) {
                    continue;
                }
                DecimalFormat df = new DecimalFormat("0");// 格式化 number String
                // 字符
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
                DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
                switch (cell.getCellType()) {
                    case XSSFCell.CELL_TYPE_STRING:
                        value = cell.getStringCellValue();
                        System.out.println(i + "行" + j + " 列 is String type"
                                + "\tValue:" + value);
                        break;
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                            value = df.format(cell.getNumericCellValue());
                        } else if ("General".equals(cell.getCellStyle()
                                .getDataFormatString())) {
                            value = nf.format(cell.getNumericCellValue());
                        } else {
                            value = sdf.format(HSSFDateUtil.getJavaDate(cell
                                    .getNumericCellValue()));
                        }
                        System.out.println(i + "行" + j
                                + " 列 is Number type ; DateFormt:"
                                + cell.getCellStyle().getDataFormatString()
                                + "\tValue:" + value);
                        break;
                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        value = cell.getBooleanCellValue();
                        System.out.println(i + "行" + j + " 列 is Boolean type"
                                + "\tValue:" + value);
                        break;
                    case XSSFCell.CELL_TYPE_BLANK:
//                    value = "";
                        value = "__BLANK__";
                        System.out.println(i + "行" + j + " 列 is Blank type"
                                + "\tValue:" + value);
                        break;
                    default:
                        value = cell.toString();
                        System.out.println(i + "行" + j + " 列 is default type"
                                + "\tValue:" + value);
                }
                if (value == null || "".equals(value)) {
                    continue;
                }
                linked.add(value);
            }
            list.add(linked);
        }
        return list;
    }

    /**
     * 读入2007excel文件
     *
     * @param file 2007excel文件
     * @return
     * @throws IOException
     */
    private static List<List<Object>> read2007Excel(File file) throws IOException {
        List<List<Object>> list = new LinkedList<List<Object>>();
        // 构造 XSSFWorkbook 对象，strPath 传入文件路径
        XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
        // 读取第一章表格内容
        XSSFSheet sheet = xwb.getSheetAt(0);
        Object value = null;
        XSSFRow row = null;
        XSSFCell cell = null;
        int counter = 0;
        for (int i = sheet.getFirstRowNum(); counter < sheet
                .getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            } else {
                counter++;
            }

            // 检查是否为空行
            // 如果前3列的值都为空，则认为是空行，跳过
            if (row.getFirstCellNum() > -1 && row.getLastCellNum() > 2) {
                if ((null == row.getCell(0) || "".equals(row.getCell(0).toString()))
                        && (null == row.getCell(1) || "".equals(row.getCell(1).toString()))
                        && (null == row.getCell(2) || "".equals(row.getCell(2).toString()))) {

                    System.out.println("=====空行======");
                    continue;
                }
            } else {
                System.out.println("=====空行======");
                continue;
            }

            List<Object> linked = new LinkedList<Object>();
            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (cell == null) {
                    continue;
                }

                DecimalFormat df = new DecimalFormat("0");// 格式化 number String
                // 字符
                SimpleDateFormat sdf = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
                DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
                switch (cell.getCellType()) {
                    case XSSFCell.CELL_TYPE_STRING:
                        System.out.println(i + "行" + j + " 列 is String type");
                        value = cell.getStringCellValue();
                        break;
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        System.out.println(i + "行" + j
                                + " 列 is Number type ; DateFormt:"
                                + cell.getCellStyle().getDataFormatString());
                        if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                            value = df.format(cell.getNumericCellValue());
                        } else if ("General".equals(cell.getCellStyle()
                                .getDataFormatString())) {
                            value = nf.format(cell.getNumericCellValue());
                        } else {
                            value = sdf.format(HSSFDateUtil.getJavaDate(cell
                                    .getNumericCellValue()));
                        }
                        break;
                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        System.out.println(i + "行" + j + " 列 is Boolean type");
                        value = cell.getBooleanCellValue();
                        break;
                    case XSSFCell.CELL_TYPE_BLANK:
                        System.out.println(i + "行" + j + " 列 is Blank type");
                        value = "__BLANK__";
                        break;
                    default:
                        System.out.println(i + "行" + j + " 列 is default type");
                        value = cell.toString();
                }
                if (value == null || "".equals(value)) {
                    continue;
                }
                linked.add(value);
            }
            list.add(linked);
        }
        return list;
    }
}
