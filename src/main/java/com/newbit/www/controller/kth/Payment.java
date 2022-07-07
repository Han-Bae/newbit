package com.newbit.www.controller.kth;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.newbit.www.dao.*;
import com.newbit.www.service.AccountService;
import com.newbit.www.vo.AccountVO;

@Controller
@RequestMapping("/payment")
public class Payment {
	
	@RequestMapping("/payForm.nbs")
	public ModelAndView payForm(ModelAndView mv) {
		mv.addObject("stat", "first");
		mv.setViewName("pay/payment");
		return mv;
	}
	
	@RequestMapping("/payFormMemo.nbs")
	public ModelAndView payFormMemo(ModelAndView mv, AccountVO aVO) {
		mv.addObject("stat", "second");
		mv.addObject("nameList", aVO.getNameList());
		mv.setViewName("pay/paymentMemo");
		return mv;
	}
	
	@RequestMapping("/payFormInfo.nbs")
	public ModelAndView payFormInfo(ModelAndView mv) {
		mv.addObject("stat", "third");
		mv.setViewName("pay/paymentInfo");
		return mv;
	}
	
	@RequestMapping("/payFormCheck.nbs")
	public ModelAndView payFormCheck(ModelAndView mv) {
		mv.addObject("stat", "fourth");
		mv.setViewName("pay/paymentCheck");
		return mv;
	}
}
