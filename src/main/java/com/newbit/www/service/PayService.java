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
		String id = (String)session.getAttribute("SID");
		List<PaymentVO> pickList = pDao.getPickList(id);
		if(session.getAttribute("NAMELIST") != null) {
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
					// Payhistory에 저장
					pDao.savePay(pVO);
					// 장바구니에서 삭제
					pDao.delBasket(pVO.getGame_id());
					// 찜목록에 있다면 삭제
					for(PaymentVO pick : pickList) {
						if(pick.getGame_id().equals(pVO.getGame_id())){
							pDao.delPick(pick.getGame_id());							
						}
					}
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
				// Payhistory에 저장
				pDao.savePay(pVO);
				// 장바구니에서 삭제
				pDao.delBasket(pVO.getGame_id());
				// 찜목록에 있다면 삭제
				for(PaymentVO pick : pickList) {
					if(pick.getGame_id().equals(pVO.getGame_id())){
						pDao.delPick(pick.getGame_id());							
					}
				}
				}
			}
	}
	
}
