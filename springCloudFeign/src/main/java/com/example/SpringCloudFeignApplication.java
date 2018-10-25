package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
/**
 * Feign 服务消费（声明式服务调用）
 * 
 * Feign是一个声明式的伪Http客户端，它使得写Http客户端变得更简单。使用Feign，只需要创建一个接口并注解。它具有可插拔的注解特性，
  *  可使用Feign 注解和JAX-RS注解,Feign支持可插拔的编码器和解码器。Feign默认集成了Ribbon，并和Eureka结合，默认实现了负载均衡的效果。
 *  
 *  简而言之：
 * 1.Feign 采用的是基于接口的注解
 * 2.Feign 整合了ribbon
 *
  * pom文件引入Feign的起步依赖spring-cloud-starter-openfeign、
  * Eureka的起步依赖spring-cloud-starter-netflix-eureka-client、
  * Web的起步依赖spring-boot-starter-web
  * 
  * 
  * 在程序的启动类上添加@EnableFeignClients注解开启Feign的功能：
 *
 * 定义一个feign接口(HelloService.java)，通过@ FeignClient（“服务名”），来指定调用哪个服务。
 * 比如在代码中调用了service-hello服务的“/hello”接口
 * 
 * 由于注解绑定服务接口时，我们几乎可以从服务提供方复制，构建出相应的服务客户端接口，既然是复制，那我们可以通过继承特性实现接口复用。
 *  springCloudEurekaClientApi  提供公用接口，服务提供方需要继承此接口，对外提供服务；服务客户端需要继承此接口，调用相应服务。
 *  优点：接口定义共享，构建期接口绑定。减少客户端服务绑定配置，方便接口定义和依赖共享
 *  缺点：牵一发动全身，谁改打死谁！！！
 *
 *
 * 默认实现了重试机制（Hystrix默认超时时间 --HystrixCommandProperties）
 *  在配置文件中添加相应的配置信息。
 *  
 *  禁用Hystrix功能的方法：
 *  1、DisableHystrixConfiguration
 *  2、feign.hystrix.enabled=false
 *  
 *  服务降级
 *  1、编写服务降级类 HelloServiceFallback
 *  2、通过@FeignClient中的fallback指定服务经济类
 * 
 * 请求压缩（支持对请求和响应进行GZIP压缩，以减少通信过程中的性能损耗）----没测
 *  需要在配置文件开启请求与响应的压缩功能：
 *  feign.compression.request.enable=true
 *  feign.compression.request.mime-types=text/xml,application/xml,application/json
 *  feign.compression.request.min-requset-size=2048
 *  feign.compression.response.enable=true
 *
 * 日志配置
 *  
 *
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SpringCloudFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudFeignApplication.class, args);
	}
}
