package com.gpo7.proceso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/flujo-de-trabajo")
public class BaseController {
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/base")
	public String base() {
		return "base";
	}

}
