package com.yuhs.utils.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yuhaisheng on 2019/5/22.
 */
public class IteratorUtil {
    /**
     * 将Iterator转换成List
     * @param iter
     * @param <T>
     * @return
     */
    public static <T> List<T> convertToList(Iterator<T> iter) {
        List<T> copy = new ArrayList<T>();
        while (iter.hasNext())
            copy.add(iter.next());
        return copy;
    }
}
