package com.lonton.dsms.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 对象值
 * 
 * @author 郭宇航
 */
public class ObjectUtil {
	/**
	 * 设置标签值
	 * 
	 * @param obj
	 * @param fieldName
	 * @param fieldValue
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void setFieldValue(Object obj, String fieldName, String fieldValue)
			throws SecurityException, NoSuchMethodException, NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		if (obj instanceof Map) {
			((Map) obj).put(fieldName, fieldValue);
			return;
		}
		Class clazz = obj.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			if (field.getName().equals(fieldName)) {
				field.setAccessible(true);
				field.set(obj, fieldValue);
				return;
			}
		}

		String fieldNameSet = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

		for (Method method : clazz.getDeclaredMethods()) {
			Class[] pTypes = method.getParameterTypes();
			if (method.getName().equals(fieldNameSet) && 1 == pTypes.length) {
				method.setAccessible(true);
				method.invoke(obj, getFieldType(pTypes[0].getClass(), fieldValue));
			}
		}
	}

	/**
	 * 获取对象对应属性的值
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("rawtypes")
	public static Object getFieldValue(Object obj, String fieldName) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (obj instanceof Map) {
			return ((Map) obj).get(fieldName);
		}

		Class clazz = obj.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			if (field.getName().equals(fieldName)) {
				field.setAccessible(true);
				return field.get(obj);
			}
		}

		String fieldNameSet = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

		for (Method method : clazz.getDeclaredMethods()) {
			Class[] pTypes = method.getParameterTypes();
			if (method.getName().equals(fieldNameSet) && 0 == pTypes.length) {
				method.setAccessible(true);
				return method.invoke(obj);
			}
		}
		return null;
	}

	/**
	 * 转换属性类型
	 * 
	 * @param type
	 * @param fieldValue
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static Object getFieldType(Class type, String fieldValue) {
		Object fieldValueSet = null;
		if (type.equals(int.class) || type.equals(Integer.class)) {
			fieldValueSet = Integer.parseInt(fieldValue);
		} else if (type.equals(Double.class)) {
			fieldValueSet = Double.parseDouble(fieldValue);
		} else {
			fieldValueSet = fieldValue;
		}
		return fieldValueSet;
	}

	/**
	 * 将对象转化为int
	 * 
	 * @param v
	 * @return
	 */
	public static int toInt(Object v) {
		if (null == v) {
			return 0;
		}
		if (v instanceof Integer) {
			return (Integer) v;
		}
		return Integer.parseInt(toString(v));
	}

	/**
	 * 将对象转化为String
	 * 
	 * @return
	 */
	public static String toString(Object v) {
		if (null == v) {
			return null;
		}
		if (v instanceof String) {
			return (String) v;
		}
		return v.toString();
	}

	public static <T> T deepCopy(T o, Class<T> clazz) throws Exception {
		String s = JsonUtil.toJsonString(o);
		return JsonUtil.parseObject(s, clazz);
	}

	public static <K, V> Map<K, V> copyMap(Map<K, V> map) {
		Map<K, V> newMap = new HashMap<K, V>();
		for (K key : map.keySet()) {
			newMap.put(key, map.get(key));
		}
		return newMap;
	}

	public static <K, V> Map<K, V> copyMap(Map<K, V> target, Map<K, V> source) {
		for (K key : source.keySet()) {
			target.put(key, source.get(key));
		}
		return target;
	}

	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "").toUpperCase();
		return uuid;
	}
}
