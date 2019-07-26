package com.yuhs.utils.image;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.*;

/**
 *
 * Created by yuhaisheng on 2019/6/11.
 */
public class ImageUtilsTest {
    private String basepath = "";
    private String imgFilePath = "";
    private String decodeFile1 = "";
    private String decodeFile2 = "";
    private String newScale1ImgFilePath = "";
    private String newScale2ImgFilePath = "";
    private String newScale3ImgFilePath = "";

    private String newCut1 = "";
    private String newCut2 = "";
    private String newCut3 = "";

    private String convertFile = "";
    private String grayFile = "";

    private String pressText1 = "";
    private String pressText2 = "";

    private String pressImage = "";

    @Before
    public void before() {
        basepath = "/Users/yuhaisheng/Documents/ideaProjects/springmvc/Sample_common/src/test/java/com/yuhs/utils/image/";
        imgFilePath = basepath + "sky.jpeg";
        newScale1ImgFilePath = basepath + "new_scale_1_sky.jpeg";
        newScale2ImgFilePath = basepath + "new_scale_2_sky.jpeg";
        newScale3ImgFilePath = basepath + "new_scale_3_sky.jpeg";
        decodeFile1 = basepath + "decode1.jpeg";
        decodeFile2 = basepath + "decode2.jpeg";

        newCut1 = basepath + "newCut1.jpeg";
        newCut2 = basepath + "newCut2.jpeg";
        newCut3 = basepath + "newCut3.jpeg";

        convertFile = basepath + "convertFile.png";
        grayFile = basepath + "grayFile.jpeg";

        pressText1 = basepath + "pressText1.jpeg";
        pressText2 = basepath + "pressText2.jpeg";

        pressImage = basepath + "pressImage.jpeg";
    }

    /**
     * 加密
     */
    @Test
    public void testBase64Encode() {
        String encode = ImageUtils.base64Encode(imgFilePath);
        System.out.println(encode);
    }

    /**
     * 解密
     */
    @Test
    public void testBase64DecodeForBoolean() {
        String encode = ImageUtils.base64Encode(imgFilePath);
        ImageUtils.base64Decode(encode, decodeFile1);
    }

    /**
     * 解密
     */
    @Test
    public void testBase64DecodeForByte() {
        String encode = ImageUtils.base64Encode(imgFilePath);
        byte[] bytes = ImageUtils.base64Decode(encode);
        try {
            OutputStream os = new FileOutputStream(decodeFile2);
            os.write(bytes, 0, bytes.length);
            os.flush();
            os.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * 缩放图片
     */
    @Test
    public void testScale() {
        ImageUtils.scale(imgFilePath, newScale1ImgFilePath, 2, false);
    }

    /**
     * 缩放图片
     */
    @Test
    public void testScale2() {
        File file = new File(imgFilePath);
        try {
            byte[] bytes = ImageUtils.scale2(new FileInputStream(file), 500, 300, true);
            OutputStream os = new FileOutputStream(newScale2ImgFilePath);
            os.write(bytes, 0, bytes.length);
            os.flush();
            os.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 缩放
     */
    @Test
    public void testScale3() {
        ImageUtils.scale3(imgFilePath, newScale3ImgFilePath, 500, 300, true);
    }

    /**
     * 切图
     */
    @Test
    public void testCut() {
        ImageUtils.cut(imgFilePath, newCut1,200,300,400,400);
    }

    /**
     * 切图
     */
    @Test
   public void testCut2() {
        ImageUtils.cut2(newCut1,newCut2,2,2);

    }

    /**
     * 切图
     */
    @Test
    public void testCut3() {
        ImageUtils.cut3(newCut1,newCut3,150,200);
    }

    /**
     * 格式转换
     */
    @Test
    public void testConvert() {
        ImageUtils.convert(newCut1, ImageUtils.IMAGE_TYPE_PNG, convertFile);
    }

    /**
     * 黑白图
     */
    @Test
    public void testGray() {
        ImageUtils.gray(newCut1,grayFile);
    }

    /**
     * 文字水印
     */
    @Test
    public void testPressText() {
        ImageUtils.pressText("掌柜",newCut1, pressText1,"宋体",Font.BOLD, Color.white,40,100,150,0.5f);
    }

    /**
     * 文字水印
     */
    @Test
    public void testPressText2() {
        ImageUtils.pressText2("掌柜",newCut1, pressText2,"黑体",36, Color.red,40,100,150,0.5f);
    }

    /**
     * 图片水印
     */
    @Test
    public void testPressImage() {
        ImageUtils.pressImage(newCut1,imgFilePath,pressImage,200,400,0.5f);
    }

    /**
     * 获取文字长度
     */
    @Test
    public void testGetLength() {
        int len = ImageUtils.getLength("掌柜");
        System.out.println(len);
    }
}
