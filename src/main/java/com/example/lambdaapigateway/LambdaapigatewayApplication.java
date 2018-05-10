package com.example.lambdaapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author jugul.mishra
 * May 10, 2018 3:05:02 PM
 */

@SpringBootApplication
@Configuration
public class LambdaapigatewayApplication {
	
	public LambdaapigatewayApplication(){
		
	}

	public static void main(String[] args) {
		SpringApplication.run(LambdaapigatewayApplication.class, args);
	}
}
