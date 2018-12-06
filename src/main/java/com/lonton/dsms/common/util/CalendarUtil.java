package com.lonton.dsms.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarUtil {
	
	/**
	 * 根据年份{@code year}和月份{@code month}，返回对应该月份的最后一天日期的字符串，格式为yyyy-MM-dd
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayByYearMonth(int year, int month) {
		int days = CalendarUtil.getDaysByYearMonth(year, month);
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month-1);
		c.set(Calendar.DAY_OF_MONTH, days);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}
	
	/**
	 * 根据年份{@code year}和月份{@code month}，返回对应该月份的最后一天日期的字符串，格式为yyyy-MM-dd
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstDayByYearMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month-1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}
	
	/**
	 * 根据年份{@code year}和月份{@code month}，返回对应该月份所有天的日期字符串，格式为yyyy-MM-dd
	 * @param year
	 * @param month
	 * @return
	 */
	public static List<String> getAllDateByYearMonth(int year, int month) {
		List<String> result = new ArrayList<String>();
		int days = CalendarUtil.getDaysByYearMonth(year, month);
		for(int i=1; i<= days; i++) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, month-1);
			c.set(Calendar.DAY_OF_MONTH, i);
			result.add(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
		}
		return result;
	}

    /**
     * 由年份数值、月份数值查找当月最大日期
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }


    /**
     * 由年月的字符串（格式：2010-01）生成当年第一天
     *
     * @param year_month
     * @return
     */
    public static Date getStartDateByString(String year_month) {
        int start_year = Integer.parseInt(year_month.substring(0, 4));
        int start_month = Integer.parseInt(year_month.substring(5));
        return new Date(start_year - 1900, start_month - 1, 1);
    }


    /**
     * 由年月的字符串（格式：2010-01）生成当年最后一天
     *
     * @param year_month
     * @return
     */
    public static Date getEndDateByString(String year_month) {
        int end_year = Integer.parseInt(year_month.substring(0, 4));
        int end_month = Integer.parseInt(year_month.substring(5));
        int end_day = CalendarUtil.getDaysByYearMonth(end_year, end_month);
        return new Date(end_year - 1900, end_month - 1, end_day);
    }

    /**
     * 用Date对象创建整点Date（或LocalDateTime）对象,如2017-10-10 12:34:34 返回 2017-10-10 12:00:00
     */
    public static Date getDateSharpDateHour(Date dt) {
        Calendar a = new GregorianCalendar(dt.getYear() + 1900, dt.getMonth(), dt.getDate(), dt.getHours(), 00, 00);
        return a.getTime();
    }

//    public static Date getDateSharpDateHour(LocalDateTime dt) {
//        Calendar a = new GregorianCalendar(dt.getYear(), dt.getMonthOfYear() - 1, dt.getDayOfMonth(), dt.getHourOfDay(), 00, 00);
//        return a.getTime();
//    }
//
//
//
//    /**
//     * 用Date对象创建整点Date（或LocalDateTime）对象,如2017-10-10 12:34:34 返回 2017-10-10 12:00:00
//     */
//    public static Date getDateStartHour(Date dt) {
//        Calendar a = new GregorianCalendar(dt.getYear() + 1900, dt.getMonth(), dt.getDate(), 00, 00, 00);
//        return a.getTime();
//    }
//
//
//
//
//
//
//    public static LocalDateTime getLocalDateTimeSharpDateHour(Date dt) {
//        Calendar a = new GregorianCalendar(dt.getYear() + 1900, dt.getMonth(), dt.getDate(), dt.getHours(), 00, 00);
//        return new LocalDateTime(a.getTime());
//    }
//
//    public static LocalDateTime getLocalDateTimeSharpDateHour(LocalDateTime dt) {
//        Calendar a = new GregorianCalendar(dt.getYear(), dt.getMonthOfYear() - 1, dt.getDayOfMonth(), dt.getHourOfDay(), 00, 00);
//        return new LocalDateTime(a.getTime());
//    }
//
//
//    /**
//     * 用Date对象创建当日最后时刻的Date对象,如2017-10-10 12:34:34 返回 2017-10-10 23:59:59
//     *
//     * @param dt
//     * @return
//     */
//    public static Date getLastHour(Date dt) {
//        Calendar a = new GregorianCalendar(dt.getYear() + 1900, dt.getMonth(), dt.getDate(), 23, 59, 59);
//        return a.getTime();
//    }
//
//    public static Date getLastHour(LocalDateTime dt) {
//        Calendar a = new GregorianCalendar(dt.getYear(), dt.getMonthOfYear() - 1, dt.getDayOfMonth(), 23, 59, 59);
//        return a.getTime();
//    }
//
//    /**
//     * 取一个月的最后一天时间
//     *
//     * @param dt
//     * @return
//     */
//    public static String getLastDay(Date dt) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar a = Calendar.getInstance();
//        a.setTime(dt);
//        a.add(Calendar.MONTH, 1);
//        a.set(Calendar.DATE, 1);
//        a.add(Calendar.DATE, -1);
//        return new StringBuffer().append(df.format(a.getTime())).append(" ").append("23:59:59").toString();
//    }
//
//    public static String getLastDay(LocalDateTime dt) {
//
//        Date t = new GregorianCalendar(dt.getYear(), dt.getMonthOfYear() - 1, 1).getTime();
//
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        Calendar a = Calendar.getInstance();
//        a.setTime(t);
//        a.add(Calendar.MONTH, 1);
//        a.set(Calendar.DATE, 1);
//        a.add(Calendar.DATE, -1);
//
//        String s = new StringBuffer().append(df.format(a.getTime())).append(" ").append("23:59:59").toString();
//        return s;
//    }
//
//
//    /**
//     * 用Date对象创建当年的12月1号的Date对象,如2017-10-10 12:34:34 返回 2017-12-01   构造一年的终止月
//     */
//    public static Date getDateEndMonth(LocalDateTime dt) {
//        Calendar a = new GregorianCalendar(dt.getYear(), 11, 1);
//        return a.getTime();
//    }
//
//
//    /**
//     *
//     */
//    /**
//     * 用带时间的Date对象创建只有日期的Date对象,如2017-10-10 12:34:34 返回 2017-10-10
//     *
//     * @param dt
//     * @return
//     */
//    public static Date getDateDay(Date dt) {
//        Calendar a = new GregorianCalendar(dt.getYear() + 1900, dt.getMonth(), dt.getDate());
//        return a.getTime();
//    }
//
//    public static Date getDateDay(LocalDateTime dt) {
//        Calendar a = new GregorianCalendar(dt.getYear(), dt.getMonthOfYear() - 1, dt.getDayOfMonth());
//        return a.getTime();
//    }
//
//
//    /**
//     * 用Date对象创建当年第一天Date（或LocalDateTime对象）,如2017-10-10 12:34:34 返回 2017-01-01   构造一年的起始月
//     */
//    public static Date getDateStartMonth(LocalDateTime dt) {
//        Calendar a = new GregorianCalendar(dt.getYear(), 0, 1);
//        return a.getTime();
//    }
//
//    public static LocalDateTime getDateStartMonth2(LocalDateTime dt) {
//        Calendar a = new GregorianCalendar(dt.getYear(), 0, 1);
//        LocalDateTime dd = new LocalDateTime(a.getTime());
//        return dd;
//    }
//
//
//    /**
//     * 由字符串生成带时间的Date对象，字符串格式 2010-11-12 12:13:33 或 2010-9-1 8:0:0
//     *
//     * @param str
//     * @return
//     */
//    public static Date getDateByString(String str) {
//        int year = Integer.parseInt(str.substring(0, 4));
//        int month = Integer.parseInt(str.substring(5, str.lastIndexOf('-')));
//        int day = Integer.parseInt(str.substring(str.lastIndexOf('-') + 1, str.lastIndexOf(' ')));
//        int hour = Integer.parseInt(str.substring(str.lastIndexOf(' ') + 1, str.indexOf(':')));
//        int minute = Integer.parseInt(str.substring(str.indexOf(':') + 1, str.lastIndexOf(':')));
//        int second = Integer.parseInt(str.substring(str.lastIndexOf(':') + 1, str.length()));
//        return new Date(year - 1900, month - 1, day, hour, minute, second);
//    }
//
//    /**
//     * 根据传入时间生成昨日 开始时间    传入2017-10-12 10:12:13  生成   2017-10-11 00:00:00
//     *
//     * @param
//     * @return
//     */
//    public static Date getYesterdayStart(Date dt) {
//        Calendar cal = new GregorianCalendar(dt.getYear() + 1900, dt.getMonth(), dt.getDate(), 00, 00, 00);
//        cal.add(Calendar.DAY_OF_MONTH, -1);
//        return cal.getTime();
//
//    }
//
//    /**
//     * 根据传入时间生成昨日 开始时间    传入2017-10-12 10:12:13  生成   2017-10-11 23:59:59
//     *
//     * @param
//     * @return
//     */
//    public static Date getYesterdayEnd(Date dt) {
//        Calendar cal = new GregorianCalendar(dt.getYear() + 1900, dt.getMonth(), dt.getDate(), 23, 59, 59);
//        cal.add(Calendar.DAY_OF_MONTH, -1);
//        return cal.getTime();
//
//    }
//
//
//    /**
//     * 根据传入时间生成昨日 开始时间    传入2017-10-12 10:12:13  生成   2017-01-01 00:00:00
//     *
//     * @param
//     * @return
//     */
//    public static Date getYearStart(Date dt) {
//        Calendar cal = new GregorianCalendar(dt.getYear() + 1900, dt.getMonth(), 01, 23, 59, 59);
//        return cal.getTime();
//
//    }
//
//
//    /**
//     * 根据传入时间生成昨日 开始时间    传入2017-10-12 10:12:13  生成   2017-10-10 08:00:00
//     *
//     * @param
//     * @return
//     */
//    public static Date getEight(Date dt) {
//        Calendar cal = new GregorianCalendar(dt.getYear() + 1900, dt.getMonth(),dt.getDate(), 8, 00, 00);
//        return cal.getTime();
//
//    }







}
