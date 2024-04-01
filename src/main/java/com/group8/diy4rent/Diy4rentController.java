package com.group8.diy4rent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Diy4rentController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}