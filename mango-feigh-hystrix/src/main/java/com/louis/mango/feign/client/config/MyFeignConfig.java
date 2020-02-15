package com.louis.mango.feign.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
public class MyFeignConfig {

	/**
	 * feign打印日志等级
	 * @return
	 */
	@Bean
	Logger.Level feignLoggerLeval(){
		return Logger.Level.FULL;
	}
}
