package com.newbit.www.controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.newbit.www.dao.*;
import com.newbit.www.vo.*;

@Controller
public class MainController {
	
	@RequestMapping({"/", "/main.nbs"})
	public ModelAndView getMain(ModelAndView mv, HttpSession session) {
		String sid =(String) session.getAttribute("SID");
		
		mv.setViewName("main");
		return mv;
	}
	

}