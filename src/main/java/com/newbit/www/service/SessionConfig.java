package com.newbit.www.service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * 이 클래스는 중복 로그인 방지 기능을 위한 클래스
 * 
 * @author 김태현
 * @since 2022.07.18
 * @version v.1.0
 * 
 *          작업이력 ] 2022.07.18 - 담당자 : 김태현 -> 중복 로그인 관리
 */

@WebListener
public class SessionConfig implements HttpSessionListener {
	// 해당 객체는 Session이 생성되거나 제거될때 발생하는 이벤트를 제공
	private static final Map<String, HttpSession> sessions = new ConcurrentHashMap<>();
		// ConcurrentHashMap는 일반 HashMap과는 다르게 key, value값으로 Null을 허용하지 않는 컬렉션입니다.
				   //  < 세션 고유 생성 아이디 값, 세션 >
	//중복로그인 지우기
	public synchronized static String getSessionidCheck(String type, String compareId){
		String result = "";
		System.out.println(sessions.keySet());
		for( String key : sessions.keySet() ){
			HttpSession hs = sessions.get(key);
			if(hs != null &&  hs.getAttribute(type) != null && hs.getAttribute(type).toString().equals(compareId) ){
				result =  key.toString();
				System.out.println(key+":"+result);
			}
		}
		removeSessionForDoubleLogin(result);
		return result;
	}
	
	private static void removeSessionForDoubleLogin(String SID){    	
		System.out.println("remove userId : " + SID);
		if(SID != null && SID.length() > 0){
			sessions.get(SID).invalidate();
			sessions.remove(SID);
		}
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// 세션 생성 시
		System.out.println(se);
	    sessions.put(se.getSession().getId(), se.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// 세션 만료 시
		if(sessions.get(se.getSession().getId()) != null){
			sessions.get(se.getSession().getId()).invalidate();
			sessions.remove(se.getSession().getId());	
		}
	}

}
