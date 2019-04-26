package com.yuhs.utils.secure;

import org.junit.Test;

import java.util.UUID;

import static com.yuhs.utils.secure.MD5Utils.getMd5Sum;

/**
 * Created by yuhaisheng on 2019/4/25.
 */
public class MD5UtilsTest {

    String pwd = UUID.randomUUID().toString();
    String salt = "654321";

    @Test
    public void testGetMd5Sum1() throws Exception {

    }
    @Test
    public void testGetMd5Sum2() throws Exception {
        String strMD5 = getMd5Sum(pwd);
        System.out.println(strMD5);
    }
    @Test
    public void testGetMd5Sum3() throws Exception {
        String strMD5 = getMd5Sum(pwd);
        String strMD5Salt = MD5Utils.getMd5Sum(strMD5, salt);
        System.out.println(strMD5Salt);

    }
    @Test
    public void testGetMd5Sum4() throws Exception {

    }
    @Test
    public void testVerify() throws Exception {

    }
    @Test
    public void testGetMd5Sum5() throws Exception {

    }

}
