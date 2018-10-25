package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
/**
 *  Bus  消息总线
 *    支持 RabbitMQ（http://localhost:15672/） 和 Kafka
 *  
 *  
 *  Spring Cloud Bus 将分布式的节点用轻量的消息代理连接起来。它可以用于广播配置文件的更改或者服务之间的通讯，也可以用于监控。
 *  
 *  消息代理：是一种消息验证、传输、路由的架构模式。
 *  它在应用程序之间起到通信调度并最小化应用之间的依赖的作用。使的应用程序可以高效的解耦通信过程。
 *  
 *  消息代理的场景：
 *  1、将小西路由的一个或者多个目的地
 *  2、消息转化为其他的表现方式
 *  3、执行消息的聚集、消息的分解、并将结果发送到她们的目的地，然后重新组合响应返回给消息用户
 *  4、调用web服务来检索数据
 *  5、响应事件或错误
 *  6、使用发布-订阅模式来提供内容或基于猪蹄的消息路由
 *  
 *  整合RabbitMQ,在pom.xml添加spring-cloud-starter-bus-amqp  依赖。在application.properties中配置关于rabbitMq的连接和用户信息
 *  Sender --Receiver--RabbitConfig
 * 
 *  举个例子:
 *  如果我们去代码仓库将foo的值改为“foo version 4”，即改变配置文件foo的值。如果是传统的做法，需要重启服务，才能达到配置文件的更新。
 *  此 时，我们只需要发送post请求：http://localhost:7003/actuator/bus-refresh，你会发现config-client也会重新读取配置文件
 *  
 *  要实现此功能需要修改配置文件:
 *  Spring boot 2.0的改动较大，/bus/refresh全部整合到actuador里面了，所以之前1.x的management.security.enabled全部失效，
 *  不适用于2.0,适用于2.0的配置是这样的:management.endpoints.web.exposure.include: bus-refresh
 *  
 *  另外需要在方法上加上 @RefreshScope 注解，就是说附带@Value的页面加上此注解
 *  
 *  请求刷新的页面由原来1.5.x的localhost:7003/bus/refresh变成：http://localhost:7003/actuator/bus-refresh
 *  可以在启动日志观察到此路径
 *  
 *  
 *  另外，/actuator/bus-refresh接口可以指定服务，即使用”destination”参数，比如 “/actuator/bus-refresh?destination=CONFIG-CLIENT:**” 
 *  即刷新服务名为CONFIG-CLIENT的所有服务，不管ip。
 *  
 *  当git文件更改的时候，通过pc端用post 向端口为8890的config-bus发送请求/actuator/bus-refresh；此时8890端口会发送一个消息，
 *  由消息总线向其他服务传递（如：config-client），从而使整个微服务集群都达到更新配置文件。
 * 
 * 
 *  spring.cloud.bus.trace.enabled=true，如果那样做的话，
 *  那么Spring Boot TraceRepository（如果存在）将显示每个服务实例发送的所有事件和所有的ack,
 *   
 */
@SpringBootApplication
public class SpringCloudBusApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringCloudBusApplication.class, args);
		context.getBean(Sender.class).send();
	}
}
