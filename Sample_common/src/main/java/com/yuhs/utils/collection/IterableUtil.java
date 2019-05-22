package com.yuhs.utils.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *     |---java.lang---Iterable
 *     |
 *     |
 *     |
 * Java+                           |-+List
 *     |             |-+Collection-+-+Set--SortedSet--NevigableSet
 *     |             |             |-+Queue--Deque
 *     |             |
 *     |---java.util-+-+Map--SortedMap--NavigableSMap
 *                   |
 *                   |-+Iterator
 *                   |
 *                   |-+RandomAccess
 *
 * Created by yuhaisheng on 2019/5/22.
 */
public class IterableUtil {
    /**
     * 获取Iterable的大小
     * @param iterable
     * @param <T>
     * @return
     */
    public static<T> int getIterableSize(Iterable<T> iterable){
        Iterator<T> iterator=iterable.iterator();
        int count=0;
        while(iterator.hasNext()){
            count++;
            iterator.next();
        }
        return count;
    }

    /**
     * 将Iterable转换成List
     * @param iterable
     * @param <T>
     * @return
     */
    public static<T> List<T> convertToList(Iterable<T> iterable){
        List<T> list=new ArrayList<T>();
        Iterator<T> iterator=iterable.iterator();
        while(iterator.hasNext()){
            T t=iterator.next();
            list.add(t);
        }
        return list;
    }

}
