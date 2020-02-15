package com.louis.mango.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer	  //标识EurekaServer
public class MangoEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangoEurekaApplication.class, args);
	}

}
