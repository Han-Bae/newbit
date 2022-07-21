package com.newbit.www.controller.jdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.newbit.www.api.StoreJsoup;
import com.newbit.www.api.StoreSelenium;

@Controller
@RequestMapping("/profile")
public class Profile {
	@Autowired
	StoreSelenium storeSelenium;
	@Autowired
	StoreJsoup storeJsoup;
	
	@RequestMapping("/library.nbs")
	public ModelAndView LibraryForm(ModelAndView mv) {
		mv.setViewName("/profile/library");
		return mv;
	}

}
