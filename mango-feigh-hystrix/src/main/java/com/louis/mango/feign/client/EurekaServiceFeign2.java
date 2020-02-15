package com.louis.mango.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.louis.mango.feign.client.config.MyFeignConfig;
@FeignClient(value="provider-user", fallback = EurekaServiceFeignHystrix.class, configuration=MyFeignConfig.class)
public interface EurekaServiceFeign2 {
	@RequestMapping(value="/hello/{name}",method=RequestMethod.GET)
	public String hello(@PathVariable String name);
}
