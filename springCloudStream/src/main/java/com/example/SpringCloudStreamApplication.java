package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.support.MessageBuilder;

/** 
 *  springCloudStream 是一个用来为微服务应用构建消息驱动能力的框架。
 *  springCloudStream 为一些供应商的消息中间件提供了个性化的自动化配置实现，并且引入发布-订阅、消费组、分区这三个核心概念。
 *  springCloudStream 可以有效的简化开发人员对消息中间件的使用复杂度，让系统开发人员可以有更多精力关注于核心业务逻辑的处理。
 *  
  *     支持RabbitMq ,Kafka；
  *     使用RabbitMq， 在pom依赖中引入spring-cloud-starter-stream-rabbit
 *      
  *     可以创建一个接收者  SinkReceiver ，启动项目后，打开本地的rabbitmq,可以查看到此链接，点击控制台中Queues下的Publish  message 发送一条消息测试。
 * 
  *     绑定器（Binder） 是SpringCloudStream中一个非常重要的概念，如果没有绑定器，springboot应用要直接与消息中间件进行信息交互，由于
  *     各消息中间件构建的初衷不同，所以他们在实现细节上会有较大的差异，因此应用与消息中间件的耦合度太大。通过定义绑定器作为中间层，完美的实现了
  *     应用程序和消息中间件之间的隔离，通过向应用暴露统一的Channel通道，使得应用程序不需要再考虑各种不同的消息中间件的实现，当需要升级消息中间件时，
  *     我们只需要更换它们对应的Binder绑定器而不需要修改任何应用程序的逻辑。
 *     
 *  springCloudStream 提供了对RabbitMq默认的自动化配置，你也可以在application配置文件中对默认的配置进行修改。
 *  
  *     发布-订阅
 *   SpringCloudStream 中的消息通信方式遵循了发布-订阅模式，当一条消息被投递到消息中间件之后，它会通过共享的topic主题进行广播，消息消费者在订阅
  *      的主题中收到它并触发自身的业务逻辑处理。topic主题其实就是SpringCloudStream中的一个抽象的概念，用来代表发布共享消息给消费者的地儿。
 * 
  *     使用两个端口启动应用程序，可以在rabbit控制面板上观察情况，Exchanges---Bingdings
 *   
  *     消费组
  *     虽然SpringCloudStream通过发布-订阅模式将消息生产者与消费者做了很好的解耦合，基于相同主题的消费者可以轻松的进行扩展，但是这些扩展都是针对
  *     不同的应用实例。在现实的微服务架构中，我们的每一个微服务应用为了实现高可用和负载均衡，实现上都会集群部署，在很多情况下，消息生产者发生消息
  *     给某个具体的服务时，只希望被消费一次，如果集群部署的话，肯定会重复消费，为了解决这个问题，SpringCloudStream提供了消费组的概念。
 *   spring.cloud.stream.bindings.input.group=xzk-A 属性为应用指定一个组名，这样这个应用的多个实例在接收到消息的时候，只会有
  *     一个成员真正的收到消息并进行处理。默认规则是轮询。
 *     
  *     消息分区  
  *     消费组无法控制消息具体被哪个实例进行消费，也就是说，对于同一条消息，它多次到达之后可能是由不同的实例进行消费的，但是对于
  *     一些业务场景，需要对一些具有相同特征的消息设置每次都被同一个消费实例处理，为了解决此问题，springCloudStream引入消息分区概念，
  *     当生产者将消息数据发送给多个消费者实例时，保证拥有共同特性的消息数据始终是由同一个消费者实例接收和处理。
 *     
  *    在application 中配置 消费者 和生产者的配置参数 
 *     在SinkReceiver中新增listen方法用于接收规则为payload的信息，启动两个消费者实例，和一个生产者实例，调用MyController测试。
 *        
 *     
  *     来个消息生产者SinkSender ，需要在application配置文件中添加
 *   spring.cloud.stream.bindings.input.destination=xzk
 *   spring.cloud.stream.bindings.output.destination=xzk
 *   
  *     同样也可以使用测试用例（SpringCloudStreamApplicationTests）中的方式发送消息
 *     
  *     消息反馈
  *     很多时候在处理完输入消息之后，需要反馈一个消息给对方，这时候可以通过@SendTo 注解来指定返回内容的输出通道。
  *     例如：SendToReceiver 、 SendToSender ，在测试用例发送消息测试。
 *     
 *     
 *     
 *     
 *
 */
@SpringBootApplication
public class SpringCloudStreamApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringCloudStreamApplication.class, args);
		context.getBean(SinkSender.class).output().send(MessageBuilder.withPayload("hello world").build());
	}
}
