package com.newbit.www.controller.kth;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.newbit.www.dao.*;
import com.newbit.www.service.*;
import com.newbit.www.vo.*;

@Controller
@RequestMapping("/payment")
public class Payment {
	
	@Autowired
	AccountDao aDao;
	@Autowired
	PaymentDao pDao;
	@Autowired
	PaymentImp pImp;
	
	// 장바구니 페이지 이동
	@RequestMapping("/basket.nbs")
	public ModelAndView basket(ModelAndView mv) {
		mv.setViewName("pay/basket");
		return mv;
	}

	// 셀프 결제 1단계 이동
	@RequestMapping("/myselfPayInfo.nbs")
	public ModelAndView myselfPayInfo(ModelAndView mv, PaymentVO pVO) {
		mv.addObject("stat", "first");
		mv.addObject("pVO", pVO);
		mv.setViewName("pay/myselfPayCheck");
		return mv;
	}
	
	// 셀프 결제 2단계 이동
	@RequestMapping("/myselfPayCheck.nbs")
	public ModelAndView myselfPayCheck(ModelAndView mv, PaymentVO pVO ,HttpSession session) {
		mv.addObject("stat", "second");
		return mv;
	}
	
	// 선물하기 결제 1단계
	@RequestMapping("/payForm.nbs")
	public ModelAndView payForm(ModelAndView mv, HttpSession session) {
		List<Integer> list = pDao.selFollower((String)session.getAttribute("SID"));
		List<String> nameList = pDao.getFriendInfo(list);
		mv.addObject("nameList", nameList);
		mv.addObject("stat", "first");
		mv.setViewName("pay/payment");
		return mv;
	}
	
	// 선물하기 결제 2단계 이동
	@RequestMapping("/payFormMemo.nbs")
	public ModelAndView payFormMemo(ModelAndView mv, PaymentVO pVO, HttpSession session) {
		session.setAttribute("NAMELIST",pVO.getNameList());
		mv.addObject("stat", "second");
		mv.addObject("nameList", pVO.getNameList());
		mv.setViewName("pay/paymentMemo");
		return mv;
	}
	
	// 선물하기 결제 3단계 이동
	@RequestMapping("/payFormInfo.nbs")
	public ModelAndView payFormInfo(ModelAndView mv, PaymentVO pVO) {
		mv.addObject("stat", "third");
		mv.addObject("pVO", pVO);
		mv.setViewName("pay/paymentInfo");
		return mv;
	}

	
	// 선물하기 결제 4단계 이동
	@RequestMapping("/payFormCheck.nbs")
	public ModelAndView payFormCheck(ModelAndView mv, PaymentVO pVO ,HttpSession session) {
		session.setAttribute("PVO", pVO);
		AccountVO aVO = new AccountVO();
		aVO.setId((String)session.getAttribute("SID"));
		aVO = aDao.selAccountInfo(aVO);
		aVO.setTel("010-1111-1111");
		mv.addObject("aVO", aVO);
		mv.addObject("nameList", session.getAttribute("NAMELIST"));
		mv.addObject("paySel", pVO.getPaySel());
		mv.addObject("stat", "fourth");
		mv.setViewName("pay/paymentCheck");
		return mv;
	}
	
	// 결제 검증, 내역 저장 및 결제 완료
	@RequestMapping(path="/checkPay.nbs", method=RequestMethod.POST)
	@ResponseBody
	public AccountVO checkPay(HttpSession session,
				PaymentVO pVO, String amount) throws IOException {
		// 반환VO
		AccountVO returnVO = new AccountVO();
				// 게임이름       구매자
		String token = pImp.getToken();
			// 결제 완료된 금액
		int paid_amount = pImp.paymentInfo(pVO.getImp_uid(), token);
			// 계산된 금액
		int count_amount = Integer.parseInt(amount);
		// 계산된 금액과 실제 금액이 다를 때
		try {
			if(count_amount != paid_amount) {
	            pImp.payMentCancle(token, pVO.getImp_uid(), paid_amount, "결제 금액 오류");
	            returnVO.setTitle("결제 금액 오류");
	            returnVO.setMsg("처음부터 다시 시도해주세요.");
	            returnVO.setIcon("error");
	            return returnVO;
			}
				// 금액이 같다면 결제내역 저장
			List<String> nameList = (ArrayList<String>)session.getAttribute("NAMELIST");
				// 선물받는 친구 명단
			List<Integer> noList = pDao.getNoList(nameList);
				// 선물할 친구 수 * 게임 수만큼 내역 반복
			int total_cnt = 0;
			int total = noList.size() * pVO.getGameIdList().size();
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
					int cnt = pDao.savePay(pVO);
					if(cnt == 1) {
						total_cnt += 1;
					}else {
			            returnVO.setTitle("결제 오류");
			            returnVO.setMsg("처음부터 다시 시도해주세요.");
			            returnVO.setIcon("error");
			            return returnVO;
					}
				}
			}
			if(total_cnt == total) {
	            returnVO.setTitle("주문 완료");
	            returnVO.setMsg("주문이 성공하였습니다.");
	            returnVO.setIcon("success");
	            return returnVO;
			}			 
		} catch (Exception e) {
			e.printStackTrace();
	        pImp.payMentCancle(token, pVO.getImp_uid(), paid_amount, "결제 에러");
	    }
        returnVO.setTitle("결제 에러");
        returnVO.setMsg("처음부터 다시 시도해주세요.");
        returnVO.setIcon("error");
        return returnVO;
	}
	
	// 환불(구매 후 2주가 지나지 않은 경우만)
	@RequestMapping("/refund.nbs")
	@ResponseBody
	public AccountVO refund(PaymentVO pVO, HttpSession session) {
		AccountVO returnVO = new AccountVO();
		// 게임아이디, 유저아이디 받아서 2주 이내면 환불하고 삭제
		// pVO.setId((String)session.getAttribute("SID"));
		// 결제ID, 낱개 게임 가격, 구매 내역 번호
		pVO = pDao.refundGame(pVO);
			// 결과가 나오지 않았다면 환불 자격이 없음
		if("".equals(pVO.getImp_uid())) {
			returnVO.setTitle("환불 불가능");
			returnVO.setMsg("구매 후 2주가 지났거나 존재하지 않습니다.");
			returnVO.setIcon("error");
		}else {
			try {
				if("N".equals(pVO.getIsrefund())) {
					String token = pImp.getToken();
					// 전액
					// int amount = pImp.paymentInfo(pVO.getImp_uid(), token);
					pImp.payMentCancle(token, pVO.getImp_uid(), pVO.getGame_price(), "환불요청");
					
					int cnt = pDao.refundSuccess(pVO);
					if(cnt == 1) {
						returnVO.setTitle("환불 성공");
						returnVO.setMsg("정상적으로 구매자에게 환불되었습니다.");
						returnVO.setIcon("success");	        	
					} else {throw new Exception();}	        							
				} else {throw new Exception();}
					// else의 경우 강제 exception처리로 catch이동
			} catch(Exception e) {
				returnVO.setTitle("환불 불가능");
				returnVO.setMsg("처리 과정에서 오류가 발생했습니다.");
				returnVO.setIcon("error");
				e.printStackTrace();
			}
		}
		return returnVO;
	}
}
