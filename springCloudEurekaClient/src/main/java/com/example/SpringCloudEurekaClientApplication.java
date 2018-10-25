package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *   服务提供者
 *  
 *   构建一个eureka服务提供者：
 *  1、在pom.xml中引入spring-cloud-starter-netflix-eureka-client   jar文件
 *  2、在启动类上添加@@EnableEurekaClient 注解激活服务客户端
 *  3、在application 配置文件中配置 关于客户端的一些信息
 *  
 * 当client向server注册时，它会提供一些元数据，例如主机和端口，URL，主页等。
 * Eureka server 从每个client实例接收心跳消息。 如果心跳超时，则通常将该实例从注册server中删除
 * 
 *
 */
@EnableEurekaClient
@SpringBootApplication
public class SpringCloudEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaClientApplication.class, args);
	}
}
