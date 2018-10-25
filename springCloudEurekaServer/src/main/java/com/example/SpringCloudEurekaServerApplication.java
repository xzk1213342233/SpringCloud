package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * EurekaServer:服务注册中心
 * EurekaClient:服务注册与发现
 *  
 *  服务治理：
 *  1、构建服务注册中心
 *  2、服务注册与发现
 *  3、Eureka的基础架构
 *  4、Eureka的服务治理机制
 *  5、Eureka的配置
 *  
 *  
 *  构建一个eureka服务注册中心：
 *  1、在pom.xml中引入spring-cloud-starter-netflix-eureka-server   jar文件
 *  2、在启动类上添加@EnableEurekaServer 注解开启注册中心功能
 *  3、在默认情况下服务注册中心也会把自己作为客户端来尝试注册自己，可以在application配置文件中禁用此行为
 *  
 *  
 *    高可用注册中心（集群三高？）
 *     1、修改application配置文件
 *     2、在C:\Windows\System32\drivers\etc\hosts 中添加server1 和 server2 的转换
 *        127.0.0.1 server1
 *        127.0.0.1 server2
 *     
 *     3、修改eurekaClient中的eureka.client.service-url.defaultZone为
 *        eureka.client.service-url.defaultZone=http://localhost:8111/eureka/,http://localhost:8112/eureka/
 *    
 *    基础架构：服务注册中心、服务提供者、服务消费者
 *    
 *    服务提供者(eureka_client)：
 *    1、服务注册
 *    2、服务同步:多个服务注册中心时，由于服务注册中心的相互注册，可保证服务高可用且服务同步。
 *    3、服务续约:服务注册后，服务提供者会维护一个心跳告诉eureka-server,i feel good!
 *              服务续约有两个重要属性，可以在applicatione配置文件中进行配置，
 *              eureka.instance.lease-renewal-interval-in-seconds=10
 *              eureka.instance.lease-expiration-duration-in-seconds=30
 *              此时，我们可以在注册中心的配置文件中关闭自我保护机制，当服务不可用时，注销并清除。
 *              
 *     服务消费者：
 *     1、获取服务:启动服务消费者时，会发送一个rest请求给注册中心，来获取上面注册的服务请求。
 *     2、服务调用:ribbon   feign
 *     3、服务下线
 *     
 *    服务注册中心：
 *    1、失效剔除：eureka.server.eviction-interval-timer-in-ms  定时任务，默认60s，将清单中超时的服务剔除
 *    2、自我保护：由于网络不稳定等因素，注册中心将当前实例注册信息保护起来，让这些信息不会过期。eureka.server.enable-self-preservation=false ，默认为true
 *    
 * 
 *
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class SpringCloudEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaServerApplication.class, args);
	}
}
