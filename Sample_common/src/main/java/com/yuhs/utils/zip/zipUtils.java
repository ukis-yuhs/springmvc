package com.yuhs.utils.zip;

import com.yuhs.utils.collection.CollectionUtil;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 压缩工具类
 * 依赖:commons-compress-xxx.jar
 * Created by yuhaisheng on 2019/5/17.
 */
public class ZipUtils {
    private static final int BUFF_SIZE = 4 * 1024;

    /**
     * 把N多文件或文件夹压缩成zip
     *
     * @param files   需要压缩的文件或文件夹
     * @param zipFile 压缩后的zip文件
     * @throws IOException
     */
    public static void compress(File[] files, File zipFile) throws IOException {
        if (CollectionUtil.isEmpty(files)) {
            return;
        }
        ZipArchiveOutputStream out = null;
        try {
            out = new ZipArchiveOutputStream(zipFile);
            out.setUseZip64(Zip64Mode.AsNeeded);
            //将每个文件用ZipArchiveEntry封装
            for (File file : files) {
                if (file == null) {
                    continue;
                }
                compressOneFile(file, out, "");
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 压缩文件
     *
     * @param srcFile  源文件
     * @param destFile 压缩后的文件
     * @throws IOException
     */
    public static void compress(File srcFile, File destFile) throws IOException {
        ZipArchiveOutputStream out = null;
        try {
            out = new ZipArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(destFile), 1024));
            compressOneFile(srcFile, out, "");
        } finally {
            out.close();
        }
    }

    /**
     * 压缩单个文件,非文件夹
     *
     * @param srcFile 源文件，不能是文件夹
     * @param out     压缩文件的输出流
     * @param dir     在压缩包中的位置,根目录传入/
     * @throws IOException
     */
    private static void compressOneFile(File srcFile, ZipArchiveOutputStream out, String dir) throws IOException {
        if (srcFile.isDirectory()) {//对文件夹进行处理。
            ZipArchiveEntry entry = new ZipArchiveEntry(dir + srcFile.getName() + "/");
            out.putArchiveEntry(entry);
            out.closeArchiveEntry();
            //循环文件夹中的所有文件进行压缩处理。
            String[] subFiles = srcFile.list();
            for (String subFile : subFiles) {
                compressOneFile(new File(srcFile.getPath() + "/" + subFile), out, (dir + srcFile.getName() + "/"));
            }
        } else { //普通文件。
            InputStream is = null;
            try {
                is = new BufferedInputStream(new FileInputStream(srcFile));
                //创建一个压缩包。
                ZipArchiveEntry entry = new ZipArchiveEntry(srcFile, dir + srcFile.getName());
                out.putArchiveEntry(entry);
                IOUtils.copy(is, out);
                out.closeArchiveEntry();
            } finally {
                if (is != null)
                    is.close();
            }
        }
    }

    /**
     * 解压缩zip压缩包下的所有文件
     *
     * @param zipFile zip压缩文件
     * @param dir     解压缩到这个路径下
     * @throws IOException
     */
    public void decompressZip(File zipFile, String dir) throws IOException {
        org.apache.commons.compress.archivers.zip.ZipFile zf
                = new org.apache.commons.compress.archivers.zip.ZipFile(zipFile);
        try {
            for (Enumeration<ZipArchiveEntry> entries = zf.getEntries();
                 entries.hasMoreElements(); ) {
                ZipArchiveEntry ze = entries.nextElement();
                //不存在则创建目标文件夹。
                File targetFile = new File(dir, ze.getName());
                //遇到根目录时跳过。
                if (ze.getName().lastIndexOf("/") == (ze.getName().length() - 1)) {
                    continue;
                }
                //如果文件夹不存在，创建文件夹。
                if (!targetFile.getParentFile().exists()) {
                    targetFile.getParentFile().mkdirs();
                }

                InputStream i = zf.getInputStream(ze);
                OutputStream o = null;
                try {
                    o = new FileOutputStream(targetFile);
                    IOUtils.copy(i, o);
                } finally {
                    if (i != null) {
                        i.close();
                    }
                    if (o != null) {
                        o.close();
                    }
                }
            }
        } finally {
            zf.close();
        }
    }

    /**
     * 解压缩zip压缩包下的某个文件信息
     *
     * @param zipFile  zip压缩文件
     * @param fileName 某个文件名,例如abc.zip下面的a.jpg，需要传入/abc/a.jpg
     * @param dir      解压缩到这个路径下
     * @throws IOException
     */
    public void decompressZip(File zipFile, String fileName, String dir) throws IOException {
        //不存在则创建目标文件夹。
        File targetFile = new File(dir, fileName);
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }

        org.apache.commons.compress.archivers.zip.ZipFile zf
                = new org.apache.commons.compress.archivers.zip.ZipFile(zipFile);
        Enumeration<ZipArchiveEntry> zips = zf.getEntries();
        ZipArchiveEntry zip = null;
        while (zips.hasMoreElements()) {
            zip = zips.nextElement();
            if (fileName.equals(zip.getName())) {
                OutputStream o = null;
                InputStream i = zf.getInputStream(zip);
                try {
                    o = new FileOutputStream(targetFile);
                    IOUtils.copy(i, o);
                } finally {
                    if (i != null) {
                        i.close();
                    }
                    if (o != null) {
                        o.close();
                    }
                }
            }
        }
    }

    /**
     * 得到zip压缩包下的某个文件信息,只能在根目录下查找
     *
     * @param zipFile  zip压缩文件
     * @param fileName 某个文件名,例如abc.zip下面的a.jpg，需要传入/abc/a.jpg
     * @return 压缩文件中的这个文件, 没有找到返回null
     * @throws IOException
     */
    public ZipArchiveEntry readZip(File zipFile, String fileName) throws IOException {
        org.apache.commons.compress.archivers.zip.ZipFile zf
                = new org.apache.commons.compress.archivers.zip.ZipFile(zipFile);
        Enumeration<ZipArchiveEntry> zips = zf.getEntries();
        ZipArchiveEntry zip = null;
        while (zips.hasMoreElements()) {
            zip = zips.nextElement();
            if (fileName.equals(zip.getName())) {
                return zip;
            }
        }
        return null;
    }

    /**
     * 得到zip压缩包下的所有文件信息
     *
     * @param zipFile zip压缩文件
     * @return 压缩文件中的文件枚举
     * @throws IOException
     */
    public Enumeration<ZipArchiveEntry> readZip(File zipFile) throws IOException {
        org.apache.commons.compress.archivers.zip.ZipFile zf
                = new org.apache.commons.compress.archivers.zip.ZipFile(zipFile);
        Enumeration<ZipArchiveEntry> zips = zf.getEntries();
        return zips;
    }

    /**
     * 解压
     *
     * @param zipFilePath 压缩文件
     * @param unzipPath   解压路径
     * @return return true if success
     */
    public static boolean unzip(String zipFilePath, String unzipPath) {
        try {
            ZipFile zipFile = new ZipFile(zipFilePath);
            Enumeration emu = zipFile.entries();
            int i = 0;
            while (emu.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) emu.nextElement();
                if (entry.isDirectory()) {
                    new File(unzipPath + "/" + entry.getName()).mkdirs();
                    continue;
                }

                BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));
                File file = new File(unzipPath + "/" + entry.getName());
                File parent = file.getParentFile();
                if (parent != null && (!parent.exists())) {
                    parent.mkdirs();
                }

                FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos);

                int count;
                byte data[] = new byte[BUFF_SIZE];
                while ((count = bis.read(data, 0, BUFF_SIZE)) != -1) {
                    bos.write(data, 0, count);
                }
                bos.flush();
                bos.close();
                bis.close();
            }
            zipFile.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
