package com.yuhs.utils.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by yuhaisheng on 2019/6/24.
 */
public class ZipUtil {
    private ZipUtil() {
    }

    /**
     * 压缩srcFile为zipFile
     *
     * @param srcFile 源文件路径
     * @param zipFile zip文件路径
     * @throws IOException
     */
    public static void doCompress(String srcFile, String zipFile) throws IOException {
        doCompress(new File(srcFile), new File(zipFile));
    }

    /**
     * 压缩srcFile为zipFile
     *
     * @param srcFile 源文件
     * @param zipFile zip文件
     * @throws IOException
     */
    public static void doCompress(File srcFile, File zipFile) throws IOException {
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(zipFile));
            doCompress(srcFile, out);
        } catch (IOException ex) {
            throw ex;
        } finally {
            out.close();//记得关闭资源
        }
    }

    /**
     * 压缩filelName
     *
     * @param filelName 要压缩文件路径
     * @param out       zip输出流
     * @throws IOException
     */
    public static void doCompress(String filelName, ZipOutputStream out) throws IOException {
        doCompress(new File(filelName), out);
    }

    /**
     * 压缩file
     *
     * @param file 要压缩文件
     * @param out  zip输出流
     * @throws IOException
     */
    public static void doCompress(File file, ZipOutputStream out) throws IOException {
        doCompress(file, out, "");
    }

    /**
     * 压缩文件或目录
     *
     * @param inFile 输入文件或目录
     * @param out    zip输出流
     * @param dir    输出目录
     * @throws IOException
     */
    public static void doCompress(File inFile, ZipOutputStream out, String dir) throws IOException {
        if (inFile.isDirectory()) {
            File[] files = inFile.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    String name = inFile.getName();
                    if (!"".equals(dir)) {
                        name = dir + "/" + name;
                    }
                    doCompress(file, out, name);
                }
            }
        } else {
            doZip(inFile, out, dir);
        }
    }

    /**
     * 压缩文件
     *
     * @param inFile 输入文件
     * @param out    zip输出流
     * @param dir    输出目录
     * @throws IOException
     */
    public static void doZip(File inFile, ZipOutputStream out, String dir) throws IOException {
        String entryName = null;
        if (!"".equals(dir)) {
            entryName = dir + "/" + inFile.getName();
        } else {
            entryName = inFile.getName();
        }
        ZipEntry entry = new ZipEntry(entryName);
        out.putNextEntry(entry);

        int len = 0;
        byte[] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(inFile);
        while ((len = fis.read(buffer)) > 0) {
            out.write(buffer, 0, len);
            out.flush();
        }
        out.closeEntry();
        fis.close();
    }
}
