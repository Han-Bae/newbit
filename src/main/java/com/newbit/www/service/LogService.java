package com.newbit.www.service;

import java.util.*;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newbit.www.dao.*;
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
		AccountVO aVO = (AccountVO) join.getArgs()[0];
		
		if(aVO.getCnt() == 1) {
			accountLog.info(aVO.getId() + " 님 로그인");
		}
		
		return true;
	}
}
