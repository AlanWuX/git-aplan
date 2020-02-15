package com.louis.zuul.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 有四种过滤器
 * pre: 该过滤器再请求被路由之前调用, 我们可利用这种过滤器实现身份验证, 再集群中选择请求的微服务, 记录调式信息等
 * routing: 该过滤器将请求路由到微服务. 这种过滤器永固构建发送给微服务的请求, 并使用Apache HttpClient或Netfilx Ribbon请求微服务
 * post: 该过滤器再路由到微服务以后执行. 这种过滤器可用来为响应添加标准的HTTP Header, 收集统计信息和指标, 将响应从微服务发送给客户端等.
 * error: 再其他阶段发生错误时执行该过滤器
 * @author AlanW
 *
 */
@Component
public class MyFilter extends ZuulFilter {
	private static Logger log = LoggerFactory.getLogger(MyFilter.class);

	@Override
	//定义filter的类型, 有pre, rout, post, error四种类型
	public String filterType() {
		return "pre";
	}
	
	//定义filter的顺序, 数字越小表示顺序越高, 越先执行
	@Override
	public int filterOrder() {
		return 0;
	}
	
	//表示十分需要执行该filter, true 表示执行, false表示不执行
	@Override
	public boolean shouldFilter() {
		return true;
	}

	//filter需要执行的具体操作
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String token = request.getParameter("token");
		System.out.println(token);
		if(token==null) {
			log.warn("there is no request token");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			try {
				ctx.getResponse().getWriter().write("there is no request token");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		log.info("ok");
		return null;
	}
	
	
	

	

}
