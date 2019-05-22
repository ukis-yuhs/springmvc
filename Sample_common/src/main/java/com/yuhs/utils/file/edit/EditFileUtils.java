package com.yuhs.utils.file.edit;


import com.yuhs.utils.file.read.ReadFileUtils;

import java.io.*;

/**
 * Created by yuhaisheng on 2019/5/9.
 */
public class EditFileUtils {

    /**
     * 根据给出路径自动选择删除文件或整个文件夹
     * @param path :文件或文件夹路径
     * */
    public static void deleteFiles(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            // 删除文件
            file.delete();
        } else {
            File[] subFiles = file.listFiles();
            for (File subfile : subFiles) {
                // 删除当前目录下的子目录
                deleteFiles(subfile.getAbsolutePath());
            }
            // 删除当前目录
            file.delete();
        }
    }

    /**
     * 根据给出路径自动选择复制文件或整个文件夹
     * @param src :源文件或文件夹路径
     * @param dest :目标文件或文件夹路径
     * */
    public static void copyFiles(String src, String dest) {
        File srcFile = new File(src);
        if (srcFile.exists()) {
            if (srcFile.isFile()) {
                writeFileFromInputStream(ReadFileUtils.readFileToInputStream(src), dest);
            } else {
                File[] subFiles = srcFile.listFiles();
                if (subFiles.length == 0) {
                    File subDir = new File(dest);
                    subDir.mkdirs();
                } else {
                    for (File subFile : subFiles) {
                        String subDirPath = dest + System.getProperty("file.separator") + subFile.getName();
                        copyFiles(subFile.getAbsolutePath(), subDirPath);
                    }
                }
            }
        }
    }

    /**
     * 将文件输入流写入文件
     * */
    public static boolean writeFileFromInputStream(InputStream inStream, String path) {
        boolean result = true;
        try {
            File file = createFile(path);
            FileOutputStream out = new FileOutputStream(file);
            byte[] data = new byte[1024];
            int num = 0;
            while ((num = inStream.read(data, 0, data.length)) != -1) {
                out.write(data, 0, num);
            }
            out.close();
            data = null;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * 向文件中写入内容
     * @param filePath
     * @param filecontent
     * @return
     */
    public static boolean writeContent(String filePath, String filecontent) {
        boolean result = true;
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            writeFileContent(filePath, filecontent);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * 向文件中写入内容
     * @param filepath
     * @param newContent
     * @return
     * @throws IOException
     */
    public static boolean writeFileContent(String filepath, String newContent) throws IOException {
        Boolean bool = false;
        String filein = newContent + "\r\n";//新写入的行，换行
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);//文件路径(包括文件名称)
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();

            //文件原有内容
            for (int i = 0; (temp = br.readLine()) != null; i++) {
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }

    /**
     * 创建文件，若文件夹不存在则自动创建文件夹，若文件存在则删除旧文件
     * @param path :待创建文件路径
     * */
    public static File createFile(String path) {
        File file = new File(path);
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 创建文件，若文件夹不存在则自动创建文件夹，若文件存在则重命名
     * @param path
     * @return
     */
    public static File createFileGenerateFileName(String path) {
        File file = new File(path);
        String directory = file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf(System.getProperty("file.separator")));
        if (file.exists()) {
            String fileName = file.getName();
            String name = "";
            String extension = "";
            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex > 0) {
                name = fileName.substring(0, dotIndex);
                extension = fileName.substring(dotIndex);
            }
            int index = 0;
            while (file.exists()) {
                index++;
                fileName = name + '(' + index + ')' + extension;
                file = new File(directory, fileName);
            }
        }
        try {
            if (!file.createNewFile()) {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 获取文件名
     * @param filename
     * @return
     */
    public static String getName(String filename) {
        if (filename == null) {
            return null;
        }
        int index = filename.lastIndexOf('/');
        return filename.substring(index + 1);
    }

    /**
     * 根据byte数组，生成文件
     * @param bfile
     * @param filePath
     * @param fileName
     */
    public static void createFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bufferedOutputStream = null;
        FileOutputStream fileOutputStream = null;
        File file = null;
        try {
            File dir = new File(filePath);
            //判断文件目录是否存在
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fileOutputStream = new FileOutputStream(file);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != bufferedOutputStream) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fileOutputStream) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
