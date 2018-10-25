package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *  默认情况下configServer的端口号为8888
 *  
 *  创建bootstrap.properties配置文件
 *  在配置文件中添加数据：
 *  server.port=8889
 *  spring.application.name=config-client
 *     对应配置文件规则中的{label}部分
 *  spring.cloud.config.label=master
 *     对应配置文件规则中的{profile}部分
 *  spring.cloud.config.profile=dev
 *     配置中心config-server的地址
 *  spring.cloud.config.uri= http://localhost:8887/
 *  
 *  可以通过此配置文件自定义configServer端口号
 *  
 *  HTTP服务具有以下格式的资源：
 *  /{application}/{profile}[/{label}]
 *  /{application}-{profile}.yml
 *  /{label}/{application}-{profile}.yml
 *  /{application}-{profile}.properties
 *  /{label}/{application}-{profile}.properties
 *  
 *  
 *  通过服务名称消费配置中心文件，修改配置文件：
 *  eureka.client.service-url.defaultZone= http://localhost:8111/eureka/
 *  spring.cloud.config.discovery.enabled=true 是从配置中心读取文件。
 *  spring.cloud.config.discovery.serviceId=config-server 配置中心的servieId，即服务名。
 *  
 *  在读取配置文件不再写ip地址，而是服务名，这时如果配置服务部署多份，通过负载均衡，从而实现高可用。
 * ------------------------------------------------------------------------------------------ 
 *  
 *  Spring Security加密
 *  
 *  在服务端添加Security  jar文件，在配置文件中配置
 *   security.user.name=xzk
 *   security.user.password=xzk1213
 *   
 *  在客户端配置文件中添加：
 *  spring.cloud.config.username=xzk
 *  spring.cloud.config.password=xzk1213
 *  
 *  -----------------------------------------------------------------------------------
 *  失败快速响应与重试
 *  快速失败：
 *  在配置bootstrap.properties文件中添加spring.cloud.config.failFast=true
 *  当config-server配置有误时，避免多等待一些前置的加载时间，实现快速返回失败信息。
 *  失败重试：
 *  确保已经配置spring.cloud.config.failFast=true
 *  添加spring-retry 和 spring-boot-starter-aop依赖,默认重试六次，可以自定义:
 *   spring.cloud.config.retry.multiplier 初始重试间隔时间（毫秒），默认1000毫秒
 *   spring.cloud.config.retry.initial-interval 下一间隔个乘数，默认1.1，所以间隔是1000毫秒时，下一次失败后间隔为1100毫秒。
 *   spring.cloud.config.retry.max-interval 最大间隔时间，默认2000毫秒
 *   spring.cloud.config.retry.max-attempts 最大重试次数，默认为6次
 *   
 *   --------------------------------------------------------------------------------------------------
 *   
 *  
 *  
 *  
 */

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigClientApplication.class, args);
	}
}
