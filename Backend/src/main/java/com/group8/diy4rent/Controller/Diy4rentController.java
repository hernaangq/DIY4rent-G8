package com.group8.diy4rent.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Diy4rentController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Grupo 8! Somos Eric y Hernán, hecha por Luis la página, muy bueno makina";
	}

}