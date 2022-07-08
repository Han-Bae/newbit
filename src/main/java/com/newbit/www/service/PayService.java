package com.newbit.www.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PayService {
	@GetMapping("/iamport")
	public String iamport(){
		return "index"; 
	} 
}