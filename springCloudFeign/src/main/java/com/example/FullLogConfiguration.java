package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

/**
 * 
 * 在配置文件中添加  logging.level.com.example.HelloService=DEBUG
 *
 */

@Configuration
public class FullLogConfiguration {

	@Bean
	Logger.Level feignLoggerLevel(){
		return Logger.Level.FULL;
	}
	
	
}
