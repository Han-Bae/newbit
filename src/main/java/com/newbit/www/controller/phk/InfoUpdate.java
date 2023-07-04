package com.newbit.www.controller.phk;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.newbit.www.dao.*;
import com.newbit.www.vo.*;

@Controller
@RequestMapping("/info") // 아래 InfoUpdate라는 클래스를 사용하기위해 매핑시켜준다.
public class InfoUpdate {
	
	@Autowired
	InfoDao inDao;

	// 회원정보를 infoUpdate.nbs로 요청이 왔을때 띄워주는 기능

	@RequestMapping("/InfoUpdate.nbs")
	public ModelAndView infoUpdate(ModelAndView mv) {

		mv.setViewName("info/InfoUpdate");
		return mv;
	}
	
	//이 요청이 들어왔을때 실행하는 함수인거다.
	@RequestMapping("/ProfileUpdate.nbs")
	public ModelAndView ProfileUpdate(ModelAndView mv, HttpSession session, InfoVO inVO) {
		
		// 세션에서 SID를 가져와서 강제형변환 후 변수 id에 담는다.
		String id = (String)session.getAttribute("SID");
		
		// inDao.myInfoData에 변수 id를 들고 가서 함수를 수행하라 그리고 반환값은 inVO에 넣어라
		inVO = inDao.myInfoData(id);
		
		// 변수 inVO에 담긴 결과값을 "DATA"로 전환해준다.---객체화하여 활용하기 위함 
		mv.addObject("DATA", inVO);
		
		// 불러올려고 하는 JSP는 info/ProfileUpdate.jsp 이다.
		mv.setViewName("info/ProfileUpdate");
		
		// 반환값 mv로 반환된다.
		return mv;
	}
	
	@RequestMapping("/withdrawal.nbs")
	public ModelAndView DelInfo(ModelAndView mv) {
		
		mv.setViewName("info/withdrawal");
		return mv;
	}
}
