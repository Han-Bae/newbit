package com.newbit.www.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.newbit.www.dao.PaymentDao;
import com.newbit.www.vo.AccountVO;
import com.newbit.www.vo.PaymentVO;

public class PayService {
	@Autowired
	PaymentDao pDao;
	
	// 데이터베이스 입력작업 전담 처리함수
	@Transactional
	public void addPayData(PaymentVO pVO, HttpSession session) {
		// 선물할 친구 명단이 있다면 선물 결제이다.
			// 선물결제
		System.out.println(session.getAttribute("NAMELIST"));
		if(session.getAttribute("NAMELIST") == null) {
			List<String> nameList = pVO.getNameList();
				// 선물받는 친구 명단
			List<Integer> noList = pDao.getNoList(nameList);
				// 선물할 친구 수 * 게임 수만큼 내역 반복
			for(int friendCnt = 0; friendCnt < noList.size(); friendCnt ++) {
				for(int gameCnt = 0; gameCnt < pVO.getGameIdList().size(); gameCnt++) {
						// game ID 입력
					pVO.setGame_id(pVO.getGameIdList().get(gameCnt));
						// 각 game 가격 입력
					pVO.setGame_price(pVO.getGamePriceList().get(gameCnt));
						// 구매자 no 입력
					pVO.setBuy_no(pDao.getNo((String)session.getAttribute("SID")));
						// 받는 사람 no 입력
					pVO.setAccount_no(noList.get(friendCnt));
					pDao.savePay(pVO);
					}
				}
				// 직접결제
		} else {				
			for(int gameCnt = 0; gameCnt < pVO.getGameIdList().size(); gameCnt++) {
					// game ID 입력
				pVO.setGame_id(pVO.getGameIdList().get(gameCnt));
					// 각 game 가격 입력
				pVO.setGame_price(pVO.getGamePriceList().get(gameCnt));
					// 구매자 no 입력
				pVO.setBuy_no(pDao.getNo((String)session.getAttribute("SID")));
					// 받는 사람 no 입력
				pVO.setAccount_no(pDao.getNo((String)session.getAttribute("SID")));
				pDao.savePay(pVO);
				}
			}
	}
	
}
