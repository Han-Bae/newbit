package com.newbit.www.controller.jdb;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.newbit.www.dao.ProfileDao;
import com.newbit.www.vo.StoreVO;

@Controller
@RequestMapping("/profile")
public class Profile {
	@Autowired
	ProfileDao profileDao;
	
	@RequestMapping("/library.nbs")
	public ModelAndView LibraryForm(ModelAndView mv, HttpSession session) {
		String id = (String) session.getAttribute("SID");
		List<StoreVO> list = profileDao.getLibrary(id);
		
		mv.setViewName("/profile/library");
		return mv;
	}

}
