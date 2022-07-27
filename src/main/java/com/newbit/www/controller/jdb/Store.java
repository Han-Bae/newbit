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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.newbit.www.api.StoreJsonSimple;
import com.newbit.www.api.StoreJsoup;
import com.newbit.www.dao.ProfileDao;
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
	@Autowired
	ProfileDao profileDao;
	
	
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
	
	// 스토어 신규 인기 페이지
	@RequestMapping("/newTopSeller.nbs")
	public ModelAndView newTopSellerForm(ModelAndView mv) {
		mv.setViewName("/store/newTopSeller");
		
		List<StoreVO> medium = storeJsoup.crawlingStoreNewTop("medium");
		List<StoreVO> mini10000 = storeJsoup.crawlingStoreNewTop("mini10000");
		List<StoreVO> mini5000 = storeJsoup.crawlingStoreNewTop("mini5000");
		
		mv.addObject("medium", medium);
		mv.addObject("mini10000", mini10000);
		mv.addObject("mini5000", mini5000);
		return mv;
	}
	
	// 스토어 특별 할인 페이지
	@RequestMapping("/specialsSale.nbs")
	public ModelAndView specialsSaleForm(ModelAndView mv) {
		mv.setViewName("/store/specialsSale");
		
		List<StoreVO> medium = storeJsoup.crawlingStoreSpecials("medium");
		List<StoreVO> dailydeal = storeJsoup.crawlingStoreSpecials("dailydeal");
		
		mv.addObject("medium", medium);
		mv.addObject("dailydeal", dailydeal);
		return mv;
	}
	
	// 스토어 페이보릿 페이지
	@RequestMapping("/favorites.nbs")
	public ModelAndView storeFavoritesForm(ModelAndView mv, HttpSession session) {
		mv.setViewName("/store/favorites");
		
		String sessionId = (String) session.getAttribute("SID");
		String tagName = storeDao.getAccountTag(sessionId);
		String tagEng = "";
		switch(tagName) {
		case "액션":
			tagEng = "action";
			break;
		case "롤플레잉":
			tagEng = "rpg";
			break;
		case "전략":
			tagEng = "strategy";
			break;
		case "어드벤처":
			tagEng = "adventure";
			break;
		case "시뮬레이션":
			tagEng = "simulation";
			break;
		case "모든 스포츠":
			tagEng = "sports";
			break;
		}
		List<StoreVO> list = storeJsoup.crawlingStoreFavorites(tagEng);
		
		mv.addObject("TAG", tagName);
		mv.addObject("LIST", list);
		return mv;
	}
	
	// 스토어 카테고리 페이지
	@RequestMapping(path="/categories.nbs", method=RequestMethod.GET, params="tag")
	public ModelAndView categoryForm(ModelAndView mv, HttpServletRequest request, String tag) {
		mv.setViewName("/store/categories");
		
		String tab = request.getParameter("tab");
		List<StoreVO> list = storeJsoup.crawlingStoreCategory(tag, tab);
		
		mv.addObject("LIST", list);
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

		int haveGame = 0;
		String sessionId = (String) session.getAttribute("SID");
		if(sessionId != null) {
			sVO.setAppId(appId);
			sVO.setSessionId(sessionId);
			int pickCount = storeDao.getPickCount(sVO);
			int basketCount = storeDao.getBasketCount(sVO);
			haveGame = profileDao.countLibraryGame(sVO);
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
		mv.addObject("haveGame", haveGame);
		return mv;
	}
	
	@RequestMapping("/search")
	public ModelAndView storeSearchForm(ModelAndView mv, HttpServletRequest request) {
		mv.setViewName("/store/search");
		
		String term = request.getParameter("term");
		List<StoreVO> list = storeJsoup.crawlingStoreSearch(term);
		
		mv.addObject("LIST", list);
		return mv;
	}
}
