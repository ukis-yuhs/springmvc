package com.yuhs.utils.zip;

import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/5/22.
 */
public class CompressStringUtilsTest {
    /**
     * 测试gzip和ungzip
     */
    @Test
    public void testGzip() {
        StringBuffer stringBuffer = new StringBuffer("");
        stringBuffer.append("1234567890123456789012345678901234567890123456789012345678901234567890");
        stringBuffer.append("ABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABC");
        stringBuffer.append("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
        String gzip = CompressStringUtils.gzip(stringBuffer.toString());
        System.out.println("gzip:\n" + gzip);
        String ungzip = CompressStringUtils.ungzip(gzip);
        System.out.println("ungzip:\n" + ungzip);
    }

    @Test
    public void testZip() {
        StringBuffer stringBuffer = new StringBuffer("");
        stringBuffer.append("1234567890123456789012345678901234567890123456789012345678901234567890");
        stringBuffer.append("ABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABC");
        stringBuffer.append("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
        String zip = CompressStringUtils.zip(stringBuffer.toString());
        System.out.println("zip:\n" + zip);
        String unzip = CompressStringUtils.unzip(zip);
        System.out.println("unzip:\n" + unzip);
    }
}
