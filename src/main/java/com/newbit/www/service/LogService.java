package com.newbit.www.service;



import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.*;
import org.springframework.stereotype.Service;

import com.newbit.www.vo.*;

/**
 * 이 클래스는 현재 프로젝트 전반의 로그에 대한 클래스
 * 
 * @author 김태현
 * @since 2022.06.30
 * @version v.1.0
 * 
 *          작업이력 ] 2022.06.30 - 담당자 : 김태현 클래스제작
 *
 */

@Service
@Aspect
public class LogService {
	private static Logger accountLog = LoggerFactory.getLogger("accountLog");
	
	
	@Pointcut("execution(* com.newbit.www.controller.kth.Account.loginProc(..))")
	public void recordLogin() {
	}
	
	@After("recordLogin()")
	public boolean rec(JoinPoint join) {
		AccountVO aVO = (AccountVO) join.getArgs()[1];
		
		if(aVO.getCnt() == 1) {
			accountLog.info(aVO.getId() + " 님 로그인");
		}
		
		return true;
	}


	@Before("execution(* com.newbit.www.controller.kth.Account.logout(..))")
	public void logoutSetData(JoinPoint join) {
		HttpSession session = (HttpSession) join.getArgs()[1];
		AccountVO aVO = (AccountVO) join.getArgs()[2];
		aVO.setId((String) session.getAttribute("SID"));
	}
	
	@After("execution(* com.newbit.www.controller.kth.Account.logout(..))")
	public void logoutRecord(JoinPoint join) {
		AccountVO aVO = (AccountVO) join.getArgs()[2];
		String id = aVO.getId();
		String result = aVO.getResult();
		
		if(result != null && result.equals("OK")) {
			accountLog.info(id + " 님 로그아웃");
		}
	}
	
}
