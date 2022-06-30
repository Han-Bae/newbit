package com.newbit.www.controller.kth;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.newbit.www.dao.*;
import com.newbit.www.vo.AccountVO;

@Controller
@RequestMapping("/account")
public class Account {
	
	@Autowired
	AccountDao aDao;
	
	// 로그인 폼 보기 요청처리
	@RequestMapping("/login.nbs")
	public ModelAndView loginForm(ModelAndView mv) {
		mv.setViewName("/account/login");
		return mv;
	}
	
	@RequestMapping(path="/loginProc.nbs", method=RequestMethod.POST, params= {"id", "pw"})
	public ModelAndView loginProc(ModelAndView mv, AccountVO aVO, HttpSession session){

			int cnt = aDao.getLogin(aVO);
			aVO.setCnt(cnt);
			
			if(cnt == 1) {

				// 아이디와 비밀번호가 일치하는
				// 회원이 있는 경우 -> 로그인 처리
				session.setAttribute("SID", aVO.getId());
				mv.addObject("icon", "success");
				mv.addObject("title", "로그인 성공!");
				mv.addObject("msg", aVO.getId()+"님 어서오세요.");
				mv.addObject("url", "/www/main.nbs");
			}else {
				// 로그인 처리하면 안된다.
				// 정보가 정확하지 않거나 없는 회원이다.
				// 정보가 일치하지 않는다면
				mv.addObject("icon", "error");
				mv.addObject("title", "로그인 실패!");
				mv.addObject("msg", "아이디나 비밀번호가 일치하지 않습니다.");
				mv.addObject("url", "/www/account/login.nbs");
					// 스테이터스 변경
				mv.addObject("stat", "relogin");
			}
			mv.setViewName("account/redirect");
			return mv;
	}
	
	@RequestMapping("/logout.nbs")
	public ModelAndView logout(ModelAndView mv, HttpSession session) {
		//accountLog.info((String) session.getAttribute("SID") + "님이 로그아웃하였습니다.");
		session.removeAttribute("SID");
		mv.setViewName("main");
		return mv;
	}
}
