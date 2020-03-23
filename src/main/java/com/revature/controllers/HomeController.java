package com.revature.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping()
public class HomeController {
	@GetMapping("/")
	@ResponseBody
	public String returnHomePage() {
		return "main page";
	}
	
}
