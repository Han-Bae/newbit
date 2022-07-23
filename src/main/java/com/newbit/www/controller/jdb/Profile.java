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
	public ModelAndView addLibraryGame(ModelAndView mv, HttpSession session, RedirectView rv) {
		String sessionId = (String) session.getAttribute("SID");
		List<String> appIds = profileDao.getPayHistoryList(sessionId);
		
		for(int i = 0 ; i < appIds.size() ; i++) {
			String appId = appIds.get(i);
			StoreVO sVO = profileJson.getType(appId.substring(appId.indexOf("_") + 1));
			sVO.setAppId(appId);
			sVO.setSessionId(sessionId);
			
			int count = profileDao.addLibraryGame(sVO);
			
			if(count == 1) {
				System.out.println("페이히스토리에서 라이브러리로 이동 성공");
			} else {
				System.out.println("실패실패");
			}
		}
		
		rv.setUrl("/www/profile/library.nbs");
		mv.setView(rv);
		return mv;
	}
}
