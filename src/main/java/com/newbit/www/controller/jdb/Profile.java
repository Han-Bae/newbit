package com.newbit.www.controller.jdb;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.newbit.www.api.ProfileJsonSimple;
import com.newbit.www.dao.ProfileDao;
import com.newbit.www.vo.StoreVO;

@Controller
@RequestMapping("/profile")
public class Profile {
	@Autowired
	ProfileDao profileDao;
	@Autowired
	ProfileJsonSimple profileJson;
	
	@RequestMapping("/library.nbs")
	public ModelAndView LibraryForm(ModelAndView mv, HttpSession session) {
		mv.setViewName("/profile/library");
		
		String id = (String) session.getAttribute("SID");
		List<StoreVO> list = profileDao.getLibrary(id);
		list = profileJson.getLibraryJson(list);
		
		mv.addObject("LIST", list);
		return mv;
	}

}
