package com.yuhs.utils.zh;

import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/4/24.
 */
public class PinyinUtilTest {
    @Test
    public void testGetFullSpell1() throws Exception {
        String string = PinyinUtil.getFullSpell("测试拼音转换");
        System.out.println(string);
    }
    @Test
    public void testGetFullSpell2() throws Exception {
        String string = PinyinUtil.getFullSpell("测试拼音转换"," ");
        System.out.println(string);
    }
    @Test
    public void testGetFullSpellFirstUp1() throws Exception {
        String string = PinyinUtil.getFullSpellFirstUp("测试拼音转换");
        System.out.println(string);
    }
    @Test
    public void testGetFullSpellFirstUp2() throws Exception {
        String string = PinyinUtil.getFullSpellFirstUp("测试拼音转换"," ");
        System.out.println(string);
    }
    @Test
    public void testGetFirstSpell() throws Exception {
        String string = PinyinUtil.getFirstSpell("测试拼音转换");
        System.out.println(string);
    }
    @Test
    public void testGetFullSpellOfWord() throws Exception {
        char ch = '测';
        String string = PinyinUtil.getFullSpellOfWord(ch);
        System.out.println(string);
    }
}
