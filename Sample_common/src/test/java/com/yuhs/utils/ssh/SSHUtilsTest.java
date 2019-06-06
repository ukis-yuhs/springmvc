package com.yuhs.utils.ssh;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by yuhaisheng on 2019/6/2.
 */
public class SSHUtilsTest {
    
    @Test
    public void test() {
        SSHUtils s=new SSHUtils();
        try {
            System.out.println(s.login("116.255.149.143", "runner", ""));
            //s.downloadFile("/home/runner/checkorder/telecom/MXT371_20130901", "g://");
            System.out.println(s.ls("/home/runner").size());
            //s.createFile("/home/runner/acc.txt");
            //s.uploadFile("/home/runner/",new File( "d://first.log"));
            //s.rm("/home/runner/c.a");
            //s.rmdir("/home/runner/aaa");
//            s.createFile("/home/runner/my.doc");
//            s.mv("/home/runner/my.doc", "cccccc");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
