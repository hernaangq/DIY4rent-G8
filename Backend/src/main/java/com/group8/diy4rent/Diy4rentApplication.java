package com.group8.diy4rent;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc

@ComponentScan
@SpringBootApplication
public class Diy4rentApplication {


	public static void main(String[] args) {
		SpringApplication.run(Diy4rentApplication.class, args);
	}



}