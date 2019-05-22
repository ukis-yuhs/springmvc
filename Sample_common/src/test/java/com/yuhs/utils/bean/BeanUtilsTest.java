package com.yuhs.utils.bean;

import com.yuhs.utils.bean.testSampleBean.ABean;
import com.yuhs.utils.bean.testSampleBean.CBean;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by yuhaisheng on 2019/5/20.
 */
public class BeanUtilsTest {

    /**
     * 深度Copy
     */
    @Test
    public void testDeepClone() {
        CBean cBean = new CBean();
        cBean.setC1(1L);
        cBean.setC1(2L);
        cBean.setC1(3L);
        cBean.setB1(4);
        cBean.setB2(5);
        cBean.setB3(6);
        cBean.setA1("A1");
        cBean.setA1("A2");
        cBean.setA1("A3");

        try {
            CBean cloneBean = (CBean)BeanUtils.deepClone(cBean);
            cloneBean.setB2(50);

            System.out.println("original bean B2 value " + cBean.getB2());
            System.out.println("clone bean B2 value " +cloneBean.getB2());

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取指定类到所有属性
     */
    @Test
    public void testGetAllFields() {
        CBean cBean = new CBean();
        Field[] fields = BeanUtils.getAllFields(cBean);
        for (int index = 0; index < fields.length; index++) {
            System.out.println(index + " >> "+fields[index]);
        }
    }

    /**
     * 是否为JDK内置对象
     */
    @Test
    public void testIsJdkInnerObject() {
        ABean aBean = new ABean();
        System.out.println(BeanUtils.isJdkInnerObject(aBean));
        System.out.println(BeanUtils.isJdkInnerObject(new Integer("60")));
    }

    /**
     * 判断传入对象是否存在空对象
     */
    @Test
    public void testIsEmptyObjects() {
        ABean aBean = new ABean();
        System.out.println(BeanUtils.isEmptyObjects(aBean));
        System.out.println(BeanUtils.isEmptyObjects(aBean,null));
    }
}
