package com.newbit.www.service;



import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.*;
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
	private static Logger paymentLog = LoggerFactory.getLogger("paymentLog");
	 
	
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

	@Pointcut("execution(* com.newbit.www.controller.kth.Account.logout(..))")
	public void recordLogout() {
	}
	@Before("recordLogout()")
	public void logoutSetData(JoinPoint join) {
		HttpSession session = (HttpSession) join.getArgs()[1];
		AccountVO aVO = (AccountVO) join.getArgs()[2];
		aVO.setId((String) session.getAttribute("SID"));
	}
	@After("recordLogout()")
	public boolean logoutRecord(JoinPoint join) {
		AccountVO aVO = (AccountVO) join.getArgs()[2];
		String id = aVO.getId();
		String result = aVO.getResult();
		
		if(result != null && result.equals("OK")) {
			accountLog.info(id + " 님 로그아웃");
		}
		return true;
	}

	// 결제
	@After("execution(* com.newbit.www.controller.kth.Payment.**heckPay(..))")
	public void payRecord(JoinPoint join) {
		MethodSignature methodSignature = (MethodSignature) join.getSignature();
        Method method = methodSignature.getMethod();
        String funcName = method.getName();
        if(funcName.equals("selfPayCheck")) {
    		PaymentVO pVO = (PaymentVO) join.getArgs()[1];
    		String result = pVO.getResult();
    		String id = pVO.getId();
    		List<String> gameList = pVO.getGameIdList();
    		if(result.contentEquals("OK")) {
    			paymentLog.info(id + " 회원님이 게임 [ " + gameList + " ] 을 결제하셨습니다.");
    		}
        }else {
        	PaymentVO pVO = (PaymentVO) join.getArgs()[1];
    		String result = pVO.getResult();
    		String id = pVO.getId();
    		List<String> gameList = pVO.getGameIdList();
    		List<String> nameList = pVO.getNameList();
    		if(result.contentEquals("OK")) {
    			paymentLog.info(id + " 회원님이 친구 [" +nameList+ "] 님께 게임 [ " + gameList + " ] 를 선물하셨습니다.");
    		}
        }
	}
}
	
