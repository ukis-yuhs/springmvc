package com.yuhs.utils.idCard;

import org.junit.Test;

import java.text.ParseException;

/**
 * Created by yuhaisheng on 2019/6/27.
 */
public class IdCardUtilsTest {

    /**
     * 生成行政区域Code
     */
    @Test
    public void testGenerateAreaCode() {
        Integer areaCode = IdCardUtils.generateAreaCode();
        System.out.println("areaCode = " + areaCode);
    }

    /**
     * 是否为日期
     */
    @Test
    public void testIsDate() {
        System.out.println(IdCardUtils.isDate(2100,2,29));
    }

    /**
     * 随机生日
     */
    @Test
    public void testGenerateBirthday() {
        for (int i = 0; i < 20; i++) {
            System.out.println("Index[ " + i + " ] = " + IdCardUtils.generateBirthday());
        }
    }

    /**
     * 随机身份证号码
     */
    @Test
    public void testGenerateIdCard() {
        for (int index = 0; index < 20; index++) {
            System.out.println("IdCard[ " + index + " ] = " + IdCardUtils.generateIdCard());
        }
    }

    /**
     * 获取权重
     */
    @Test
    public void testGetWeightedCode() {
        int[] wi = IdCardUtils.getWeightedCode();
        for (int i = 0; i < wi.length; i++) {
            if (i == 0 ) {
                System.out.print(wi[i]);
            } else {
                System.out.print("," + wi[i]);
            }
        }
    }

    /**
     * 验证身份证号码
     */
    @Test
    public void testIsIdCardNo() {
        System.out.println(IdCardUtils.isIdCardNo("110112196806111273"));
    }

    /**
     * 计算年龄（具体年月日）
     */
    @Test
    public void testAge() throws ParseException {
        System.out.println(IdCardUtils.age("110112680611127"));
        System.out.println(IdCardUtils.age("110112680628127"));
        System.out.println(IdCardUtils.age("110112680629127"));
        System.out.println(IdCardUtils.age("110112680701127"));
    }
}
