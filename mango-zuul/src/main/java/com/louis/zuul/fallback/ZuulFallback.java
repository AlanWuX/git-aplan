package com.louis.zuul.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class ZuulFallback implements FallbackProvider{

	@Override	//获取路由, application.name
	public String getRoute() {
		return "mango-feign-hystrix";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		System.out.println("进来了!!!!!!");
		System.out.println("传过来的route: " + route);
		System.out.println("出错了: " + cause.getMessage());
		
		return new ClientHttpResponse() {
			//请求响应头信息, contentType和字符类型
			@Override
			public HttpHeaders getHeaders() {
				//返回类型为json,字符集为u8
				HttpHeaders header = new HttpHeaders();
				header.setContentType(MediaType.APPLICATION_JSON_UTF8);
				System.out.println("头头头头头头: " + header);
				return header;
			}
			
			//响应体,具体返回内容,熔断后返回的信息
			@Override
			public InputStream getBody() throws IOException {
				System.out.println("我要返回, 我要返回, 我要返回");
				String returnValu = "error lo";  
				return new ByteArrayInputStream(returnValu.getBytes());
			}
			
			//返回文字描述
			@Override
			public String getStatusText() throws IOException {
				return HttpStatus.BAD_REQUEST.getReasonPhrase();
			}
			
			//返回状态码
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.BAD_REQUEST;
			}
			
			//返回二进制状态码
			@Override
			public int getRawStatusCode() throws IOException {
				return 0;
			}
			
			//关闭释放资源
			@Override
			public void close() {
				
			}
		};
	}

}
