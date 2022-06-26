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
	@Autowired
	SurveyDao sDao;
	
	@RequestMapping({"/", "/main.blp"})
	public ModelAndView getMain(ModelAndView mv, SurveyVO sVO, HttpSession session) {
		String sid =(String) session.getAttribute("SID");
		if(sid != null) {
			sVO.setId(sid);
			
			int cnt = sDao.getCount(sVO);
			mv.addObject("SCOUNT", cnt);
		}
		
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("/mainMsgCheck.blp")
	@ResponseBody
	public Map<String, String> msgCheck(HttpSession session){
		HashMap<String, String> map = new HashMap<String, String>();
		
		session.setAttribute("MSG_CHECK", "NO");
		
		map.put("result", "OK");
		return map;
	}
}
