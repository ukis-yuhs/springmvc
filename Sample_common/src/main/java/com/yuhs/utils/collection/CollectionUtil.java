package com.yuhs.utils.collection;


import com.yuhs.utils.math.MathUtil;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 集合(List,Map,Set)辅助类
 * Created by yuhaisheng on 2019/5/23.
 */
public class CollectionUtil {

    /**
     * 从List中随机取出一个元素
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T randomOne(List<T> list) {
        if (isEmpty(list)) {
            return null;
        }
        return list.get(MathUtil.randomNumber(0, list.size()));
    }

    /**
     * 从数组中随机取出一个元素
     *
     * @param object 源数组
     * @param <T>    数组的一个元素
     * @return
     */
    public static <T> T randomOne(T[] object) {
        if (isEmpty(object)) {
            return null;
        }
        return object[MathUtil.randomNumber(0, object.length)];
    }

    /**
     * 判断数组是否为空
     *
     * @param array 数组
     * @param <T>
     * @return 空返回true，否则返回false
     */
    public static <T> boolean isEmpty(T[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * 集合是否为空
     *
     * @param collection 集合
     * @return 空返回true，否则返回false
     */
    public static boolean isEmpty(Collection collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * Map是否为空。如果传入的值为null或者集合不包含元素都认为为空
     *
     * @param map Map
     * @return 空返回true，否则返回false
     */
    public static boolean isEmpty(Map map) {
        return (map == null || map.isEmpty());
    }

    /**
     * 数组中是否存在这个元素
     *
     * @param objectArray 数组
     * @param compare     元素
     * @param <T>
     * @return
     */
    public static <T> boolean arrayContain(T[] objectArray, T compare) {
        if (isEmpty(objectArray)) {
            return false;
        }
        for (T obj : objectArray) {
            if (obj.equals(compare)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 向list中添加数组
     *
     * @param list
     * @param array
     * @param <T>
     */
    public static <T> void addArrayToList(List<T> list, T[] array) {
        if (isEmpty(list)) {
            return;
        }
        for (T t : array) {
            list.add(t);
        }
    }

    /**
     * 将数组进行反转，倒置
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> T[] reverseArray(T[] array) {
        if (isEmpty(array)) {
            return null;
        }
        T[] res = (T[]) java.lang.reflect.Array.newInstance(array[0].getClass(), array.length);
        //新序号
        int k = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            res[k++] = array[i];
        }
        return res;
    }

    /**
     * 将数组转为list
     *
     * @param array 源数组
     * @param <T>
     * @return
     */
    public static <T> List<T> arrayToList(T[] array) {
        if (isEmpty(array)) {
            return null;
        }
        List<T> list = new LinkedList<T>();
        for (T obj : array) {
            list.add(obj);
        }
        return list;
    }

    /**
     * 将list转为数组
     *
     * @param list 源list
     * @param <T>
     * @return
     */
    public static <T> T[] listToArray(List<T> list) {
        if (isEmpty(list)) {
            return null;
        }
        T[] objs = (T[]) java.lang.reflect.Array.newInstance(list.get(0).getClass(), list.size());
        int i = 0; //数组下标。
        for (T obj : list) {
            objs[i++] = obj;
        }
        return objs;
    }

    /**
     * 将一个数组的内容全部添加到另外一个数组中，并返回一个新数组
     *
     * @param array1
     * @param array2
     * @param <T>
     * @return
     */
    public static <T> T[] concatenateArrays(T[] array1, T[] array2) {
        if (isEmpty(array1)) {
            return array2;
        }
        if (isEmpty(array2)) {
            return array1;
        }
        T[] resArray = (T[]) java.lang.reflect.Array.newInstance(array1[0].getClass(), array1.length + array2.length);
        System.arraycopy(array1, 0, resArray, 0, array1.length);
        System.arraycopy(array2, 0, resArray, array1.length, array2.length);
        return resArray;
    }

    /**
     * 将一个object添加到一个数组中，并返回一个新数组
     *
     * @param array  被添加到的数组
     * @param object 被添加的object
     * @param <T>
     * @return
     */
    public static <T> T[] addObjectToArray(T[] array, T object) {
        //结果数组
        T[] resArray = null;
        if (isEmpty(array)) {
            resArray = (T[]) java.lang.reflect.Array.newInstance(object.getClass(), 1);
            resArray[0] = object;
            return resArray;
        }
        //原数组不为空时。
        resArray = (T[]) java.lang.reflect.Array.newInstance(array[0].getClass(), array.length + 1);
        System.arraycopy(array, 0, resArray, 0, array.length);
        resArray[array.length] = object;
        return resArray;
    }
}
