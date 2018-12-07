package com.dengjian.gomars;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
 * spring-boot 入口类
 * 1) @SpringBootApplication
 */
@SpringBootApplication
//@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.dengjian.gomars"})  // 组件扫描
//@MapperScan(basePackages= {"com.dengjian.gomars"})  // spring-mybatis Mapper接口扫描, @MapperScan存在的问题，会把basepackages下所有接口都扫描进入，包括service
@EnableCaching  // 缓存
public class GomarsApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(GomarsApplication.class);

	public static void main(String[] args) {
		logger.info("======= 启动GomarsApplication-开始  ======");
		SpringApplication.run(GomarsApplication.class, args);
		logger.info("======= 启动GomarsApplication-完成  ======");
	}
}
