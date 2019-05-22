package com.yuhs.utils.bean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yuhaisheng on 2019/5/20.
 */
public class BeanUtils {

    /**
     * 深度克隆
     * @param objSource
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public static Object deepClone(Object objSource) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (null == objSource) return null;
        // 获取源对象类型
        Class<?> clazz = objSource.getClass();
        Object objDes = clazz.newInstance();
        // 获得源对象所有属性
        Field[] fields = getAllFields(objSource);
        // 循环遍历字段，获取字段对应的属性值
        for (Field field : fields) {
            field.setAccessible(true);
            // 如果该字段是 static + final 修饰
            if (field.getModifiers() >= 24) {
                continue;
            }
            try {
                // 设置字段可见，即可用get方法获取属性值。
                field.set(objDes, field.get(objSource));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return objDes;
    }

    /**
     * 获取包括父类所有的属性
     * @param objSource
     * @return
     */
    public static Field[] getAllFields(Object objSource) {
        /*获得当前类的所有属性(private、protected、public)*/
        List<Field> fieldList = new ArrayList<Field>();
        Class tempClass = objSource.getClass();
        //当父类为null的时候说明到达了最上层的父类(Object类).
        while (tempClass != null && !tempClass.getName().toLowerCase().equals("java.lang.object")) {
            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

    /**
     * 判定传入对象是否为JDK内置对象
     * @param object
     * @return
     */
    public static boolean isJdkInnerObject(Object object) {
        if(object == null) {
            return false;
        } else if(object.getClass().isPrimitive()) {
            return true;
        } else {
            String packageName = object.getClass().getPackage().getName();
            return packageName.indexOf("java.") > -1;
        }
    }

    /**
     * 判断传入对象是和否存在空对象
     * @param objects
     * @return
     */
    public static boolean isEmptyObjects(Object... objects) {
        if(objects == null) {
            return true;
        } else {
            Object[] arr$ = objects;
            int len$ = objects.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Object object = arr$[i$];
                if(object == null) {
                    return true;
                }
            }

            return false;
        }
    }
}
