package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hystrix  断路器
 * 
 * 在微服务架构中，根据业务来拆分成一个个的服务，服务与服务之间可以相互调用（RPC），在Spring Cloud可以用RestTemplate+Ribbon和Feign
 * 来调用。为了保证其高可用，单个服务通常会集群部署。由于网络原因或者自身的原因，服务并不能保证100%可用，如果单个服务出现问题，调
 * 用这个服务就会出现线程阻塞，此时若有大量的请求涌入，Servlet容器的线程资源会被消耗完毕，导致服务瘫痪。服务与服务之间的依赖性，故
 * 障会传播，会对整个微服务系统造成灾难性的严重后果，这就是服务故障的“雪崩”效应
 * 为了解决这个问题，业界提出了断路器模型。
 * 
 * ribbon实现断路器：
 * 
 * 在pom.xml 配置文件中加入spring-cloud-starter-netflix-ribbon、spring-cloud-starter-netflix-eureka-client、
 * spring-cloud-starter-netflix-hystrix、spring-boot-starter-web依赖
 * 
 * 在程序的启动类上添加加@EnableHystrix注解开启Hystrix
 * 还可以自定义EnableHystrix，参看MyCommand.class
 * 
 * 在RibbonHelloService类的sayHello方法上加上@HystrixCommand注解。该注解对该方法创建了熔断器的功能，并指定了fallbackMethod熔断方法sayHelloError，
 * 
 * 当 SERVICE-HELLO工程不可用的时候，service-hystrix调用 service-hello的API接口时，会执行快速失败，直接返回一组字符串，
 * 而不是等待响应超时，这很好的控制了容器的线程阻塞。
 * 断路打开后，可用避免连锁故障，fallback方法可以直接返回一个固定值。
 * 
 * Feign实现断路器：
 * 
 * Feign是自带断路器的，在Spring Cloud中，它没有默认打开。需在配置文件加上：feign.hystrix.enabled=true 打开它
 * 
 *   需要在FeignHelloService接口的注解中加上fallback的指定类FeignHelloServiceImpl
 * FeignHelloServiceImpl需要实现FeignHelloService接口,并注入到spring容器中。
 * 
 * 
 * Hystrix Dashboard (断路器：Hystrix 仪表盘)（未测通）
 * 
 *  在pom.xml引入spring-cloud-starter-netflix-hystrix-dashboard的起步依赖：
 *  
 *  在主程序启动类中加入@@EnableHystrixDashboard注解，开启hystrixDashboard
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
public class SpringCloudHystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudHystrixApplication.class, args);
	}
}
