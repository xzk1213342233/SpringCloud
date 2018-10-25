package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 *  zuul  路由网关(api网关服务)
 *  
 *  无zuul的情况
 *  1、请求通过负载均衡，被转发到各个不同的服务实例上，运维人员需要手工维护这些路由规则与服务实例列表，维护会越来越难。
 *  2、我们需要一套机制能够很好的解决微服务架构中，对于微服务接口访问时各前置校验的冗余问题。
 *  
 *  实现请求路由、负载均衡、检验过滤等功能。同样也有与服务治理框架的结合、请求转发时的熔断机制、服务的聚合等一系列高级功能。
 * 
 *  Zuul的主要功能是路由转发和过滤器。路由功能是微服务的一部分，
 *  路由：负责将外部请求转发到具体的微服务实例上，是实现外部访问同一入口的基础。
 *  
 *  比如／api/user转发到到user服务，/api/shop转发到到shop服务。zuul默认和Ribbon结合实现了负载均衡的功能。
 * 
 *  pom.xml 文件中加入spring-boot-starter-web、spring-cloud-starter-netflix-eureka-client、spring-cloud-starter-netflix-zuul依赖
 *  
 *  在启动类上添加注解@EnableZuulProxy，开启zuul的功能
 *  
 *  面向服务的路由配置,需在配置文件中添加：
 *  zuul.routes.point-ribbon.path=/point-ribbon/**
 *  zuul.routes.point-ribbon.service-id=service-ribbon
*   zuul.routes.point-feign.path=/point-feign/**
*   zuul.routes.point-feign.service-id=service-feign
*   
*   ?    匹配任意单个字符                                           /point-ribbon/a,/point-ribbon/b,/point-ribbon/c
*   *    匹配任意数量的字符                                       /point-ribbon/a,/point-ribbon/ab,/point-ribbon/abc
*   **   匹配任意数量的字符，支持多级目录               /point-ribbon/a,/point-ribbon/a/b,/point-ribbon/aaa/b/c
*  
 *  以/point-ribbon/ 开头的请求都转发给service-ribbon服务；以/point-feign/开头的请求都转发给service-feign服务,起到路由的作用
 *  
 *  zuul.ignored-patterns  禁止网关路由的url表达式
 *  
 *  请求过滤：负责对请求的处理过程进行干预，是实现请求校验、服务聚合等功能的基础。
 *  另外zuul不仅只是路由，并且还能过滤，做一些权限控制、安全验证。
 *  新建一个MyFilter类，继承ZuulFilter抽象类，
 *  
 *  API网关服务优点：
 *  1、可以作为系统 的统一入口，屏蔽了系统内部各个微服务的细节
 *  2、可以与微服务治理框架结合，实现自动化的服务实例维护以及负载均衡路由的妆发
 *  3、可以实现接口权限校验与微服务业务逻辑的解耦
 *  4、通过服务网关中的过滤器，在各个生命周期中去检验请求的内容，将原本在对外服务的层做的校验前移，保证微服务的无状态性，同时降低了
 *     微服务的测试难度，让服务本身更集中关注业务逻辑的处理。
 * 
 * 
 *   过滤器异常处理：
 *   1、参看SendErrorFilter的shouldFilter方法，自定义MyErrorController(继承ErrorController)异常处理,
 *   2、自定义error类型的过滤器ErrorFilter，由于在请求生命周期的pre、route、post三个阶段中的异常抛出的时候
 *      都会进入error阶段的处理，所以可以创建一个error类型的过滤器来捕获这些异常信息。（参看ZuulServlet）
 * 
 *  禁用过滤器：
 *  zuul.<SimpleClassName>.<filterType>.disable=true  例：
 *  zuul.AccessFilter.pre.disable=true
 *  
 *  动态加载:具备动态更新内部逻辑的能力，如动态修改路由规则、动态添加/删除过滤器
 *  
 *  1、动态路由
 *     1.1、springCloudConfig 分布式配置文件动态刷新
 *  
 *  2、动态过滤器
 *     2.1、Groovy
 *   
 *  
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class SpringCloudZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudZuulApplication.class, args);
	}
}
