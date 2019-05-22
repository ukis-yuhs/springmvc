package com.yuhs.utils.html;

import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/5/22.
 */
public class HtmlUtilsTest {
    /**
     * 通过url获取网站html
     */
    @Test
    public void testParseHtml() {
        System.out.println(HtmlUtils.parseHtml("https://www.baidu.com"));
    }
}
