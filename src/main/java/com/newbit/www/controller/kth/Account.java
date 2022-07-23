package com.newbit.www.controller.kth;


import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.RedirectView;

import com.newbit.www.dao.*;
import com.newbit.www.service.AccountService;
import com.newbit.www.service.SessionConfig;
import com.newbit.www.vo.*;

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
	@RequestMapping(path="/login.nbs", params="vw")
	public ModelAndView loginForm(ModelAndView mv, String vw, HttpSession session) {
		session.setAttribute("vw", vw);
		mv.setViewName("/account/login");
		return mv;
	}
	
	// 관리자 폼 보기 요청처리
	@RequestMapping("/admin.nbs")
	public ModelAndView admin(ModelAndView mv, HttpSession session, RedirectView rv) {
		AccountVO aVO = aDao.getType((String)session.getAttribute("SID"));
		// 관리자만 관리자폼 진입
		if(aVO.getIstype().equals("A")) {			
			mv.setViewName("/account/admin");
		}else {
			rv.setUrl("/www/store/");
			mv.setView(rv);
		}
		return mv;
	}
	// 알림창 폼 보기 요청처리
	@RequestMapping("/notice.nbs")
	public ModelAndView noticeForm(ModelAndView mv, HttpSession session) {
		AccountVO aVO = new AccountVO();
		aVO.setnVOList(aDao.getNotice((String)session.getAttribute("SID")));
		mv.addObject("NOTICELIST", aVO);
		mv.setViewName("/account/notice");
		return mv;
	}
	// 알림창 디테일 폼 보기 요청처리
	@RequestMapping(path="/noticeDetail.nbs", method=RequestMethod.POST, params= {"no", "title", "body", "ischeck"})
	public ModelAndView noticeDetail(ModelAndView mv, NoticeVO nVO) {
		if(nVO.getIscheck().equals("N")) {
			aDao.checkNotice(nVO.getNo());
		}
		mv.addObject("NOTICE", nVO);
		mv.setViewName("/account/noticeDetail");
		return mv;
	}
	// 로그인 처리
	@RequestMapping(path="/loginProc.nbs", method=RequestMethod.POST, params= {"id", "pw"})
	public ModelAndView loginProc(ModelAndView mv, AccountVO aVO, HttpSession session){
			
			int cnt = aDao.getLogin(aVO);
			aVO.setCnt(cnt);
			
			if(cnt == 1) {

				// 아이디와 비밀번호가 일치하는
				// 회원이 있는 경우 -> 로그인 처리
				String userId = SessionConfig.getSessionidCheck("SID", aVO.getId());
				System.out.println(aVO.getId()+" : "+userId);
				session.setAttribute("SID", aVO.getId());
				
				// 아이디가 관리자일 경우
				aVO = aDao.getType(aVO.getId());
				if(aVO.getIstype().equals("A")) {
					mv.addObject("title", "관리자 접속");
					mv.addObject("url", "/www/account/admin.nbs");
					session.setAttribute("AVO", aVO);
				// 그 외 유저일 경우
				}else {					
					mv.addObject("title", "로그인 성공!");
					if(session.getAttribute("vw") != null) {
						mv.addObject("url", (String)session.getAttribute("vw"));					
					} else {					
						mv.addObject("url", "/www/store/");
					}
				}
				mv.addObject("icon", "success");
				mv.addObject("msg", aVO.getNickname()+"님 어서오세요.");
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
	
	// 로그아웃
	@RequestMapping("/logout.nbs")
	public ModelAndView logout(ModelAndView mv, HttpSession session, AccountVO aVO) {
		session.removeAttribute("SID");
		mv.addObject("icon", "success");
		mv.addObject("title", "로그 아웃");
		mv.addObject("msg", "");
		mv.addObject("url", "/www/account/login.nbs");
		aVO.setResult("OK");
		mv.setViewName("account/redirect");
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
	

// 중복확인
	
	// 아이디 중복확인
	@RequestMapping(path="/idCk.nbs", method=RequestMethod.POST, params="var")
	@ResponseBody
	public Map<String, String> checkId(ModelAndView mv, AccountVO aVO, String var) {
		String id = var;
		int cnt = aDao.getIdCnt(id);
		aVO.setCnt(cnt);
		
		HashMap<String, String> map = new HashMap<String, String>();
		String result = "NO";
		// 없어야 OK
		if(cnt == 0) {
			result = "OK";
		}
		map.put("result", result);
		return map;
	}
	
	// 닉네임 중복확인
	@RequestMapping(path="/nicknameCk.nbs", method=RequestMethod.POST, params="var")
	@ResponseBody
	public Map<String, String> checkNick(ModelAndView mv, AccountVO aVO, String var) {
		String nickname = var;
		int cnt = aDao.getNickCnt(nickname);
		aVO.setCnt(cnt);
		
		HashMap<String, String> map = new HashMap<String, String>();
		String result = "NO";
		// 없어야 OK
		if(cnt == 0) {
			result = "OK";
		}
		map.put("result", result);
		return map;
	}

// 이메일 관련
	// 이메일 중복확인
	@RequestMapping(path="/emailCk.nbs", method=RequestMethod.POST, params="var")
	@ResponseBody
	public Map<String, String> checkEmail(ModelAndView mv, AccountVO aVO, String var) {
		String email = var;
		int cnt = aDao.getEmailCnt(email);
		aVO.setCnt(cnt);
		
		HashMap<String, String> map = new HashMap<String, String>();
		String result = "NO";
		// 없어야 OK
		if(cnt == 0) {
			result = "OK";
		}
		map.put("result", result);
		return map;
	}
	
	// 인증 메일 전송
	@ResponseBody
	@RequestMapping(path="/certifiMail.nbs", method=RequestMethod.POST, params="email")
	public Map<String, String> SendEmail(ModelAndView mv, AccountVO aVO, HttpSession session) {
		int cnt = aDao.getEmailCnt(aVO.getEmail());
		// 중복 재확인
		aVO.setCnt(cnt);
		HashMap<String, String> map = new HashMap<String, String>();
		String result = "NO";
		// 중복된 이메일 개수이므로 0이어야함
		if(cnt == 0) {
			if(aDao.registCk(aVO) == 0) {				
				aDao.insertMail(aVO);
			}
			aVO.setCk_mail(aDao.selCkMail(aVO));	
			aSrc.sendMail(aVO);
			result = "OK";
		}
		
		map.put("result", result);
		return map;		
	}
	
	// 인증 메일 확인
	@RequestMapping("/checkMail.nbs")
	public ModelAndView checkMail(ModelAndView mv, AccountVO aVO, @RequestParam int ck) {
		aVO.setCk_mail(ck);
		String mail = aDao.getEmail(aVO);
		mv.setViewName("account/redirect");
		if(mail == null) {
			mv.addObject("icon", "error");
			mv.addObject("title", "오류 발생");
			mv.addObject("msg", "다시 시도해주세요");
			mv.addObject("url", "/www/account/join.nbs");
			return mv;
		}
		
		// 이메일 저장
		aVO.setEmail(mail);
		int cnt = aDao.editOkMail(aVO);
		
			// 현재 이메일 비회원상태 isokay = no로 저장돼있다.
		if(cnt == 1) {
			// 회원가입 메일 인증 확인용 어트리뷰트
			// 알림창 파라미터
			mv.addObject("icon", "success");
			mv.addObject("title", "이메일 인증 성공!");
			mv.addObject("msg", "회원가입 페이지로 돌아가 남은 절차를 진행해주세요.");
			mv.addObject("mailCheck", "pass");
		} else {
			// 회원가입 메일 인증 확인용 어트리뷰트
			// 알림창 파라미터
			mv.addObject("icon", "error");
			mv.addObject("title", "이미 가입한 이메일입니다!");
			mv.addObject("url", "/www/account/join.nbs");
			mv.addObject("msg", "회원가입을 처음부터 진행해주세요");
		}
		return mv;
	}
	
	// 인증 메일 전송
	@RequestMapping(path="/checkOkMail.nbs", method=RequestMethod.POST, params="email")
	@ResponseBody
	public Map<String, String> checkOkMail(ModelAndView mv, AccountVO aVO) {
		int cnt = aDao.getEmailCnt(aVO.getEmail());	
		// 중복 재확인
		aVO.setCnt(cnt);
		HashMap<String, String> map = new HashMap<String, String>();
		String result = "NO";
		System.out.println(cnt);
		// 있어야 OK
			// 이미 가입된 경우 유효성/중복검사로 되돌아가므로 중복 검사할 필요 없음
		if(cnt == 1) {
			aSrc.sendMail(aVO);
			result = "OK";
		}
		map.put("result", result);
		return map;		
	}
	
	// 회원가입
	@RequestMapping("/joinProc.nbs")
	public ModelAndView joinProc(ModelAndView mv, AccountVO aVO) {
		int cnt = aDao.insertJoin(aVO);
		if(cnt == 1) {
			mv.addObject("icon", "success");
			mv.addObject("title", "회원가입 성공!");
			mv.addObject("msg", "로그인을 진행해주세요");
			mv.addObject("url", "/www/account/login.nbs");
		}else {
			aDao.editNoMail(aVO);
			mv.addObject("icon", "success");
			mv.addObject("title", "회원가입 실패");
			mv.addObject("msg", "다시 시도해주세요.");
			mv.addObject("url", "/www/account/join.nbs");
		}
		mv.setViewName("account/redirect");
		return mv;
	}
}
