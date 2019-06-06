package com.yuhs.utils.file.excel;

import com.yuhs.utils.collection.CollectionUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * 依赖于
 * poi-3.9-20121203.jar
 * poi-ooxml-3.9-20121203.jar
 * poi-ooxml-schemas-3.9-20121203.jar
 * dom4j-1.6.1.jar
 * <p>
 * Created by yuhaisheng on 2019/5/18.
 */
public class ExcelUtils {

    /**
     * excel文件路径
     */
    private String path;
    /**
     * 是否自适应列宽
     */
    private boolean autoColumnWidth = false;

    public ExcelUtils() {
    }

    public ExcelUtils(String path) {
        this.path = path;
    }

    public void setAutoColumnWidth(boolean autoColumnWidth) {
        this.autoColumnWidth = autoColumnWidth;
    }

    /**
     * 读取某个工作簿上的所有单元格的值
     *
     * @param sheetOrder 工作簿序号，从0开始
     * @return 所有单元格的值
     * @throws IOException            加载excel文件IO异常
     * @throws InvalidFormatException
     */
    public List<Object[]> read(int sheetOrder) throws IOException, InvalidFormatException {
        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = WorkbookFactory.create(fis);
        if (fis != null) {
            fis.close();
        }
        Sheet sheet = workbook.getSheetAt(sheetOrder);
        //用来记录excel值
        List<Object[]> valueList = new LinkedList<Object[]>();
        //循环遍历每一行、每一列。
        for (Row row : sheet) {
            //每一行
            Object[] rowObject = null;
            for (Cell cell : row) {
                //cell.getCellType是获得cell里面保存的值的type
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_BOOLEAN:
                        //得到Boolean对象的方法
                        rowObject = CollectionUtil.addObjectToArray(rowObject, cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        //先看是否是日期格式
                        if (DateUtil.isCellDateFormatted(cell)) {
                            //读取日期格式
                            rowObject = CollectionUtil.addObjectToArray(rowObject, cell.getDateCellValue());
                        } else {
                            DecimalFormat df = new DecimalFormat();
                            //单元格的值,替换掉,
                            String value = df.format(cell.getNumericCellValue()).replace(",", "");
                            //读取数字
                            rowObject = CollectionUtil.addObjectToArray(rowObject, value);
                        }
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        //读取公式
                        rowObject = CollectionUtil.addObjectToArray(rowObject, cell.getCellFormula());
                        break;
                    case Cell.CELL_TYPE_STRING:
                        //读取String
                        rowObject = CollectionUtil.addObjectToArray(rowObject, cell.getRichStringCellValue().toString());
                        break;
                }
            }
            //将这行添加到list。
            valueList.add(rowObject);
        }
        return valueList;
    }

    /**
     * 读取某个工作簿上的某个单元格的值
     *
     * @param sheetOrder 工作簿序号,从0开始
     * @param colum      列数 从1开始
     * @param row        行数 从1开始
     * @return 单元格的值
     * @throws IOException
     * @throws InvalidFormatException
     */
    public String read(int sheetOrder, int colum, int row) throws IOException, InvalidFormatException {
        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = WorkbookFactory.create(fis);
        if (fis != null) {
            fis.close();
        }
        Sheet sheet = workbook.getSheetAt(sheetOrder);
        Row rows = sheet.getRow(row - 1);
        Cell cell = rows.getCell(colum - 1);
        String content = cell.getStringCellValue();
        return content;
    }

    /**
     * 在指定的工作簿、行、列书写值
     *
     * @param sheetOrder 工作簿序号，基于0.
     * @param colum      列 基于1
     * @param row        行 基于1
     * @param content    将要被书写的内容
     * @throws IOException
     * @throws InvalidFormatException
     */
    public void write(int sheetOrder, int colum, int row, String content) throws IOException, InvalidFormatException {
        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = WorkbookFactory.create(fis);
        if (fis != null) {
            fis.close();
        }
        Sheet sheet = workbook.getSheetAt(sheetOrder);
        Row rows = sheet.createRow(row - 1);
        Cell cell = rows.createCell(colum - 1);
        cell.setCellValue(content);
        FileOutputStream fileOut = new FileOutputStream(path);
        workbook.write(fileOut);
        fileOut.close();
    }

    /**
     * 得到一个工作区最后一条记录的序号，相当于这个工作簿共多少行数据
     *
     * @param sheetOrder 工作区序号
     * @return 行号
     * @throws IOException
     * @throws InvalidFormatException
     */
    public int getSheetLastRowNum(int sheetOrder) throws IOException, InvalidFormatException {
        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = WorkbookFactory.create(fis);
        if (fis != null) {
            fis.close();
        }
        Sheet sheet = workbook.getSheetAt(sheetOrder);
        return sheet.getLastRowNum();
    }

    /**
     * 在磁盘生成一个含有内容的excel,路径为path属性
     *
     * @param sheetName 导出的sheet名称
     * @param fieldName 列名数组
     * @param data      数据组
     * @throws IOException
     */
    public void makeExcel(String sheetName, String[] fieldName, List<Object[]> data) throws IOException {
        //在内存中生成工作薄
        HSSFWorkbook workbook = makeWorkBook(sheetName, fieldName, data);
        //截取文件夹路径
        String filePath = path.substring(0, path.lastIndexOf(System.getProperty("file.separator")));
        // 如果路径不存在，创建路径
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fileOut = new FileOutputStream(path);
        workbook.write(fileOut);
        fileOut.close();
    }

    /**
     * 在输出流中导出excel
     *
     * @param excelName 导出的excel名称 包括扩展名
     * @param sheetName 导出的sheet名称
     * @param fieldName 列名数组
     * @param data      数据组
     * @param response
     * @throws IOException
     */
    public void makeStreamExcel(String excelName, String sheetName, String[] fieldName, List<Object[]> data, HttpServletResponse response) throws IOException {
        OutputStream os = null;
        response.reset(); // 清空输出流
        os = response.getOutputStream(); // 取得输出流
        response.setHeader("Content-disposition", "attachment; filename="
                + new String(excelName.getBytes(), "ISO-8859-1")); // 设定输出文件头
        response.setContentType("application/msexcel"); // 定义输出类型
        //在内存中生成工作薄
        HSSFWorkbook workbook = makeWorkBook(sheetName, fieldName, data);
        os.flush();
        workbook.write(os);
    }

    /**
     * 根据条件，生成工作薄对象到内存
     *
     * @param sheetName 工作表对象名称
     * @param fieldName 首列列名称
     * @param data      数据
     * @return
     */
    private HSSFWorkbook makeWorkBook(String sheetName, String[] fieldName, List<Object[]> data) {
        //用来记录最大列宽,自动调整列宽。
        Integer collength[] = new Integer[fieldName.length];
        // 产生工作薄对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 产生工作表对象
        HSSFSheet sheet = workbook.createSheet();
        // 为了工作表能支持中文,设置字符集为UTF_16
        workbook.setSheetName(0, sheetName);
        // 产生一行
        HSSFRow row = sheet.createRow(0);
        // 产生单元格
        HSSFCell cell;
        // 写入各个字段的名称
        for (int i = 0; i < fieldName.length; i++) {
            // 创建第一行各个字段名称的单元格
            cell = row.createCell((short) i);
            // 设置单元格内容为字符串型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            // 为了能在单元格中输入中文,设置字符集为UTF_16
            // cell.setEncoding(HSSFCell.ENCODING_UTF_16);
            // 给单元格内容赋值
            cell.setCellValue(new HSSFRichTextString(fieldName[i]));
            //初始化列宽
            collength[i] = fieldName[i].getBytes().length;
        }
        //临时单元格内容
        String tempCellContent = "";
        // 写入各条记录,每条记录对应excel表中的一行
        for (int i = 0; i < data.size(); i++) {
            Object[] tmp = data.get(i);
            // 生成一行
            row = sheet.createRow(i + 1);
            for (int j = 0; j < tmp.length; j++) {
                cell = row.createCell((short) j);
                //设置单元格字符类型为String
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                tempCellContent = (tmp[j] == null) ? "" : tmp[j].toString();
                cell.setCellValue(new HSSFRichTextString(tempCellContent));

                //如果自动调整列宽度。
                if (autoColumnWidth) {
                    if (j >= collength.length) {    // 标题列数小于数据列数时。
                        collength = CollectionUtil.addObjectToArray(collength, tempCellContent.getBytes().length);
                    } else {
                        //如果这个内容的宽度大于之前最大的，就按照这个设置宽度。
                        if (collength[j] < tempCellContent.getBytes().length) {
                            collength[j] = tempCellContent.getBytes().length;
                        }
                    }
                }
            }
        }
        //自动调整列宽度。
        if (autoColumnWidth) {
            //调整列为这列文字对应的最大宽度。
            for (int i = 0; i < fieldName.length; i++) {
                sheet.setColumnWidth(i, collength[i] * 2 * 256);
            }
        }
        return workbook;
    }

    /**
     * 创建一个简单Excel文件
     *
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
