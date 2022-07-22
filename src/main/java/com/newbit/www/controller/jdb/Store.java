package com.newbit.www.controller.jdb;

/**
 * 
 * 	@author 전다빈
 * 	@since 22.07.01
 * 	@version v.1.0
 * 
 * 			담당자 : 전다빈
 * 			
 */

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.newbit.www.api.StoreJsonSimple;
import com.newbit.www.api.StoreJsoup;
import com.newbit.www.dao.StoreDao;
import com.newbit.www.vo.StoreVO;

@Controller
@RequestMapping("/store")
public class Store {
	@Autowired
	StoreJsoup storeJsoup;
	@Autowired
	StoreJsonSimple storeJson;
	@Autowired
	StoreDao storeDao;
	
	
	// 스토어 메인겸 인기 페이지
	@RequestMapping({"/", "/topSeller.nbs"})
	public ModelAndView GameForm(ModelAndView mv, HttpServletRequest request, RedirectView rv) {
		mv.setViewName("/store/topSeller");
		
		String sortBy = request.getParameter("sortSelect");
		
		List<StoreVO> list = storeJsoup.crawlingStoreMain(sortBy);
		list = storeJson.getTitleReleased(list);
		
		mv.addObject("LIST", list);
		mv.addObject("sortBy", sortBy);
		return mv;
	}
	
	@RequestMapping("/categories.nbs")
	public ModelAndView CategoryForm(ModelAndView mv) {
		mv.setViewName("/store/categories");
		return mv;
	}
	
	@RequestMapping("/app")
	public ModelAndView AppDetailForm(ModelAndView mv, HttpServletRequest request,  HttpSession session) {
		mv.setViewName("/store/appDetail");
		
		String appId = request.getParameter("game");
		String appNo = appId.substring(appId.indexOf("_") + 1);
		StoreVO sVO = storeJson.getDetailJson(appNo);
		
		String sessionId = (String) session.getAttribute("SID");
		if(sessionId != null) {
			sVO.setAppId(appId);
			sVO.setSessionId(sessionId);
			int pickCount = storeDao.getPickCount(sVO);
			int basketCount = storeDao.getBasketCount(sVO);
			sVO.setPickCount(pickCount);
			sVO.setBasketCount(basketCount);
		}
		
		// type이 dlc인 경우
		if(sVO.getType().equals("dlc")) {
			appNo = sVO.getFullgameId().substring(sVO.getFullgameId().indexOf("_") + 1);
			String fullgameImg = storeJson.getFullgameImg(appNo);
			mv.addObject("fullgameImg", fullgameImg);
		}
		
		mv.addObject("sVO", sVO);
		return mv;
	}
	

}
