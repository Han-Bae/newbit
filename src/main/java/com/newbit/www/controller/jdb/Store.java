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
	
	
	// 스토어 메인겸 최고 인기 페이지
	@RequestMapping({"/", "/topSeller.nbs"})
	public ModelAndView topSellerForm(ModelAndView mv, HttpServletRequest request) {
		mv.setViewName("/store/topSeller");
		
		String sortSelect = request.getParameter("sortSelect");
		String maxPrice = request.getParameter("maxPrice");
		String specials = request.getParameter("specials");
		
		List<StoreVO> list = null;
		
		if(specials == null) {
			if(maxPrice == null) {
				list = storeJsoup.crawlingStoreTopSeller(sortSelect);
			} else {
				list = storeJsoup.crawlingStoreTopSeller(sortSelect, maxPrice);
			}
		} else {
			if(maxPrice == null) {
				list = storeJsoup.crawlingStoreTopSeller(sortSelect, specials);
			} else {
				list = storeJsoup.crawlingStoreTopSeller(sortSelect, maxPrice, specials);
			}
		}
		
		mv.addObject("LIST", list);
		mv.addObject("sortSelect", sortSelect);
		mv.addObject("maxPrice", maxPrice);
		mv.addObject("specials", specials);
		return mv;
	}
	
	// 스토어 신규
	@RequestMapping("/newTopSeller.nbs")
	public ModelAndView newTopSeller(ModelAndView mv) {
		mv.setViewName("/store/newTopSeller");
		
		List<StoreVO> medium = storeJsoup.crawlingStoreNewTop("medium");
		List<StoreVO> mini10000 = storeJsoup.crawlingStoreNewTop("mini10000");
		List<StoreVO> mini5000 = storeJsoup.crawlingStoreNewTop("mini5000");
		
		mv.addObject("medium", medium);
		mv.addObject("mini10000", mini10000);
		mv.addObject("mini5000", mini5000);
		return mv;
	}
	
	// 스토어 카테고리 페이지
	@RequestMapping("/categories.nbs")
	public ModelAndView categoryForm(ModelAndView mv) {
		mv.setViewName("/store/categories");
		return mv;
	}

	// 스토어 게임 디테일 페이지
	@RequestMapping("/app")
	public ModelAndView appDetailForm(ModelAndView mv, HttpServletRequest request,  HttpSession session) {
		mv.setViewName("/store/appDetail");
		
		String appId = request.getParameter("game");
		String appNo = appId.substring(appId.indexOf("_") + 1);
		StoreVO sVO = storeJson.getDetailJson(appNo);
		sVO = storeJsoup.crawlingStoreDetail(appNo, sVO);
		
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
