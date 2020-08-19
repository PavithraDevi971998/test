package com.springreactive.bms;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;



//@EnableDiscoveryClient
@SpringBootApplication
public class BankManagementSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankManagementSystemApplication.class, args);
		
		
	}

}
