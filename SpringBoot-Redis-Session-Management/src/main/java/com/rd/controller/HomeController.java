package com.rd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class HomeController {

	@GetMapping
	public String home()
	{
		return "Hello User";
	}
}
//29d2ee49-0132-4e00-8873-83b5da9901b6