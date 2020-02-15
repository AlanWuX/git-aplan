package com.louis.mango.feign;

import javax.servlet.Servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

@SpringCloudApplication
@EnableFeignClients		//开启feign
//@EnableCircuitBreaker	//开启hystrix
//@EnableHystrix
@EnableEurekaClient
@EnableHystrixDashboard
public class MangoFeighHystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangoFeighHystrixApplication.class, args);
	}
	
	//此配置为了服务监控而配置, 与服务容错本身无关
	//ServletRegistrationBean因为springboot的默认路径不是"/hystrix.stream"
	//只要在自己的项目里配置删改面的servlet就可以了
	@Bean
	public ServletRegistrationBean getServlet() {
		HystrixMetricsStreamServlet stremServlet = new HystrixMetricsStreamServlet();
		ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean<>(stremServlet);
		registrationBean.setLoadOnStartup(1);
		registrationBean.addUrlMappings("/hystrix.stream");
		registrationBean.setName("HystrixMetricsStreamServlet");
		return registrationBean;
	}

}
