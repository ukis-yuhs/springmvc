package com.yuhs.utils.bean;

import com.yuhs.utils.bean.testSampleBean.AABean;
import com.yuhs.utils.bean.testSampleBean.BBBean;
import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/5/20.
 */
public class BeanConvertUtilsTest {

    /**
     * 拷贝(指定原，目标)相同属性
     */
    @Test
    public void testConvert() {
        AABean aaBean = new AABean();
        aaBean.setAa1("001");
        aaBean.setAa2("002");
        aaBean.setAa3("003");
        aaBean.setBb1("004");
        aaBean.setBb2("005");
        aaBean.setBb3("006");


        BBBean bbBean = new BBBean();

        bbBean = BeanConvertUtils.convert(aaBean, BBBean.class);
        System.out.println("BB1 = " + bbBean.getBb1());
        System.out.println("BB2 = " + bbBean.getBb2());
        System.out.println("BB3 = " + bbBean.getBb3());
    }
}
