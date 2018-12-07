package com.dengjian.gomars;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.dengjian.gomars.common.util.CalendarUtil;

public class DateAndCalendarUtilsTests {

	public static void main(String[] args) throws Exception {
		
		String beginDate = "";
		String endDate = "";
		int days = CalendarUtil.getDaysByYearMonth(2018, 10);
		System.out.println(days);
		
		Calendar c = Calendar.getInstance();
//		c.set(Calendar.YEAR, 2018);
		c.set(Calendar.MONTH, 9);
		c.set(Calendar.DAY_OF_MONTH, days);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(c.getTime()));
		
//		System.out.println(CalendarUtil.getLastDay(new Date()));
		
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		// 获取月，这里需要需要月份的范围为0~11，因此获取月份的时候需要+1才是当前月份值
		int month = calendar.get(Calendar.MONTH) + 1;
		// 获取日
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		// 获取时
		int hour = calendar.get(Calendar.HOUR);
		// int hour = calendar.get(Calendar.HOUR_OF_DAY); // 24小时表示
		// 获取分
		int minute = calendar.get(Calendar.MINUTE);
		// 获取秒
		int second = calendar.get(Calendar.SECOND);
		// 毫秒
		int milliSecond = calendar.get(Calendar.MILLISECOND);
		// 星期，英语国家星期从星期日开始计算
		int weekday = calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println("现在是" + year + "年" + month + "月" + day + "日" + hour
		        + "时" + minute + "分" + second + "秒" + milliSecond + "毫秒" + "星期" + weekday);
		
		System.out.println(CalendarUtil.getLastDayByYearMonth(2018, 10));
		System.out.println(CalendarUtil.getFirstDayByYearMonth(2018, 10));
		
		Date date = new SimpleDateFormat("yyyy-MM").parse("2018-10");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date));
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		System.out.println(ca.get(Calendar.YEAR));
		System.out.println(ca.get(Calendar.MONTH));
	}
	
	@Test
	public void test1() {
		java.sql.Date d = new java.sql.Date(new Date().getTime());
		System.out.println(d);
	}
}
