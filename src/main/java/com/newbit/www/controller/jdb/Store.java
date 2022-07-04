package com.newbit.www.controller.jdb;

/**
 * 
 * 	@author 전다빈
 * 	@since 22.07.01
 * 	@version v.1.0
 * 
 * 			담당자 : 전다빈
 * 			
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/store")
public class Store {
	
	
	@RequestMapping("/games.nbs")
	public ModelAndView GameForm(ModelAndView mv) {
		mv.setViewName("/store/games");
		return mv;
	}
	
	@RequestMapping("/categories.nbs")
	public ModelAndView CategoryForm(ModelAndView mv) {
		mv.setViewName("/store/categories");
		return mv;
	}
}
