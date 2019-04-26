package com.yuhs.shiro.authentication;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/4/25.
 */
public class MD5Test {
    @Test
    public void md5test1() {
        String source = "123456";
        String salt ="abc";
        int hashIterations = 1;
        Md5Hash md5Hash = new Md5Hash(source,salt,hashIterations);
        //System.out.println(md5Hash.toBase64());
        System.out.println(md5Hash.toString());
    }

    @Test
    public void md5test2(){
        String source = "123456";
        String salt ="abc";
        int hashIterations = 2;
        SimpleHash simpleHash = new SimpleHash("md5",source,salt,hashIterations);
        System.out.println(simpleHash.toBase64());
    }
}
