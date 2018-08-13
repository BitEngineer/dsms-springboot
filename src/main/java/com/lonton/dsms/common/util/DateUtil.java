package com.lonton.dsms.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期工具类
 * @author 郭宇航
 */
public final class DateUtil {
	public static final String DEFAULT_PATTERN = "yyyy-MM-dd";
	public static final String PATTERN_17 = "yyyyMMddhhmmssSSS";
	public static final String PATTERN_19 = "yyyy-MM-dd hh:mm:ss";
	
	private static ThreadLocal<Map<String, DateFormat>> formats = new ThreadLocal<Map<String,DateFormat>>();
	
	/**
	 * 格式化当前日期
	 * @param pattern
	 * @return
	 */
	public static String format(String pattern){
		Date date = new Date();
		return format(date, pattern);
	}
	
	/**
	 * 格式化日期到默认格式
	 * @param date
	 * @return
	 */
	public static String format(Date date){
		return format(date, DEFAULT_PATTERN);
	}
	
	/**
	 * 格式化日期
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern){
		DateFormat format = getFormat(pattern);
		return format.format(date);
	}
	
	/**
	 * 解析日期字符串
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String date) throws ParseException{
		return parse(date, DEFAULT_PATTERN);
	}
	
	/**
	 * 解析指定格式日期字符串
	 * @param date
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String date, String pattern) throws ParseException{
		DateFormat format = getFormat(pattern);
		return format.parse(date);
	}
	
	/**
	 * 线程安全地获取Dateformat对象
	 * @param pattern
	 * @return
	 */
	private static DateFormat getFormat(String pattern){
		if(null != formats.get()){
			Map<String, DateFormat> m = formats.get();
			if(m.containsKey(pattern)){
				return m.get(pattern);
			} else {
				DateFormat format = new SimpleDateFormat(pattern);
				m.put(pattern, format);
				return format;
			}
		} else {
			Map<String, DateFormat> m = new HashMap<String, DateFormat>();
			DateFormat format = new SimpleDateFormat(pattern);
			m.put(pattern, format);
			formats.set(m);
			return format;
		}
	}
	
}
