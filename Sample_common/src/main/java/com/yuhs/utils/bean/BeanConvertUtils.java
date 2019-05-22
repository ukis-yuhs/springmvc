package com.yuhs.utils.bean;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 依赖库：org.springframework.spring-beans
 * 要拷贝字段的目标对象不可以是抽象类、接口，或者只有有参构造方法，
 * 否则在targetClass.newInstance()时会报java.lang.InstantiationException
 * Created by yuhaisheng on 2019/5/20.
 * 这个utils使用率应该不大
 */
public class BeanConvertUtils {

    /**
     * 拷贝(指定原，目标)相同属性
     * @param source 原对象
     * @param targetClass 目标类型Class
     * @param <T> 返回类型
     * @return
     */
    public static <T> T convert(Object source, Class<T> targetClass) {
        return convert(source, targetClass, (String[]) null);
    }

    /**
     * 拷贝(指定原，目标)相同属性
     * @param source 原对象
     * @param target 目标类型
     * @param <T> 返回类型
     * @return
     */
    public static <T> T convert(Object source, T target) {
        return convert(source, target, (String[]) null);
    }

    /**
     * 拷贝(指定原，目标)相同属性,忽略空值
     * @param source 原对象
     * @param targetClass 目标类型Class
     * @param <T> 返回类型
     * @return
     */
    public static <T> T convertIgnoreNullProperty(Object source, Class<T> targetClass) {
        return convert(source, targetClass, getNullPropertyNames(source));
    }

    /**
     * 拷贝(指定原，目标)相同属性,忽略空值
     * @param source 原对象
     * @param target 目标类型
     * @param <T> 返回类型
     * @return
     */
    public static <T> T convertIgnoreNullProperty(Object source, T target) {
        return convert(source, target, getNullPropertyNames(source));
    }

    /**
     * 拷贝列表(指定原，目标)相同属性
     * @param sourceList 原对象列表
     * @param targetClass 目标类型
     * @param <T> 返回类型
     * @return
     */
    public static <T> List<T> convert(List<?> sourceList, Class<T> targetClass) {
        return convert(sourceList, targetClass, false);
    }

    /**
     * 拷贝列表(指定原，目标)相同属性,忽略空值
     * @param sourceList 原对象列表
     * @param targetClass 目标类型
     * @param <T> 返回类型
     * @return
     */
    public static <T> List<T> convertIgnoreNullProperty(List<?> sourceList, Class<T> targetClass) {
        return convert(sourceList, targetClass, true);
    }

    /**
     * 拷贝(指定原，目标)相同属性
     * @param source
     * @param targetClass
     * @param ignoreProperties
     * @param <T>
     * @return
     */
    private static <T> T convert(Object source, Class<T> targetClass, String... ignoreProperties) {
        if (source == null) {
            return null;
        }
        try {
            T target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target, ignoreProperties);

            return target;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 拷贝(指定原，目标)相同属性
     * @param source
     * @param target
     * @param ignoreProperties
     * @param <T>
     * @return
     */
    private static <T> T convert(Object source, T target, String... ignoreProperties) {
        if (source == null || target == null) {
            return null;
        }
        try {
            BeanUtils.copyProperties(source, target, ignoreProperties);
            return target;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 拷贝列表(指定原，目标)相同属性
     * @param sourceList
     * @param targetClass
     * @param isIgnoreProperties
     * @param <T>
     * @return
     */
    private static <T> List<T> convert(List<?> sourceList, Class<T> targetClass, boolean isIgnoreProperties) {
        if (sourceList == null || sourceList.size() == 0) {
            return new ArrayList<T>(0);
        }
        List<T> list = new ArrayList<T>(sourceList.size());
        for (Object obj : sourceList) {
            String[] ignoreProperties = (String[]) null;
            if (isIgnoreProperties) {
                ignoreProperties = getNullPropertyNames(obj);
            }
            list.add(convert(obj, targetClass, ignoreProperties));
        }
        return list;
    }

    /**
     *
     * @param source
     * @return
     */
    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
