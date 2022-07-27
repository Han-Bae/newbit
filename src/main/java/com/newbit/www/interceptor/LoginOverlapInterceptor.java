package com.newbit.www.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.newbit.www.service.AccountService;
/**
 * 다른 작업 중 로그인 페이지로 이동했다면 로그인 이후 해당 작업으로 이동 
 * 인터셉터 클래스
 * @author	김태현
 * @since	2022.07.18
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.07.18	-	담당자 : 김태현
 * 									클래스 제작
 */
public class LoginOverlapInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		final HttpSession session = request.getSession();
		String path = request.getRequestURI();
		if(path.contains("/store/") || path.contains("/account/login.nbs")|| path.contains("/account/logout.nbs"))
		{ //접근 경로가 상점인 경우에인 interceptor 체크 예외
			return true;
		}else if (session.getAttribute("SID") == null) {  //세션 로그인이 없으면 리다이렉트 처리
			response.sendRedirect("/store/");
			return false;
		}
		
		return true;
	}
}