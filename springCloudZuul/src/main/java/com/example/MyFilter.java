package com.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class MyFilter extends  ZuulFilter{

	//返回一个boolean类型来判断该过滤器是否要执行。我们可以通过此方法来指定过滤器的有效范围。
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 过滤器的具体逻辑。在该函数中，我们可以实现自定义的过滤逻辑，来确定是否要拦截当前的请求,
	 * 不对其进行后续的路由，或是在请求路由返回结果之后，对处理结果做一些加工等。
	 */
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		System.out.println("pre过滤器===="+String.format("%s &amp;gt;&amp;gt;&amp;gt; %s", request.getMethod(), request.getRequestURL().toString()));
		Object name = request.getParameter("name");
		if(name == null) {
			System.out.println("name is empty");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			try {
			   ctx.getResponse().getWriter().write("name is empty");
			}catch (Exception e){}
			return null;
		}else if("xzk".equalsIgnoreCase(name.toString())) {
				int aa = 1/0;
		}else if("xzk123".equalsIgnoreCase(name.toString())) {
			throw 	new RuntimeException("xzk123  不行啊");
		}
		System.out.println("ok");
		return null;
	}

	/**
	 *  filterType：该函数需要返回一个字符串来代表过滤器的类型，而这个类型就是在HTTP请求过程中定义的各个阶段。
	  *   在Zuul中默认定义了四种不同生命周期的过滤器类型，具体如下：
	 *	pre：可以在请求被路由之前调用。目的是在进行请求路由之前做一些前置加工，比如请求校验等。
	 *	routing：在路由请求时候被调用。具体处理内容就是将外部请求转发到具体的服务实例上，当服务实例将请求结果都返回之后，此阶段完成。，
	 *	post：在routing和error过滤器之后被调用。可以对处理结果进行加工或者转换内容
	 *	error：处理请求时发生错误时被调用。该阶段在上述三个阶段发生异常时触发，但是它的最后流向还是post过滤器，
	 *         因为他需要通过post过滤器将最终结果返回给请求客户端
	 *     
	 *  zuulServlet.class       
	 *         
	 *  sb的核心过滤器（spring-cloud-netflix-zuul-2.0.1.RELEASE.jar）会在api网关服务启动的时候被自动加载和启用。   
	 *  
	 *      顺序            过滤器                                                  功能
	 *      -3        ServletDetectionFilter            标记处理servlet的类型
	 *      -2        Servlet30WrapperFilter            包装HttpServletRequest请求
	 *      -1        FormBodyWrapperFilter             包装请求体
	 *       1        DebugFilter                       标记调试标志
	 *       5        PreDecorationFilter               处理请求上下文供后续使用
	 *       
	 *       10       RibbonRoutingFilter               serviceId请求转发
	 *       100      SimpleHostRoutingFilter           url请求转发
	 *       500      SendForwardFilter                 forward请求转发
	 *       
	 *       0        SendErrorFilter                   处理有错误的请求相应
	 *       1000     SendResponseFilter                处理正常处理的请求相应
	 *       
	 *       
	 *       
	 *       
	 *       
	 *        
	 *      
	 *      
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	//filterOrder：通过int值来定义过滤器的执行顺序，数值越小优先级越高。
	@Override
	public int filterOrder() {
		return 0;
	}

}
