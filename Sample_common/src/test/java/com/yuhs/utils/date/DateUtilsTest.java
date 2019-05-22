package com.yuhs.utils.date;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import static com.yuhs.utils.date.DateUtils.getNewDateFormat;
import static com.yuhs.utils.date.DateUtils.shortFormat;
import static com.yuhs.utils.date.DateUtils.webFormat;

/**
 * Created by yuhaisheng on 2019/5/21.
 */
public class DateUtilsTest {

    /**
     * 日期格式转换，返回日期类型
     */
    @Test
    public void testFormatDate() {
        Date org = DateUtils.getNowDate();
        Date dest = DateUtils.formatDate(org, shortFormat);
        System.out.println(org.toString());
        System.out.println(dest.toString());
    }

    /**
     * 日期格式转换，返回字符串类型
     */
    @Test
    public void testFormat() {
        Date org = DateUtils.getNowDate();
        String dest = DateUtils.format(org, webFormat);
        System.out.println(org.toString());
        System.out.println(dest.toString());
    }

    /**
     * 将字符串格式化Date
     */
    @Test
    public void testParseDateNoTime() {
        try {
            System.out.println(DateUtils.parseDateNoTime("2019-08-08", webFormat));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * 计算当前时间几小时之后的时间
     */
    @Test
    public void testAddHours() {
        System.out.println(DateUtils.addHours(DateUtils.getNowDate(), 3));
    }

    /**
     * 计算当前时间几分钟之后的时间
     */
    @Test
    public void testAddMinutes() {
        System.out.println(DateUtils.addMinutes(DateUtils.getNowDate(), 180));
    }

    /**
     * 计算当前时间几秒之后的时间
     */
    @Test
    public void testAddSeconds() {
        System.out.println(DateUtils.addMinutes(DateUtils.getNowDate(), 180 * 60));
    }

    /**
     * 获取指定天数的后的几天
     */
    @Test
    public void testAddDays() {
        System.out.println(DateUtils.addDays(DateUtils.getNowDate(), 11));
    }

    /**
     * 获取当前日期后指定天数的日期
     */
    @Test
    public void testAddDaysFromNow() {
        System.out.println(DateUtils.addDaysFromNow(11));
    }

    /**
     * 获取指定日期明天的日期
     */
    @Test
    public void testGetTomorrowDateString() {
        try {
            System.out.println(DateUtils.getTomorrowDateString("2019-08-08", webFormat));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * 获取前一天日期(yyyyMMdd)
     */
    @Test
    public void testGetYestoday() {
        System.out.println(DateUtils.getYestoday());
    }
    /**
     * 获取指定天的前一天日期
     */
    @Test
    public void testGetYesterDayDateString() {
        try {
            System.out.println(DateUtils.getYesterDayDateString("2019-08-08", webFormat));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取指定天的前n天日期
     */
    @Test
    public void testGetBeforeDayString() {
        System.out.println(DateUtils.getBeforeDayString("2019-08-08", webFormat, 8));
    }
    /**
     * 获取指当前日期的前n天日期
     */
    @Test
    public void testGetCurrentDateBeforeDayString() {
        System.out.println(DateUtils.getCurrentDateBeforeDayString(21));
    }
    /**
     * 获取当前的日期(yyyyMMdd)
     */
    @Test
    public void testGetCurrentDate() {
        System.out.println(DateUtils.getCurrentDate());
    }

    /**
     * 获取当前的时间(yyyyMMddHHmmss)
     */
    @Test
    public void testGetCurrentTime() {
        System.out.println(DateUtils.getCurrentTime());
    }

    /**
     * 取得两个日期间隔毫秒数（日期1-日期2）
     */
    @Test
    public void testGetDiffMilliseconds() {
        try {
            Date firstDate = DateUtils.parseDateNoTime("20190808121213",DateUtils.longFormat);
            Date secondDate = DateUtils.parseDateNoTime("20190808121212",DateUtils.longFormat);
            System.out.println(DateUtils.getDiffMilliseconds(firstDate,secondDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    /**
     * 取得两个日期间隔秒数（日期1-日期2）
     */
    @Test
    public void testGetDiffSeconds() {
        try {
            Date firstDate = DateUtils.parseDateNoTime("20190808121213",DateUtils.longFormat);
            Date secondDate = DateUtils.parseDateNoTime("20190808121212",DateUtils.longFormat);
            System.out.println(DateUtils.getDiffSeconds(firstDate,secondDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取得两个日期间隔分钟数（日期1-日期2）
     */
    @Test
    public void testGetDiffMinutes() {
        try {
            Date firstDate = DateUtils.parseDateNoTime("20190808131313",DateUtils.longFormat);
            Date secondDate = DateUtils.parseDateNoTime("20190808121212",DateUtils.longFormat);
            System.out.println(DateUtils.getDiffMinutes(firstDate,secondDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取得两个日期间隔天数（日期1-日期2）
     */
    @Test
    public void testGetDiffDays() {
        try {
            Date firstDate = DateUtils.parseDateNoTime("20190818131313",DateUtils.longFormat);
            Date secondDate = DateUtils.parseDateNoTime("20190808121212",DateUtils.longFormat);
            System.out.println(DateUtils.getDiffDays(firstDate,secondDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取得当前月的第一天，指定format
     */
    @Test
    public void testGetShortFirstDayOfMonth() {
        System.out.println(DateUtils.getShortFirstDayOfMonth(webFormat));
    }

    /**
     * 日期格式转换
     */
    @Test
    public void testConvert() {
        DateFormat df1 = getNewDateFormat(DateUtils.shortFormat);
        DateFormat df2 = getNewDateFormat(DateUtils.webFormat);
        System.out.println(DateUtils.convert("20190808",df1,df2));
    }

    /**
     * 比较两个日期大小
     */
    @Test
    public void testDateNotLessThan() {
        System.out.println(DateUtils.dateNotLessThan("20190808","20190708",getNewDateFormat(DateUtils.shortFormat)));
    }


}
