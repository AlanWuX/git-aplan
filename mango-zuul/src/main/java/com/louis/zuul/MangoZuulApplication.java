package com.louis.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import com.netflix.ribbon.proxy.annotation.Hystrix;

@SpringBootApplication
@EnableZuulProxy	//标识api网关
@EnableEurekaClient
public class MangoZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangoZuulApplication.class, args);
	}

}
