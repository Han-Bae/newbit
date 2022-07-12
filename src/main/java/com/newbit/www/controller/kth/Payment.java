package com.newbit.www.controller.kth;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.newbit.www.dao.*;
import com.newbit.www.service.*;
import com.newbit.www.vo.*;

@Controller
@RequestMapping("/payment")
public class Payment {
	
	@Autowired
	AccountDao aDao;
	@Autowired
	PaymentDao pDao;
	
	// 선물하기 결제 1단계
	@RequestMapping("/payForm.nbs")
	public ModelAndView payForm(ModelAndView mv, HttpSession session) {
		List<Integer> list = pDao.selFollower((String)session.getAttribute("SID"));
		List<String> nameList = pDao.getFriendInfo(list);
		mv.addObject("nameList", nameList);
		mv.addObject("stat", "first");
		mv.setViewName("pay/payment");
		return mv;
	}
	
	// 선물하기 결제 2단계 이동
	@RequestMapping("/payFormMemo.nbs")
	public ModelAndView payFormMemo(ModelAndView mv, PaymentVO pVO) {
		mv.addObject("stat", "second");
		mv.addObject("nameList", pVO.getNameList());
		mv.setViewName("pay/paymentMemo");
		return mv;
	}
	
	// 선물하기 결제 3단계 이동
	@RequestMapping("/payFormInfo.nbs")
	public ModelAndView payFormInfo(ModelAndView mv, PaymentVO pVO) {
		mv.addObject("stat", "third");
		mv.addObject("pVO", pVO);
		mv.setViewName("pay/paymentInfo");
		return mv;
	}
	
	// 선물하기 결제 4단계 이동
	@RequestMapping("/payFormCheck.nbs")
	public ModelAndView payFormCheck(ModelAndView mv, PaymentVO pVO ,HttpSession session) {
		session.setAttribute("PVO", pVO);
		AccountVO aVO = new AccountVO();
		aVO.setId((String)session.getAttribute("SID"));
		aVO = aDao.selAccountInfo(aVO);
		aVO.setTel("010-1111-1111");
		mv.addObject("aVO", aVO);
		mv.addObject("nameList", pVO.getNameList());
		mv.addObject("paySel", pVO.getPaySel());
		mv.addObject("stat", "fourth");
		mv.setViewName("pay/paymentCheck");
		return mv;
	}
}
