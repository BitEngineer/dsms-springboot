package com.lonton.dsms.cache;

import java.util.List;

import org.springframework.cache.ehcache.EhCacheCacheManager;

import com.lonton.dsms.common.util.ApplicationContextUtil;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


public class EhcacheUtils {
	
	public static Cache getCacheByName(String cacheName) {
		EhCacheCacheManager ehCachecacheManager = ApplicationContextUtil.getBean(EhCacheCacheManager.class);
		CacheManager cacheManager = ehCachecacheManager.getCacheManager();
		Cache cache = cacheManager.getCache(cacheName);
		return cache;
	}
	
	public static <T> void putToCache(String cacheName, String key, T value) {
		Cache cache = getCacheByName(cacheName);
		cache.put(new Element(key, value));
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getFromCache(String cacheName, String key) {
		Cache cache = getCacheByName(cacheName);
		return (T) cache.get(key).getObjectValue();
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> getAllKeyFromCache(String cacheName) {
		Cache cache = getCacheByName(cacheName);
		return cache.getKeys();
	}
	
}
