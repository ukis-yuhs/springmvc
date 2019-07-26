package com.yuhs.utils.file.convert;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import com.yuhs.utils.file.read.ReadFileUtils;

import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 依赖itext.jar
 * Created by yuhaisheng on 2019/7/4.
 */
public class ConvertImage2PdfUtils {

    /**
     * 图片转换位PDF文件
     *
     * @param imgFilePath
     * @param pdfFilePath
     * @return
     * @throws IOException
     */
    public static boolean img2Pdf(String imgFilePath, String pdfFilePath) throws IOException {
        Document document = new Document();
        FileOutputStream fos = null;
        try {
            //从URL读取图片
            //URL u = new URL(imgFilePath);
            fos = new FileOutputStream(pdfFilePath);
            PdfWriter.getInstance(document, fos);

            // 添加PDF文档的某些信息，比如作者，主题等等
            document.addAuthor("Tester");
            document.addSubject("Test PDF");

            // 设置文档的大小
            document.setPageSize(PageSize.A4);
            // 打开文档
            document.open();
            // 读取一个网络图片
            //Image image= Image.getInstance(u);
            // 从本地读取图片
            Image image= Image.getInstance(ReadFileUtils.getBytes(imgFilePath));

            // 缩放
            float imageHeight=image.getScaledHeight();
            float imageWidth=image.getScaledWidth();
            int i=0;
            while(imageHeight>500||imageWidth>500){
                image.scalePercent(100-i);
                i++;
                imageHeight=image.getScaledHeight();
                imageWidth=image.getScaledWidth();
                System.out.println("imageHeight->"+imageHeight);
                System.out.println("imageWidth->"+imageWidth);
            }

            image.setAlignment(Image.ALIGN_CENTER);
            // 插入一个图片
            document.add(image);
        } catch (DocumentException de) {
            System.out.println(de.getMessage());
            return false;
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            return false;
        }
        document.close();
        fos.flush();
        fos.close();
        return true;
    }
}
