package com.newbit.www.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 로그인이 확인되면 관련 요청을 받지 않고 메인으로 보내기 
 * 인터셉터 클래스
 * @author	김태현
 * @since	2022.07.12
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.07.12	-	담당자 : 김태현
 * 									클래스 제작
 */

public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {
		if(req.getSession().getAttribute("SID") != null) {
			resp.sendRedirect("/www/store/");
			return false; // 요청처리함수 실행을 하지 마세요...
		}
		return true;	// 요청 처리함수 실행을 해주세요.
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}