package com.jwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

	// API

	@RequestMapping("/welcome")
	public String welcome(){
		String text=" Welcome To Spring Boot Security JWT";
		return text;
	}

}
