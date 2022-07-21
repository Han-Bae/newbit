package com.newbit.www.controller.kth;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.newbit.www.api.StoreJsonSimple;
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
	AccountService aSrc;
	@Autowired
	PayService pSrc;
	@Autowired
	PaymentImp pImp;
	@Autowired
	StoreJsonSimple sJson;
	
	// 찜 페이지 이동
	@RequestMapping("/pick.nbs")
	public ModelAndView pick(ModelAndView mv, HttpSession session) {
		String id = (String)session.getAttribute("SID");
		List<String> gameIdList = pDao.getPickList(id);
		List<StoreVO> gameSVO = new ArrayList<StoreVO>();
		for(int i = 0; i < gameIdList.size(); i++) {
			String gameId = gameIdList.get(i).substring(4,gameIdList.get(i).length()); 
			StoreVO sVO = sJson.getDetailJson(gameId);
			sVO.setAppId(gameIdList.get(i));
			gameSVO.add(sVO);
		}
		List<String> basketList = pDao.getBasketList(id);
		List<StoreVO> basketSVO = new ArrayList<StoreVO>();
		for(int i = 0; i < basketList.size(); i++) {
			String gameId = basketList.get(i).substring(4,basketList.get(i).length()); 
			StoreVO sVO = sJson.getDetailJson(gameId);
			sVO.setAppId(basketList.get(i));
			basketSVO.add(sVO);
		}
		mv.addObject("gameList", gameSVO);
		mv.addObject("basketList", basketSVO);
		mv.setViewName("pay/pick");
		return mv;
	}
	// 장바구니 추가
	@ResponseBody
	@RequestMapping(path="/addBasket.nbs", method=RequestMethod.POST, params="game_id")
	public Map<String, String> addBasket(ModelAndView mv, HttpSession session, String game_id) {
		HashMap<String, String> map = new HashMap<String, String>();
		String result = "NO";
		PaymentVO pVO = new PaymentVO();
		pVO.setGame_id(game_id);
		pVO.setId((String)session.getAttribute("SID"));
		int cnt = 0;
		cnt = pDao.addBasket(pVO);
		if(cnt == 1) {
			result = "OK";
		}
		map.put("result", result);
		return map;
	}
	// 찜목록, 장바구니 삭제
	@ResponseBody
	@RequestMapping(path="/delPick.nbs", method=RequestMethod.POST, params={"game_id","url"})
	public Map<String, String> delPick(ModelAndView mv, String game_id, String url) {
		HashMap<String, String> map = new HashMap<String, String>();
		String result = "NO";
		int cnt = 0;
		// 찜목록에서 삭제 시
		if(url == "http://localhost/www/payment/pick.nbs") {
			cnt = pDao.delPick(game_id);
			// 장바구니에서 삭제 시
		} else {
			cnt = pDao.delBasket(game_id);
		}
		if(cnt == 1) {
			result = "OK";
		}
		map.put("result", result);
		return map;
	}
	
	// 장바구니 페이지 이동
	@RequestMapping("/basket.nbs")
	public ModelAndView basket(ModelAndView mv, HttpSession session) {
		String id = (String)session.getAttribute("SID");
		List<String> basketList = pDao.getBasketList(id);
		List<StoreVO> basketSVO = new ArrayList<StoreVO>();
		for(int i = 0; i < basketList.size(); i++) {
			String gameId = basketList.get(i).substring(4,basketList.get(i).length()); 
			StoreVO sVO = sJson.getDetailJson(gameId);
			sVO.setAppId(basketList.get(i));
			basketSVO.add(sVO);
		}
		mv.addObject("gameList", basketSVO);
		mv.setViewName("pay/basket");
		return mv;
	}

	// 셀프 결제 1단계 이동
	@RequestMapping("/myselfPayInfo.nbs")
	public ModelAndView myselfPayInfo(ModelAndView mv, PaymentVO pVO, HttpSession session) {
		session.setAttribute("GAMELIST", pVO.getGameIdList());
		mv.addObject("stat", "first");
		mv.addObject("gameIdList", pVO.getGameIdList());
		mv.setViewName("pay/myselfPayInfo");
		return mv;
	}
	
	// 셀프 결제 2단계 이동
	@RequestMapping("/myselfPayCheck.nbs")
	public ModelAndView myselfPayCheck(ModelAndView mv, PaymentVO pVO ,HttpSession session) {
		AccountVO aVO = new AccountVO();
		aVO.setId((String)session.getAttribute("SID"));
		aVO = aDao.selAccountInfo(aVO);

		List<String> gameList = (ArrayList<String>)session.getAttribute("GAMELIST");
		List<StoreVO> gameSVO = new ArrayList<StoreVO>();
		for(int i = 0; i < gameList.size(); i++) {
			String gameId = gameList.get(i).substring(4,gameList.get(i).length()); 
			StoreVO sVO = sJson.getDetailJson(gameId);
			sVO.setAppId(gameList.get(i));
			gameSVO.add(sVO);
		}
		
		aVO.setTel("010-1111-1111");
		mv.addObject("aVO", aVO);
		mv.addObject("stat", "second");
		mv.addObject("gameIdList", gameSVO);
		mv.addObject("paySel", pVO.getPaySel());
		mv.setViewName("pay/myselfPayCheck");
		return mv;
	}
	
	// 선물하기 결제 1단계 이동
	@RequestMapping("/payForm.nbs")
	public ModelAndView payForm(ModelAndView mv, HttpSession session, PaymentVO pVO) {
		session.setAttribute("GAMELIST", pVO.getGameIdList());
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

		List<String> gameList = (ArrayList<String>)session.getAttribute("GAMELIST");
		List<StoreVO> gameSVO = new ArrayList<StoreVO>();
		for(int i = 0; i < gameList.size(); i++) {
			String gameId = gameList.get(i).substring(4,gameList.get(i).length()); 
			StoreVO sVO = sJson.getDetailJson(gameId);
			sVO.setAppId(gameList.get(i));
			gameSVO.add(sVO);
		}
		
		mv.addObject("aVO", aVO);
		mv.addObject("gameIdList", gameSVO);
		mv.addObject("nameList", session.getAttribute("NAMELIST"));
		mv.addObject("paySel", pVO.getPaySel());
		mv.addObject("stat", "fourth");
		mv.setViewName("pay/paymentCheck");
		return mv;
	}
	// 셀프 결제
	// 결제 검증, 내역 저장 및 결제 완료
	@RequestMapping(path="/selfCheckPay.nbs", method=RequestMethod.POST)
	@ResponseBody
	public AccountVO selfCheckPay(HttpSession session,
				PaymentVO pVO, String amount) throws IOException {
		// 반환VO
		AccountVO returnVO = new AccountVO();
		System.out.println(pVO);
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
			pSrc.addPayData(pVO, session);
    		pVO.setId((String)session.getAttribute("SID"));
            pVO.setResult("OK");
            
    		List<String> gameIdList = (ArrayList<String>)session.getAttribute("GAMELIST");
    		List<StoreVO> gameSVO = new ArrayList<StoreVO>();
    		for(int i = 0; i < gameIdList.size(); i++) {
    			String gameId = gameIdList.get(i).substring(4,gameIdList.get(i).length()); 
    			StoreVO sVO = sJson.getDetailJson(gameId);
    			sVO.setAppId(gameIdList.get(i));
    			gameSVO.add(sVO);
    		}
    		// 게임 정보 저장
            pVO.setsVOList(gameSVO);
            
            returnVO.setTitle("주문 완료");
            returnVO.setMsg("주문이 성공하였습니다.");
            returnVO.setIcon("success");
            
            	// 게임 결제 메일 보내기
            AccountVO aVO = new AccountVO();
            aVO.setId((String)session.getAttribute("SID"));
            aVO = aDao.selAccountInfo(aVO);
            aVO.setpVO(pVO);
            aSrc.sendMail(aVO);
            
            return returnVO;
		} catch (Exception e) {
			pVO.setResult("NO");
			e.printStackTrace();
	        pImp.payMentCancle(token, pVO.getImp_uid(), paid_amount, "결제 에러");
	    }
        returnVO.setTitle("결제 에러");
        returnVO.setMsg("처음부터 다시 시도해주세요.");
        returnVO.setIcon("error");
        return returnVO;
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
				pVO.setNameList((ArrayList<String>)session.getAttribute("NAMELIST"));
				pSrc.addPayData(pVO, session);
	            returnVO.setTitle("주문 완료");
	            returnVO.setMsg("주문이 성공하였습니다.");
	            returnVO.setIcon("success");
	            
	    		List<String> gameIdList = pVO.getGameIdList();
	    		List<StoreVO> gameSVO = new ArrayList<StoreVO>();
	    		for(int i = 0; i < gameIdList.size(); i++) {
	    			String gameId = gameIdList.get(i).substring(4,gameIdList.get(i).length()); 
	    			StoreVO sVO = sJson.getDetailJson(gameId);
	    			sVO.setAppId(gameIdList.get(i));
	    			gameSVO.add(sVO);
	    		}
	    		// 게임 정보 저장
	            pVO.setsVOList(gameSVO);
	            
            	// 게임 결제 메일 보내기
	            AccountVO aVO = new AccountVO();
	            aVO.setId((String)session.getAttribute("SID"));
	            aVO = aDao.selAccountInfo(aVO);
	            aVO.setpVO(pVO);
	            aSrc.sendMail(aVO);
	            
	    		pVO.setId((String)session.getAttribute("SID"));
	            pVO.setResult("OK");
	            return returnVO;	 
		} catch (Exception e) {
			e.printStackTrace();
			pVO.setResult("NO");
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
