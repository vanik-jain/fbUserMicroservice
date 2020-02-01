package com.facebookclone.fbuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FbuserApplication {

	public static void main(String[] args) {
		SpringApplication.run(FbuserApplication.class, args);
	}

}
