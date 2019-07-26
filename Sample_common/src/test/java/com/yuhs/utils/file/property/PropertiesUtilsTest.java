package com.yuhs.utils.file.property;

import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * Created by yuhaisheng on 2019/6/10.
 */
public class PropertiesUtilsTest {

    /**
     * 属性文件读取
     */
    @Test
    public void testPropertiesUtils() {
        try {
            PropertiesUtils propertiesUtils = new PropertiesUtils();
            propertiesUtils.insertOrUpdateValue("city","beijing","首都");
            Map<String, String> map = propertiesUtils.readAllProperties();
            for (String key : map.keySet()) {
                System.out.println("key = " + key + " value = " + map.get(key));
            }
            propertiesUtils.setValue("pagesize","100","页数");
            System.out.println("pagesize = " + propertiesUtils.readValue("pagesize"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
