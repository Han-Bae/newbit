package com.newbit.www.controller.phk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/info") // 아래 InfoUpdate라는 클래스를 사용하기위해 매핑시켜준다.
public class InfoUpdate {

	// 회원정보를 infoUpdate.nbs로 요청이 왔을때 띄워주는 기능

	@RequestMapping("/InfoUpdate.nbs")
	public ModelAndView infoUpdate(ModelAndView mv) {

		mv.setViewName("info/InfoUpdate");
		return mv;
	}
	
	@RequestMapping("/ProfileUpdate.nbs")
	public ModelAndView ProfileUpdate(ModelAndView mv) {

		mv.setViewName("info/ProfileUpdate");
		return mv;
	}
	
	@RequestMapping("/withdrawal.nbs")
	public ModelAndView DelInfo(ModelAndView mv) {
		
		mv.setViewName("info/withdrawal");
		return mv;
	}
}
