package com.yuhs.utils.other;

import java.text.DecimalFormat;

/**
 * Created by yuhaisheng on 2019/5/17.
 */
public class OtherUtils {

    /**
     * 计算机单位变换
     * @param size
     * @return
     */
    public static String tranCompSize(long size) {
        DecimalFormat df = new DecimalFormat("#.00");
        String result = "";
        if (size < 1024) {
            result = df.format((double) size) + "B";
        } else if (size < 1048576) {
            result = df.format((double) size / 1024) + "K";
        } else if (size < 1073741824) {
            result = df.format((double) size / 1048576) + "M";
        } else {
            result = df.format((double) size / 1073741824) + "G";
        }
        return result;
    }

    /**
     * 计算机单位变换
     * @param size
     * @return
     */
    public static String TranCompSize2(int size) {
        final int BYTES_IN_KILOBYTES = 1024;
        final DecimalFormat dec = new DecimalFormat("###.#");
        final String KILOBYTES = " KB";
        final String MEGABYTES = " MB";
        final String GIGABYTES = " GB";
        float fileSize = 0;
        String suffix = KILOBYTES;

        if (size > BYTES_IN_KILOBYTES) {
            fileSize = size / BYTES_IN_KILOBYTES;
            if (fileSize > BYTES_IN_KILOBYTES) {
                fileSize = fileSize / BYTES_IN_KILOBYTES;
                if (fileSize > BYTES_IN_KILOBYTES) {
                    fileSize = fileSize / BYTES_IN_KILOBYTES;
                    suffix = GIGABYTES;
                } else {
                    suffix = MEGABYTES;
                }
            }
        }
        return String.valueOf(dec.format(fileSize) + suffix);
    }
}
