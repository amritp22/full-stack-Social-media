package com.social.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping()
	public String homeController() {
		return "this is home controller";
	}
	@GetMapping("/home")
	public String homeController2() {
		return "this is home controller2";
	}
}
