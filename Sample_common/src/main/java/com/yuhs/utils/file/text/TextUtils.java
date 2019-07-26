package com.yuhs.utils.file.text;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * 文本操作
 * 可调用setCharEncode设置读写文件编码，若不设置则采用utf-8
 * Created by yuhaisheng on 2019/6/10.
 */
public class TextUtils {
    /**
     * txt文件路径
     */
    private String filePath;
    /**
     * 系统属性
     */
    private Properties props = System.getProperties();
    /**
     * 字符编码
     */
    private String charEncode = "utf-8";

    /**
     * 构造函数
     *
     * @param filePath 文本文件路径
     */
    public TextUtils(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 构造函数
     *
     * @param file 文本文件
     */
    public TextUtils(File file) {
        this.filePath = file.getPath();
    }

    /**
     * 在文本最后追加一个换行符
     *
     * @throws IOException
     */
    public void appendLineSeparator() throws IOException {
        append(props.getProperty("line.separator"));
    }

    /**
     * 将字符串追加在文本最后
     *
     * @param target 追加的文本内容
     * @throws IOException
     */
    public void append(String target) throws IOException {
        BufferedWriter bf = null;
        try {
            //创建字符输出流对象
            // boolean append:true代表末尾追加
            bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.filePath, true), charEncode));
            //创建缓冲字符输出流对象,将符合记录的文件追加到新文本中。
            bf.append(target);
            bf.flush();
        } catch (IOException e) {
            throw e;
        } finally {
            if (bf != null) {
                bf.close();
            }
        }
    }

    /**
     * 得到这个文本中，不包含任一个关键字的行。即只要一行中包含其中一个关键字则不符合要求
     * 将结果集保存到newfile这个文本上，如果文本不存在创建文本，文本存在将追加在文本最后
     *
     * @param keys    需要查找的key
     * @param newFile 结果集保存的文本对象
     * @throws IOException
     */
    public void findLineByNotKey(String[] keys, File newFile) throws IOException {
        //结果存放的文本。
        if (newFile.exists()) {
            newFile.mkdirs();
        }

        //用来写文件。
        FileWriter fw = null;
        //创建字符输出流对象
        BufferedWriter bf = null;
        BufferedReader br = null;// 建立BufferedReader对象，并实例化为br

        try {
            //用来写文件。
            fw = new FileWriter(newFile, true);
            //创建字符输出流对象
            bf = new BufferedWriter(fw);

            br = new BufferedReader(new InputStreamReader(new FileInputStream(this.filePath), charEncode));// 建立BufferedReader对象，并实例化为br
            String line = br.readLine();// 从文件读取一行字符串
            //遍历每一行。
            while (line != null) {
                //本行是否含有任一个key
                boolean haveAllKey = false;
                for (String key : keys) {
                    if (line.indexOf(key) != -1) {
                        haveAllKey = true;
                        break;
                    }
                }
                //key都不包含，追加到文本中去。
                if (!haveAllKey) {
                    //创建缓冲字符输出流对象,将符合记录的文件追加到新文本中,注意加上换行符。
                    bf.append(line + props.getProperty("line.separator"));
                    bf.flush();
                }
                line = br.readLine();// 从文件中继续读取一行数据
            }
        } catch (IOException e) {
            throw e;
        } finally {
            //关闭结果文件的写入流。
            if (bf != null)
                bf.close();
            // 关闭BufferedReader对象
            if (br != null)
                br.close();
            if (fw != null)
                fw.close();
        }
    }

    /**
     * 得到这个文本中，不包含任一个关键字的行。即只要一行中包含其中一个关键字则不符合要求，文件过大时不要使用
     *
     * @param keys 需要查找的key
     * @return
     * @throws IOException
     */
    public List<String> findLineByNotKey(String[] keys) throws IOException {
        //查找出来的合适的结果集。
        List<String> resultList = new LinkedList<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.filePath), charEncode));// 建立BufferedReader对象，并实例化为br
        String line = br.readLine();// 从文件读取一行字符串
        //遍历每一行。
        while (line != null) {
            //本行是否含有任一key
            boolean haveAllKey = false;
            for (String key : keys) {
                if (line.indexOf(key) != -1) {
                    haveAllKey = true;
                    break;
                }
            }
            //本行不含有任一个key,记录下来。
            if (!haveAllKey) {
                resultList.add(line);
            }
            line = br.readLine();// 从文件中继续读取一行数据
        }
        br.close();// 关闭BufferedReader对象
        return resultList;
    }

    /**
     * 得到这个文本中，包含任一个关键字的行。即只要一行中只包含其中一个关键字则符合要求
     * 将结果集保存到newfile这个文本上，如果文本不存在创建文本，文本存在将追加在文本最后
     *
     * @param keys    需要查找的key
     * @param newFile 结果集保存的文本对象
     * @throws IOException
     */
    public void findLineByKeyOr(String[] keys, File newFile) throws IOException {
        //结果存放的文本。
        if (newFile.exists()) {
            newFile.mkdirs();
        }
        //用来写文件。
        FileWriter fw = new FileWriter(newFile, true);
        //创建字符输出流对象
        BufferedWriter bf = new BufferedWriter(fw);

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.filePath), charEncode));// 建立BufferedReader对象，并实例化为br
        String line = br.readLine();// 从文件读取一行字符串
        //遍历每一行。
        while (line != null) {
            //本行是否含有任一个key
            boolean haveAllKey = false;
            for (String key : keys) {
                if (line.indexOf(key) != -1) {
                    haveAllKey = true;
                    break;
                }
            }
            //任一个key包含，追加到文本中去。
            if (haveAllKey) {
                //创建缓冲字符输出流对象,将符合记录的文件追加到新文本中,注意加上换行符。
                bf.append(line + props.getProperty("line.separator"));
                bf.flush();
            }
            line = br.readLine();// 从文件中继续读取一行数据
        }
        //关闭结果文件的写入流。
        bf.close();
        br.close();// 关闭BufferedReader对象
    }

    /**
     * 得到这个文本中，包含任一个关键字的行。即只要一行中只包含其中一个关键字则符合要求，文件过大时不要使用
     *
     * @param keys 需要查找的key
     * @return
     * @throws IOException
     */
    public List<String> findLineByKeyOr(String[] keys) throws IOException {
        //查找出来的合适的结果集。
        List<String> resultList = new LinkedList<String>();
        File file = new File(this.filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.filePath), charEncode));// 建立BufferedReader对象，并实例化为br
        String line = br.readLine();// 从文件读取一行字符串
        //遍历每一行。
        while (line != null) {
            //本行是否含有任一key
            boolean haveAllKey = false;
            for (String key : keys) {
                if (line.indexOf(key) != -1) {
                    haveAllKey = true;
                    break;
                }
            }
            //本行是否含有任一个key,记录下来。
            if (haveAllKey) {
                resultList.add(line);
            }
            line = br.readLine();// 从文件中继续读取一行数据
        }
        br.close();// 关闭BufferedReader对象
        return resultList;
    }

    /**
     * 得到这个文本中，包含全部关键字的行。如果一行中只包含部分关键字则此行不符合要求
     * 将结果集保存到newfile这个文本上，如果文本不存在创建文本，文本存在将追加在文本最后
     *
     * @param keys    需要查找的key
     * @param newFile 结果集保存的文本对象
     * @throws IOException
     */
    public void findLineByKeyAnd(String[] keys, File newFile) throws IOException {
        //结果存放的文本。
        if (newFile.exists()) {
            newFile.mkdirs();
        }
        //用来写文件。
        FileWriter fw = new FileWriter(newFile, true);
        //创建字符输出流对象
        BufferedWriter bf = new BufferedWriter(fw);

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.filePath), charEncode));// 建立BufferedReader对象，并实例化为br
        String line = br.readLine();// 从文件读取一行字符串
        //遍历每一行。
        while (line != null) {
            //本行是否含有这些key
            boolean haveAllKey = true;
            for (String key : keys) {
                if (line.indexOf(key) == -1) {
                    haveAllKey = false;
                    break;
                }
            }
            //所有的key都包含，追加到文本中去。
            if (haveAllKey) {
                //创建缓冲字符输出流对象,将符合记录的文件追加到新文本中,注意加上换行符。
                bf.append(line + props.getProperty("line.separator"));
                bf.flush();
            }
            line = br.readLine();// 从文件中继续读取一行数据
        }
        //关闭结果文件的写入流。
        bf.close();

        br.close();// 关闭BufferedReader对象
    }

    /**
     * 得到这个文本中，包含全部关键字的行。如果一行中只包含部分关键字则此行不符合要求，文件过大时不要使用
     *
     * @param keys 需要查找的key
     * @return
     * @throws IOException
     */
    public List<String> findLineByKeyAnd(String[] keys) throws IOException {
        //查找出来的合适的结果集。
        List<String> resultList = new LinkedList<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.filePath), charEncode));// 建立BufferedReader对象，并实例化为br
        String line = br.readLine();// 从文件读取一行字符串
        //遍历每一行。
        while (line != null) {
            //本行是否含有这些key
            boolean haveAllKey = true;
            for (String key : keys) {
                if (line.indexOf(key) == -1) {
                    haveAllKey = false;
                    break;
                }
            }
            //所有的key都包含，记录下来。
            if (haveAllKey) {
                resultList.add(line);
            }
            line = br.readLine();// 从文件中继续读取一行数据
        }
        br.close();// 关闭BufferedReader对象
        return resultList;
    }

    /**
     * 得到文件的行数
     *
     * @return
     * @throws IOException
     */
    public int fileLinesNum() throws IOException {
        int num = 0;    //记录行数
        // 建立BufferedReader对象，并实例化为br
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.filePath), charEncode));
        String line = br.readLine();// 从文件读取一行字符串
        // 判断读取到的字符串是否不为空
        while (line != null) {
            num++;
            line = br.readLine();// 从文件中继续读取一行数据
        }
        br.close();// 关闭BufferedReader对象
        return num;
    }

    /**
     * 得到这个txt所有的行的数据
     * <pre>
     * TextUtil tu=new TextUtil("d:/test.txt");
     * BufferedReader br=tu.fileBufferedReader();
     * // 从文件读取一行字符串
     * String Line = br.readLine();
     * // 判断读取到的字符串是否不为空
     * while (Line != null) {
     *     //Line 即为每一行。
     *     System.out.println(Line);
     *     Line = br.readLine();
     * }
     * br.close();
     * </pre>
     *
     * @return
     * @throws IOException
     */
    public BufferedReader fileBufferedReader() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.filePath), charEncode));// 建立BufferedReader对象，并实例化为br
//        br.close();// 关闭BufferedReader对象
//        fr.close();// 关闭文件
        return br;
    }

    /**
     * 得到这个txt所有的行的数据，文件过大时不要使用
     *
     * @return
     * @throws IOException
     */
    public List<String> fileLinesContent() throws IOException {
        List<String> strs = new LinkedList<String>();
        // 建立BufferedReader对象，并实例化为br
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.filePath), charEncode));
        String line = br.readLine();// 从文件读取一行字符串
        // 判断读取到的字符串是否不为空
        while (line != null) {
            strs.add(line);
            line = br.readLine();// 从文件中继续读取一行数据
        }
        br.close();// 关闭BufferedReader对象
        return strs;
    }

    /**
     * 将文本中的内容全部进行替换。(UTF-8写入)
     *
     * @param traget 替换的内容
     * @throws IOException
     */
    public void replaceAllWords(String traget) throws IOException {
        File file = new File(this.filePath);
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        output.write(traget);
        // 关闭BufferedReader对象
        output.close();
    }

    /**
     * 返回这个文本的所有内容.文件过大时不要使用
     *
     * @param lineSplit 每行记录之间的分隔符
     * @return
     * @throws IOException
     */
    public String allToString(String lineSplit) throws IOException {
        List<String> strsList = fileLinesContent();
        StringBuffer sb = new StringBuffer();
        int lineNum = 0; //当前读取到了第几行。
        for (String str : strsList) {
            sb.append(str);
            //不是最后一行加上分隔符。
            if (lineNum != strsList.size() - 1) {
                sb.append(lineSplit);
            }
            //行标加1
            lineNum++;
        }
        return sb.toString();
    }

    /**
     * 设置编码。不设置采用Utf-8编码
     *
     * @param charEncode 编码 如：utf-8
     */
    public void setCharEncode(String charEncode) {
        this.charEncode = charEncode;
    }
}
