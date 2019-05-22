package com.yuhs.utils.date;

import com.yuhs.utils.string.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by yuhaisheng on 2019/5/21.
 */
public class DateUtils {

    /**
     * 一天经历的秒数
     */
    public final static long ONE_DAY_SECONDS = 86400;
    /**
     * 一天经历的豪秒数
     */
    public static long ONE_DAY_MILL_SECONDS = 86400000;
    /**
     * 日期格式
     */
    public final static String shortFormat = "yyyyMMdd";
    public final static String longFormat = "yyyyMMddHHmmss";
    public final static String webFormat = "yyyy-MM-dd";
    public final static String timeFormat = "HHmmss";
    public final static String monthFormat = "yyyyMM";
    public final static String chineseDtFormat = "yyyy年MM月dd日";
    public final static String emailDtFormat = "yyyy年MM月dd日HH:mm:ss";
    public final static String smsDtFormat = "MM月dd日HH:mm";
    public final static String newFormat = "yyyy-MM-dd HH:mm:ss";
    public final static String newFormat2 = "yyyy-MM-dd HH:mm";
    public final static String newFormat3 = "yyyy-MM-dd HH";
    public final static String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 毫秒转换为日期
     *
     * @param millsecord 毫秒数
     * @return
     */
    public static Date getDate(long millsecord) {
        return new Date(millsecord);
    }

    /**
     * 获取指定DateFormat
     *
     * @param pattern
     * @return
     */
    public static DateFormat getNewDateFormat(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        df.setLenient(false);
        return df;
    }

    /**
     * 日期格式转换，返回日期类型
     *
     * @param date
     * @param format
     * @return
     */
    public static Date formatDate(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        ParsePosition pos = new ParsePosition(0);
        Date newDate = formatter.parse(dateString, pos);
        return newDate;
    }

    /**
     * 日期格式转换，返回文字列
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 指定毫秒，格式化成日期
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatByLong(long date, String format) {
        return new SimpleDateFormat(format).format(new Date(date));
    }

    /**
     * 将字符串格式化Date
     * @param sDate
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parseDateNoTime(String sDate, String format) throws ParseException {
        if (StringUtils.isEmpty(format)) {
            throw new ParseException("Null format. ", 0);
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        if ((sDate == null) || (sDate.length() < format.length())) {
            throw new ParseException("length too little", 0);
        }
        return dateFormat.parse(sDate);
    }

    /**
     * 将毫秒字符串格式化
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatByString(String date, String format) {
        if (StringUtils.isEmpty(date)) {
            return new SimpleDateFormat(format).format(new Date(NumberUtils.toLong(date)));
        }
        return StringUtils.EMPTY;
    }

    /**
     * 计算当前时间几小时之后的时间
     * @param date
     * @param hours
     * @return
     */
    public static Date addHours(Date date, long hours) {
        return addMinutes(date, hours * 60);
    }

    /**
     * 计算当前时间几分钟之后的时间
     * @param date
     * @param minutes
     * @return
     */
    public static Date addMinutes(Date date, long minutes) {
        return addSeconds(date, minutes * 60);
    }

    /**
     * 计算当前时间几秒之后的时间
     * @param date
     * @param seconde
     * @return
     */
    public static Date addSeconds(Date date, long seconde) {
        return new Date(date.getTime() + (seconde * 1000));
    }

    /**
     * 判断输入的字符串是否为合法的小时
     * @param target
     * @return
     */
    public static boolean isValidHour(String target) {
        if (!StringUtils.isEmpty(target) && org.apache.commons.lang.StringUtils.isNumeric(target)) {
            int hour = new Integer(target).intValue();

            if ((hour >= 0) && (hour <= 23)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断输入的字符串是否为合法的分或秒
     * @param target
     * @return
     */
    public static boolean isValidMinuteOrSecond(String target) {
        if (!StringUtils.isEmpty(target) && org.apache.commons.lang.StringUtils.isNumeric(target)) {
            int hour = new Integer(target).intValue();

            if ((hour >= 0) && (hour <= 59)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取指定天数的后的几天
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, long days) {
        return addSeconds(date, days * ONE_DAY_SECONDS);
    }

    /**
     * 获取当前日期后指定天数的日期
     * @param days
     * @return
     */
    public static Date addDaysFromNow(long days) {
        return addSeconds(new Date(System.currentTimeMillis()), days * ONE_DAY_SECONDS);
    }

    /**
     * 获取指定日期明天的日期
     * @param sDate
     * @return
     * @throws ParseException
     */
    public static String getTomorrowDateString(String sDate, String format) throws ParseException {
        Date aDate = parseDateNoTime(sDate, format);
        aDate = addSeconds(aDate, ONE_DAY_SECONDS);
        DateFormat df = getNewDateFormat(format);
        return getDateString(aDate, df);
    }

    /**
     * 格式日期
     * @param date
     * @param dateFormat
     * @return
     */
    public static String getDateString(Date date, DateFormat dateFormat) {
        if (date == null || dateFormat == null) {
            return null;
        }

        return dateFormat.format(date);
    }
    /**
     * 获取前一天日期(yyyyMMdd)
     *
     * @return
     */
    public static String getYestoday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return new SimpleDateFormat(shortFormat).format(calendar.getTime());
    }

    /**
     * 获取前一天日期
     * @param sDate
     * @return
     * @throws ParseException
     */
    public static String getYesterDayDateString(String sDate, String format) throws ParseException {
        Date aDate = parseDateNoTime(sDate, format);
        aDate = addSeconds(aDate, -ONE_DAY_SECONDS);
        DateFormat df = getNewDateFormat(format);
        return getDateString(aDate,df);
    }

    /**
     * 获取指定日期的前n天
     * @param dateString
     * @param format
     * @param days
     * @return
     */
    public static String getBeforeDayString(String dateString, String format, int days) {
        Date date;
        DateFormat df = getNewDateFormat(format);
        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            date = new Date();
        }
        date = new Date(date.getTime() - (ONE_DAY_MILL_SECONDS * days));
        return df.format(date);
    }
    /**
     * 获取当前的日期(yyyyMMdd)
     *
     * @return
     */
    public static String getCurrentDate() {
        return new SimpleDateFormat(shortFormat).format(new Date());
    }
    /**
     * 获取当前天的前n天
     * @param days
     * @return
     */
    public static String getCurrentDateBeforeDayString(int days) {
        Date date = new Date(System.currentTimeMillis() - (ONE_DAY_MILL_SECONDS * days));
        DateFormat dateFormat = getNewDateFormat(shortFormat);
        return getDateString(date, dateFormat);
    }
    /**
     * 获取当前的时间(yyyyMMddHHmmss)
     *
     * @return
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat(longFormat).format(new Date());
    }

    /**
     * 取得两个日期间隔毫秒数（日期1-日期2）
     * @param firstDate
     * @param secondDate
     * @return 间隔毫秒数
     */
    public static long getDiffMilliseconds(Date firstDate, Date secondDate) {
        Calendar first = new GregorianCalendar();
        first.setTime(firstDate);
        Calendar second = new GregorianCalendar();
        second.setTime(secondDate);
        return (first.getTimeInMillis() - second.getTimeInMillis());
    }

    /**
     * 取得两个日期间隔秒数（日期1-日期2）
     * @param firstDate
     * @param secondDate
     * @return
     */
    public static long getDiffSeconds(Date firstDate, Date secondDate) {
        Calendar first = new GregorianCalendar();
        first.setTime(firstDate);
        Calendar second = new GregorianCalendar();
        second.setTime(secondDate);
        return (first.getTimeInMillis() - second.getTimeInMillis()) / 1000;
    }

    /**
     * 取得两个日期间隔分钟数（日期1-日期2）
     * @param firstDate
     * @param secondDate
     * @return
     */
    public static long getDiffMinutes(Date firstDate, Date secondDate) {
        Calendar first = new GregorianCalendar();
        first.setTime(firstDate);
        Calendar second = new GregorianCalendar();
        second.setTime(secondDate);
        return (first.getTimeInMillis() - second.getTimeInMillis()) / (60 * 1000);
    }

    /**
     * 取得两个日期间隔天数（日期1-日期2）
     * @param firstDate
     * @param secondDate
     * @return
     */
    public static long getDiffDays(Date firstDate, Date secondDate) {
        Calendar first = new GregorianCalendar();
        first.setTime(firstDate);
        Calendar second = new GregorianCalendar();
        second.setTime(secondDate);
        return (first.getTimeInMillis() - second.getTimeInMillis()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 判断日期是否有效
     * @param strDate
     * @param format
     * @return
     */
    public static boolean isValidDateFormat(String strDate, String format) {
        if (strDate.length() != format.length()) {
            return false;
        }
        try {
            // 日期非数字
            Integer.parseInt(strDate);
        } catch (Exception NumberFormatException) {
            return false;
        }
        DateFormat df = getNewDateFormat(format);
        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 取得当前月的第一天，指定format
     * @param format
     * @return
     */
    public static String getShortFirstDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();
        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        DateFormat df = getNewDateFormat(format);
        return df.format(cal.getTime());
    }

    /**
     * 日期格式转换
     * @param dateString
     * @param formatIn
     * @param formatOut
     * @return
     */
    public static String convert(String dateString, DateFormat formatIn, DateFormat formatOut) {
        try {
            Date date = formatIn.parse(dateString);
            return formatOut.format(date);
        } catch (ParseException e) {
            return "";
        }
    }

    /**
     * 比较两个日期大小
     * @param date1
     * @param date2
     * @param format
     * @return
     */
    public static boolean dateNotLessThan(String date1, String date2, DateFormat format) {
        try {
            Date d1 = format.parse(date1);
            Date d2 = format.parse(date2);
            if (d1.before(d2)) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 是否是今天
     *
     * @param time
     * @return
     */
    public static boolean isToday(long time) {
        return org.apache.commons.lang.time.DateUtils.isSameDay(new Date(time), new Date());
    }

    /**
     * 是否是明天
     *
     * @param time
     * @return
     */
    public static boolean isYesterday(long time) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(time);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(System.currentTimeMillis());
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
                && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1
                .get(Calendar.DAY_OF_YEAR) + 1 == cal2
                .get(Calendar.DAY_OF_YEAR));
    }

    /**
     * 是否是昨天
     *
     * @param time
     * @return
     */
    public static boolean isTomorrow(long time) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(time);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(System.currentTimeMillis());
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
                && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1
                .get(Calendar.DAY_OF_YEAR) - 1 == cal2
                .get(Calendar.DAY_OF_YEAR));
    }

    /**
     * 检查指定的时间与当前时间的间隔是否大于interval
     * @param time
     * @param interval
     * @return
     */
    public static boolean compareWithNow(long time, long interval) {
        if ((System.currentTimeMillis() - time) > interval) {
            return true;
        }
        return false;
    }

    /**
     * 当前时间与指定时间比，还有几天
     * @param target
     * @return
     */
    public static long getDiffDaysWithNow(long target) {
        long t1 = target - System.currentTimeMillis();
        if (t1 < 0) {
            return -1;
        }
        return t1 / (24 * 60 * 60 * 1000);
    }

    /**
     * 指定时间据当前时间已过去多少天
     * 不足的一天的天数不算入结果
     * 如 2.99天--->2天
     * @param target
     * @return
     */
    public static long getPastDaysWithNow(long target) {
        long t1 = System.currentTimeMillis() - target;
        if (t1 < 0) {
            return -1;
        }
        return t1 / (24 * 60 * 60 * 1000);
    }

    /**
     * 输入时间和当前时间比较
     * 多于24小时，--> X天
     * 多于1小时， --> X小时
     * 多于1分钟， --> X分钟
     * 多于1秒， --> X秒
     * 小于1秒， --> 0
     * 如果输入时间比当前时间小，--> 0
     *
     * @param target
     * @return
     */
    public static String getDynamicLeftTime(long target) {
        long t1 = target - System.currentTimeMillis();
        if (t1 < 0) {
            return "0";
        }
        long days = t1 / (24 * 60 * 60 * 1000);
        if (days > 0) {
            return days + "天";
        }
        long hours = t1 / (60 * 60 * 1000);
        if (hours > 0) {
            return hours + "小时";
        }
        long minutes = t1 / (60 * 1000);
        if (minutes > 0) {
            return minutes + "分钟";
        }
        long seconds = t1 / (1000);
        if (seconds > 0) {
            return seconds + "秒";
        }
        return "0";
    }

    /**
     * 输入时间和当前时间比较
     * 小于2天，显示昨天
     * 小于一天，显示x小时前
     * 小于一小时，显示x分钟前
     * 小于5分钟，显示刚刚
     *
     * @param target
     * @return
     */
    public static String getDynamicPassTime(long target) {
        String meaningfulTimeStr = null;
        long curTime = System.currentTimeMillis();
        long timeGap = (curTime - target) / 1000;
        if (timeGap > 60 * 60 * 24 * 2) {
            // 超过昨天前，显示日期
            DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            Date targetDate = new Date(target);
            meaningfulTimeStr = formater.format(targetDate);
        } else if (timeGap > 60 * 60 * 24 && timeGap <= 60 * 60 * 24 * 2) {// 小于2天，显示昨天
            meaningfulTimeStr = "昨天";
        } else if (timeGap > 60 * 60 && timeGap <= 60 * 60 * 24) { // 小于一天，显示x小时前
            Integer hourNum = (int) (timeGap / (60 * 60));
            meaningfulTimeStr = hourNum + "小时前";
        } else if (timeGap > 60 * 5 && timeGap <= 60 * 60) { // 小于一小时，显示x分钟前
            Integer minNum = (int) (timeGap / 60);
            meaningfulTimeStr = minNum + "分钟前";
        } else if (timeGap <= 60 * 5) { // 小于5分钟，显示刚刚
            meaningfulTimeStr = "刚刚";
        }
        return meaningfulTimeStr;
    }

    /**
     * 获取前一天日期
     * @param date
     * @return
     */
    public static Date getBeforeDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取下一天
     * @param date
     * @return
     */
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }
}
