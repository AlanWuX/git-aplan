package com.louis.mango.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.louis.mango.feign.client.config.MyFeignConfig;
import com.louis.mango.feign.http.HttpResult;
import com.louis.mango.feign.page.PageRequest;


//这个接口相当于吧原来的服务提供者项目当成一个Service类
@FeignClient(value="adminLogin1", fallback = EurekaServiceFeignHystrix.class, configuration=MyFeignConfig.class)
public interface EurekaServiceFeign {

	
	
	@GetMapping(value="/role/findAll")
	public HttpResult findAll();
	
	@PostMapping(value="/role/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest);
	
	@GetMapping(value="/role/findRoleMenus")
	public HttpResult findRoleMenus(@RequestParam Long roleId);
	
}
