package com.louis.mango.feign.client;

import org.springframework.stereotype.Component;
import com.louis.mango.feign.http.HttpResult;
import com.louis.mango.feign.page.PageRequest;


@Component
public class EurekaServiceFeignHystrix implements EurekaServiceFeign,EurekaServiceFeign2{

	@Override
	public String hello(String name) {
		return "sorry, hello service call failed";
	}

	@Override
	public HttpResult findAll() {
		return HttpResult.error("sorry, hello service call failed");
	}

	@Override
	public HttpResult findPage(PageRequest pageRequest) {
		return HttpResult.error("sorry, hello service call failed");
	}

	@Override
	public HttpResult findRoleMenus(Long roleId) {
		return HttpResult.error("sorry, hello service call failed");
	}


}
