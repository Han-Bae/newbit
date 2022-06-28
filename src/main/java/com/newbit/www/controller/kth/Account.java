package com.newbit.www.controller.kth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/account")
public class Account {
	// 로그인 폼 보기 요청처리
	@RequestMapping("/login.nbs")
	public ModelAndView loginForm(ModelAndView mv) {
		mv.setViewName("/account/login");
		return mv;
	}
}
