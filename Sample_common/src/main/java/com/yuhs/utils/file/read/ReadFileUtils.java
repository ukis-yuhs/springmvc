package com.yuhs.utils.file.read;

import org.apache.http.util.ByteArrayBuffer;

import java.io.*;

/**
 * Created by yuhaisheng on 2019/4/23.
 */
public class ReadFileUtils {

    /**
     * 获取文件输入流
     * */
    public static InputStream readFileToInputStream(String path) {
        InputStream inputStream = null;
        try {
            File file = new File(path);
            inputStream = new FileInputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    /**
     * 读取文件字节数组
     * */
    public static byte[] readFileToBytes(String path) {
        InputStream inputStream = readFileToInputStream(path);
        if (inputStream != null) {
            byte[] data = new byte[1024];
            ByteArrayBuffer buffer = new ByteArrayBuffer(1024);
            int n = 0;
            try {
                while ((n = inputStream.read(data)) != -1) {
                    buffer.append(data, 0, n);
                }
                data = null;
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return buffer.toByteArray();
        }
        return null;
    }

    /**
     * 读取文件内容
     * */
    public static String readFileToString(String path) {
        byte[] dataBytes = readFileToBytes(path);
        if (dataBytes != null) {
            return new String(dataBytes);
        }
        return null;
    }


//独立方法完成文件读取 开始
    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     * @param path
     * @return
     */
    public static String readFileByLines(String path) {
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String tempString = null;
            while ((tempString = bufferedReader.readLine()) != null) {
                buffer.append(tempString).append(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer.toString();
    }
    /**
     * 读取本地文件(按行读取)
     * @param filePath
     * @return
     */
    public static String readLocalFileByLine(String filePath) {
        File file = new File(filePath);
        String content = new String();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer buffer = new StringBuffer();
            // 用来保存每行读取的内容
            String line;
            // 读取第一行
            line = reader.readLine();
            // 如果 line 为空说明读完了
            while (line != null) {
                // 将读到的内容添加到 buffer 中
                buffer.append(line);
                // 添加换行符
                buffer.append(" ");
                // 读取下一行
                line = reader.readLine();
            }
            content = buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return content;
    }

    /**
     * 获得指定文件的byte数组, 一次读取文件内容
     * @param filePath
     * @return
     */
    public static byte[] readBytesFromFile(String filePath) {
        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;
        try {
            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];
            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytesArray;
    }
    /**
     * 获得指定文件的byte数组
     * @param filePath
     * @return
     */
    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            File file = new File(filePath);
            fileInputStream = new FileInputStream(file);
            byteArrayOutputStream = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fileInputStream.read(b)) != -1) {
                byteArrayOutputStream.write(b, 0, n);
            }
            buffer = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (null != byteArrayOutputStream) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return buffer;
    }
//独立方法完成文件读取 结束
}
