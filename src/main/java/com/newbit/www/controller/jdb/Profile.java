package com.newbit.www.controller.jdb;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.newbit.www.api.ProfileJsonSimple;
import com.newbit.www.api.StoreJsonSimple;
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
	public ModelAndView libraryForm(ModelAndView mv, HttpSession session) {
		mv.setViewName("/profile/library");
		
		String id = (String) session.getAttribute("SID");
		List<StoreVO> list = profileDao.getLibrary(id);
		list = profileJson.getLibraryJson(list);
		
		mv.addObject("LIST", list);
		return mv;
	}

	
	@RequestMapping("/addLibraryGame.nbs")
	public ModelAndView addLibraryGame(ModelAndView mv, RedirectView rv) {		
		rv.setUrl("/www/profile/library.nbs");
		mv.setView(rv);
		return mv;
	}
}
