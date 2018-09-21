package com.lonton.dsms;

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
@ComponentScan(basePackages={"com.lonton.dsms"})  // 组件扫描
//@MapperScan(basePackages= {"com.lonton.dsms"})  // spring-mybatis Mapper接口扫描, @MapperScan存在的问题，会把basepackages下所有接口都扫描进入，包括service
@EnableCaching  // 缓存
public class DsmsApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(DsmsApplication.class);

	public static void main(String[] args) {
		logger.info("======= 启动DsmsApplication-开始  ======");
		SpringApplication.run(DsmsApplication.class, args);
		logger.info("======= 启动DsmsApplication-完成  ======");
	}
}
