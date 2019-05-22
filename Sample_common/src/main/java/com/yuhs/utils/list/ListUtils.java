package com.yuhs.utils.list;

import java.util.List;

/**
 * Created by yuhaisheng on 2019/5/20.
 */
public class ListUtils {
    /**
     * 判断列表是否为空
     * @param list
     * @return
     */
    public static boolean isEmpty(List list) {
        return list == null ? true : list.isEmpty();
    }

    /**
     * 判断列表是否不为空
     * @param list
     * @return
     */
    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }
}
