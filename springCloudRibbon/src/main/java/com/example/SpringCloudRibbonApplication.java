package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/***
* 客户端负载均衡 
* RestTemplate+Ribbon服务消费
* Ribbon是一个基于HTTP和TCP的客户端负载均衡工具。通过Ribbon的封装，我们可以轻松的将面向服务的Rest模板请求自动转换为客户端负载均衡的服务调用。
* 
* 什么是负载均衡？常见的负载均衡实现有哪些？客户端负载均衡和服务端负载均衡的区别？
* Ribbon实现客户端负载均衡：
* 1、在pom.xml 配置文件中加入spring-cloud-starter-netflix-ribbon、spring-cloud-starter-netflix-eureka-client、spring-boot-starter-web依赖
* 2、服务提供者只需要启动多个服务实例并注册到一个注册中心或是多个相关联的服务注册中心上。
* 3、在启动类上添加@EnableDiscoveryClient向服务中心注册
* 4、服务消费者直接通过调用被@LoadBalanced (LoadBalancerAutoConfiguration.class) 注解修饰过的RestTemplate来实现面向服务接口的调用
* 可以实现IRule接口自定义负载策略，也可以实现IRule接口自定义负载策略
* 
* 
* 
* 
* 
*/
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudRibbonApplication.class, args);
	}
}
