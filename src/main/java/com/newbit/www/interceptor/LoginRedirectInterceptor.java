package com.newbit.www.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 로그인이 필요한 요청인데 로그인이 되지 않았다면 로그인으로 보내기 
 * 인터셉터 클래스
 * @author	김태현
 * @since	2022.07.12
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.07.12	-	담당자 : 김태현
 * 									클래스 제작
 */
public class LoginRedirectInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {
		if(req.getSession().getAttribute("SID") == null) {
			req.getSession().setAttribute("vw", "/www/payment/basket.nbs");
			resp.sendRedirect("/www/account/login.nbs");
			return false;
		}
		return true;
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