package com.newbit.www.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginOverlapInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		final HttpSession session = request.getSession();
		String path = request.getRequestURI();
		if(path.contains("/store/games.nbs") || path.contains("/account/login.nbs")) { //접근 경로가 상점인 경우에인 interceptor 체크 예외
			return true;
		}else if (session.getAttribute("SID") == null) {  //세션 로그인이 없으면 리다이렉트 처리
			response.sendRedirect("/store/games.nbs");
			return false;
		}
		
		return true;
	}
}