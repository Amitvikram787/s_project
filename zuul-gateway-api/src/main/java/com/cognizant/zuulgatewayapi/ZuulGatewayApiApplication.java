package com.cognizant.zuulgatewayapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.CrossOrigin;
@EnableZuulProxy
@EnableDiscoveryClient
//@EnableFeignClients
@SpringBootApplication
public class ZuulGatewayApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulGatewayApiApplication.class, args);
	}

}
