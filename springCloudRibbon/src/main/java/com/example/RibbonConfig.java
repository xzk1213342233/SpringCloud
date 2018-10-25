package com.example;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class RibbonConfig {
	
	/**
	 *  负载均衡策略(七种)
	 *    BestAvailableRule              选择一个最小的并发请求的server
	 *    AvailabilityFilteringRule      过滤掉那些因为一直连接失败的被标记为circuit tripped的后端server，并过滤掉那些高并发的的后端server（active connections 超过配置的阈值）
	 *    WeightedResponseTimeRule       根据响应时间分配一个weight，响应时间越长，weight越小，被选中的可能性越低。
	 *    RetryRule                      对选定的负载均衡策略机上重试机制。
	 *    RoundRobinRule                 roundRobin方式轮询选择server
	 *    RandomRule                     随机选择一个server
	 *    ZoneAvoidanceRule              复合判断server所在区域的性能和server的可用性选择server
	 *  
	 *    可以在配置文件中添加service-hello.ribbon.NFLoadBalancerRuleClassName=com.netflix.loadbalancer.RandomRule
	 *    也可以使用此种方式:
	 *               @Bean
	 *               public IRule ribbonRule() {
	 *                 return new RandomRule();
	 *               }             
	 *    
	 * 
	 */
	
	
	@Bean
  //@MyLoadBalanced  //自定义拦截器
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	//Ribbon提供多种负载策略有IRule进行管理，上面设置的是随机的策略，还有其他的方式，
//    @Bean
//    public IRule ribbonRule(){
//    	//使用自定义负载策略
//        return new MyRule();
//    }
}
