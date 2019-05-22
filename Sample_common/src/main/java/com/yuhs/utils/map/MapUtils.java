package com.yuhs.utils.map;

import java.util.Map;

/**
 * Created by yuhaisheng on 2019/5/20.
 */
public class MapUtils {
    /**
     * 判断map是否为空
     * @param map
     * @return
     */
    public static boolean isEmpty(Map map) {
        if(map == null) {
            return true;
        }
        return map.isEmpty();
    }

    /**
     * 判断map是否不为空
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }
}
