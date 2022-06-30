package com.newbit.www.controller.kth;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.newbit.www.dao.*;
import com.newbit.www.service.AccountService;
import com.newbit.www.vo.AccountVO;

@Controller
@RequestMapping("/account")
public class Account {
	
	@Autowired
	AccountDao aDao;
	@Autowired
	AccountService aSrc;
	
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
	
// 아이디 찾기 및 메일 전송	
	@RequestMapping(path="/findID.nbs", method=RequestMethod.POST, params= {"fidName", "fidMail"})
	public ModelAndView findID(ModelAndView mv, AccountVO aVO, String fidName, String fidMail) {
		aVO.setNickname(fidName);
		aVO.setEmail(fidMail);
		int cnt = aDao.getFidCnt(aVO);
		if(cnt == 1) {
			// 해당 유저가 존재하면
			// 메일보내기
			aVO.setConvert_id(aSrc.convertID(aDao.getFidID(aVO)));
			aSrc.sendMail(aVO);// 변환 ID
			mv.addObject("icon", "success");
			mv.addObject("title", "사용자 인증 성공!");
			mv.addObject("msg", aVO.getNickname()+"님의 이메일에서 확인해주세요.");
		}else {
			mv.addObject("icon", "error");
			mv.addObject("title", "아이디 찾기 오류!");
			mv.addObject("msg", "이름이나 이메일이 일치하지 않습니다.");
				// 모달창 다시 오픈
			mv.addObject("stat", "refindId");
		}
		mv.addObject("url", "/www/account/login.nbs");
		mv.setViewName("account/redirect");
		return mv;
	}
	
// 비밀번호 찾기
	// 비밀번호 찾기 유저 확인
	@RequestMapping(path="/findPW.nbs", method=RequestMethod.POST, params= {"fpwId", "fpwMail"})
	public ModelAndView findPW(HttpSession session, ModelAndView mv, AccountVO aVO, String fpwId, String fpwMail) {
		aVO.setId(fpwId);
		aVO.setEmail(fpwMail);
		int cnt = aDao.getFpwCnt(aVO);
		if(cnt == 1) {
			// 해당 유저가 존재하면
			// 다음 단계로 이동
			mv.addObject("stat", "refindPw_next");
			mv.addObject("url", "/www/account/login.nbs");
				// 저장했다가 재설정시 사용
			session.setAttribute("id", aVO.getId());
			mv.setViewName("account/redirect");
		} else {
			mv.addObject("icon", "error");
			mv.addObject("title", "비밀번호 찾기 오류!");
			mv.addObject("msg", "아이디나 이메일이 일치하지 않습니다.");
			mv.addObject("url", "/www/account/login.nbs");
				// 모달창 다시 열기
			mv.addObject("stat", "refindPw");
			mv.setViewName("account/redirect");
		}
		return mv;
	}
	// 비밀번호 재설정
	@RequestMapping(path="/rePW.nbs", method=RequestMethod.POST, params= "rpw")
	public ModelAndView rePW(HttpSession session, ModelAndView mv, AccountVO aVO, String rpw) {
		aVO.setPw(rpw);
		aVO.setId((String)session.getAttribute("id"));
		int cnt = aDao.editPW(aVO);
		if(cnt == 1) {
			session.removeAttribute("id");
			mv.addObject("icon", "success");
			mv.addObject("title", "비밀번호 재설정 성공!");
			mv.addObject("msg", "재설정한 비밀번호로 로그인 해주세요.");
			mv.addObject("stat", "repw");
		}else {
			mv.addObject("icon", "error");
			mv.addObject("title", "비밀번호 재설정 오류!");
			mv.addObject("msg", "오류가 발생했습니다. 다시 시도해주세요.");
			mv.addObject("stat", "rePwInput");
		}
		mv.addObject("url", "/www/account/login.nbs");
		mv.setViewName("account/redirect");
		return mv;
	}
	
	// 회원가입 폼 이동
	@RequestMapping("/join.nbs")
	public ModelAndView join(ModelAndView mv) {
		mv.setViewName("account/join");
		return mv;
	}
	
	// 회원가입시 만들기
	@RequestMapping("/checkMail.nbs")
	public ModelAndView checkMail(ModelAndView mv, AccountVO aVO) {
		aVO.getEmail();
		return mv;
	}
	
}
