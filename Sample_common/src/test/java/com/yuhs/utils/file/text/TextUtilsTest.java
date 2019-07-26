package com.yuhs.utils.file.text;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by yuhaisheng on 2019/6/11.
 */
public class TextUtilsTest {
    private String textFilePath = "";
    private String newFilePaht = "";
    @Before
    public void before() {
        textFilePath = "/Users/yuhaisheng/Documents/ideaProjects/springmvc/Sample_common/src/test/java/com/yuhs/utils/file/text/TestFile.txt";
        newFilePaht = "/Users/yuhaisheng/Documents/ideaProjects/springmvc/Sample_common/src/test/java/com/yuhs/utils/file/text/NewFile.txt";
    }

    @Test
    public void testTestFile() {
        TextUtils textUtils = new TextUtils(textFilePath);
        try {
            /**  在文本最后追加一个换行符 */
            textUtils.appendLineSeparator();
            /** 将字符串追加在文本最后 */
            textUtils.append("KKK");
            /** 取得行号 */
            System.out.println(textUtils.fileLinesNum());

            /** 查找包含指定关键字行 非 */
//            String [] strArr = {"123"};
//            textUtils.findLineByNotKey(strArr, new File(newFilePaht));
//            List<String> list = textUtils.findLineByNotKey(strArr);
//            for (String item : list) {
//                System.out.println(item);
//            }
            /** 查找包含指定关键字行 或 */
            String [] strArr2 = {"123","ABC"};
//            textUtils.findLineByKeyOr(strArr2, new File(newFilePaht));
//            List<String> list2 = textUtils.findLineByKeyOr(strArr2);
//            for (String item : list2) {
//                System.out.println(item);
//            }

            /** 查找包含指定关键字行 与 */
            textUtils.findLineByKeyAnd(strArr2, new File(newFilePaht));
//            List<String> list3 = textUtils.findLineByKeyAnd(strArr2);
//            for (String item : list3) {
//                System.out.println(item);
//            }

            /** 获取所有行文本 */
//            List<String> list4 = textUtils.fileLinesContent();
//            for (String item : list4) {
//                System.out.println(item);
//            }

            /** 取得所有文本内容 */
            String context = textUtils.allToString(System.getProperties().getProperty("line.separator"));
            System.out.println(context);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
