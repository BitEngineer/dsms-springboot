package com.lonton.dsms.common.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * JSON工具包
 */
public class JsonUtil {
	public static String toPrettyJsonString(Object obj) throws Exception {
		return JSON.toJSONString(obj, getSerializeConfig(), SerializerFeature.PrettyFormat);
	}

	public static String toJsonString(Object obj) throws Exception {
		if (null == getSerializeConfig()) {
			return JSON.toJSONString(obj);
		}
		SerializerFeature feature = SerializerFeature.DisableCircularReferenceDetect;
		return JSON.toJSONString(obj, getSerializeConfig(), feature);
	}

	public static <T> T parseObject(String json, Class<T> cls) throws Exception {
		return JSON.parseObject(json, cls);
	}

	public static <T> T parseObject(String json, TypeReference<T> type) throws Exception {
		return JSON.parseObject(json, type);
	}

	public static <K, V> Map<K, V> parseMap(String json) throws Exception {
		return JSON.parseObject(json, new TypeReference<Map<K, V>>() {
		});
	}

	public static <T> List<T> parseArray(String json, Class<T> cls) throws Exception {
		return JSON.parseArray(json, cls);
	}

	private static SerializeConfig sc = null;

	private static SerializeConfig getSerializeConfig() {
		if (null == sc) {
			sc = ApplicationContextUtil.context == null ? null : ApplicationContextUtil.context.getBean(SerializeConfig.class);
		}
		return sc;
	}
}
