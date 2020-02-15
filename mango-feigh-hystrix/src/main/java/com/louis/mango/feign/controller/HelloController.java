package com.louis.mango.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.louis.mango.feign.client.EurekaServiceFeign;
import com.louis.mango.feign.client.EurekaServiceFeign2;
import com.louis.mango.feign.http.HttpResult;
import com.louis.mango.feign.page.PageRequest;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class HelloController  {
	
	@Autowired
	private EurekaServiceFeign eurekaServiceFeign;
	
	@Autowired
	private EurekaServiceFeign2 eurekaServiceFeign2;

	@GetMapping("/hello/{name}")
//	@HystrixCommand(fallbackMethod="helloFallback")	//出现错误时会调用括号里面的方法名的方法
	public String hello(@PathVariable String name) {
		return eurekaServiceFeign2.hello(name);
	}
	
	@GetMapping(value="/role/findAll")
	public HttpResult findAll() {
		return eurekaServiceFeign.findAll();
	}
	
	@PostMapping(value="/role/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return eurekaServiceFeign.findPage(pageRequest);
	}
	
	@GetMapping(value="/role/findRoleMenus")
	public HttpResult findRoleMenus(@RequestParam Long roleId) {
		return eurekaServiceFeign.findRoleMenus(roleId);
	}

}
