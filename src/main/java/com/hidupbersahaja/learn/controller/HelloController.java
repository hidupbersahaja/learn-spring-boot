package com.hidupbersahaja.learn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/kucing")
	public String index() {
		return "Welcome to Spring Boot, and Happy Learning !";
	}
	
}
