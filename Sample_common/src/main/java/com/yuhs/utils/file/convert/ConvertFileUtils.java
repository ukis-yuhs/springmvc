package com.yuhs.utils.file.convert;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * Created by yuhaisheng on 2019/5/8.
 */
public class ConvertFileUtils {

    /**
     * docx转换为HTML文件
     * @param docxFileName
     * @param htmlFilePath
     * @return
     * @throws Exception
     */
    public static String docxToHtmlFile(String docxFileName, String htmlFilePath) throws Exception {
        String htmlFileName = docxFileName.substring(docxFileName.lastIndexOf("/"),docxFileName.indexOf("."))+".html";
        //导出文件html路径
        if (htmlFilePath != null && htmlFilePath != "") {
            htmlFilePath = htmlFilePath + "/" + htmlFileName;
        } else {
            htmlFilePath = docxFileName.substring(0,docxFileName.indexOf("."))+".html";
        }
        XWPFDocument document = new XWPFDocument(new FileInputStream(docxFileName));
        XHTMLOptions options = XHTMLOptions.create().indent(4);

        File outFile = new File(htmlFilePath);
        outFile.getParentFile().mkdirs();
        OutputStream out = new FileOutputStream(outFile);
        XHTMLConverter.getInstance().convert(document,out, options);
        return htmlFilePath;
    }

    /**
     * docx转换为HTML文本
     * @param docxFileName
     * @return
     * @throws Exception
     */
    public static String docxToHtmlString(String docxFileName) throws Exception {
        XWPFDocument document = new XWPFDocument(new FileInputStream(docxFileName));
        XHTMLOptions options = XHTMLOptions.create().indent(4);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XHTMLConverter.getInstance().convert(document, baos, options);
        baos.close();
        String content =new String(baos.toByteArray(),"utf-8");
        return content;
    }

    /**
     * docx转换为适合UEditorHTML文本
     * @param docxFileName
     * @return
     * @throws Exception
     */
    public static String docxToHtmlForUEditorString(String docxFileName) throws Exception {
        String htmlContent = docxToHtmlString(docxFileName);
        //替换UEditor无法识别的转义字符
        return htmlContent.replaceAll("&ldquo;","\"").replaceAll("&rdquo;","\"").replaceAll("&mdash;","-");
    }

    /**
     * doc转换为HTML文件
     * @param docFileName
     * @param htmlFilePath
     * @return
     * @throws TransformerException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static String docToHtmlFile(String docFileName, String htmlFilePath) throws TransformerException, IOException,ParserConfigurationException {
        String htmlFileName = docFileName.substring(docFileName.lastIndexOf("/"),docFileName.indexOf("."))+".html";
        //导出文件html路径
        if (htmlFilePath != null && htmlFilePath != "") {
            htmlFilePath = htmlFilePath + "/" + htmlFileName;
        } else {
            htmlFilePath = docFileName.substring(0,docFileName.indexOf("."))+".html";
        }

        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(docFileName));
        //兼容2007 以上版本
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        wordToHtmlConverter.processDocument(wordDocument);
        //解析html
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(out);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "HTML");
        serializer.transform(domSource, streamResult);
        //html文件输出
        FileOutputStream fileOutputStream = new FileOutputStream(htmlFilePath);
        out.writeTo(fileOutputStream);
        out.close();
        fileOutputStream.close();

        return htmlFilePath;
    }

    /**
     * doc转换为HTML
     * @param docFileName
     * @return
     * @throws TransformerException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static String docToHtmlString(String docFileName) throws TransformerException, IOException,ParserConfigurationException {

        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(docFileName));
        //兼容2007 以上版本
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        wordToHtmlConverter.processDocument(wordDocument);
        //解析html
        Document htmlDocument = wordToHtmlConverter.getDocument();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(out);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "HTML");
        serializer.transform(domSource, streamResult);
        out.close();

        String htmlContent=new String(out.toByteArray(), "utf-8");
        return htmlContent;
    }

    /**
     * doc转换为适合UEditorHTML文本
     * @param docFileName
     * @return
     * @throws TransformerException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static String docToHtmlForUEditorString(String docFileName) throws TransformerException, IOException,ParserConfigurationException {
        String htmlContent = docToHtmlString(docFileName);
        //替换UEditor无法识别的转义字符
        return htmlContent.replaceAll("&ldquo;","\"").replaceAll("&rdquo;","\"").replaceAll("&mdash;","-");
    }

    //TODO 待补充有image的文件
}
