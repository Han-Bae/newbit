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

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.newbit.www.util.Crawling;
import com.newbit.www.vo.StoreVO;

@Controller
@RequestMapping("/store")
public class Store {
	@Autowired
	Crawling crawling;
	
	
	@RequestMapping({"/", "/games.nbs"})
	public ModelAndView GameForm(ModelAndView mv, HttpServletRequest request) {
		mv.setViewName("/store/games");
		
		String sortBy = request.getParameter("sortSelect");
		
		List<StoreVO> list = crawling.crawlingStoreMain(sortBy);
		mv.addObject("LIST", list);
		mv.addObject("sortBy", sortBy);
		return mv;
	}
	
	@RequestMapping("/categories.nbs")
	public ModelAndView CategoryForm(ModelAndView mv) {
		mv.setViewName("/store/categories");
		return mv;
	}
}
