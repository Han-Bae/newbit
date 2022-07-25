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
	ProfileDao profileDao;
	@Autowired
	AccountService aSrc;
	@Autowired
	PayService pSrc;
	@Autowired
	PaymentImp pImp;
	@Autowired
	ProfileDao proDao;
	@Autowired
	StoreJsonSimple sJson;
	
	// 찜 페이지 이동
	@RequestMapping("/pick.nbs")
	public ModelAndView pick(ModelAndView mv, HttpSession session) {
		String id = (String)session.getAttribute("SID");
		List<PaymentVO> pickList = pDao.getPickList(id);
		List<String> gameIdList = new ArrayList<String>();
		for(PaymentVO pavo : pickList) {
			gameIdList.add(pavo.getGame_id());
		}
		List<StoreVO> gameSVO = new ArrayList<StoreVO>();
		for(int i = 0; i < gameIdList.size(); i++) {
			String gameId = gameIdList.get(i).substring(4,gameIdList.get(i).length()); 
			StoreVO sVO = sJson.getDetailJson(gameId);
			sVO.setAppId(gameIdList.get(i));
			gameSVO.add(sVO);
		}
		
		List<PaymentVO> baList = pDao.getBasketList(id);
		List<String> basketList = new ArrayList<String>();
		for(PaymentVO pavo : baList) {
			basketList.add(pavo.getGame_id());
		}
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
	// 찜목록 추가
	@ResponseBody
	@RequestMapping(path="/addPick.nbs", method=RequestMethod.POST, params="game_id")
	public Map<String, String> addPick(ModelAndView mv, HttpSession session, String game_id) {
		HashMap<String, String> map = new HashMap<String, String>();
		String result = "NO";
		String overlap = "NO";
		List<PaymentVO> pavo =  pDao.getPickList((String)session.getAttribute("SID"));
		List<String> pickList = new ArrayList<String>();
		for(PaymentVO pick : pavo) {
			pickList.add(pick.getGame_id());
		}
		for(String pick : pickList) {
			if(pick.equals(game_id)) {
				// 이미 찜목록에 존재하는 게임이면
				overlap = "YES";
				result = "RETRY";
			}
		}
		// 중복이 아닐때만 장바구니에 저장
		if(overlap.equals("YES") == false) {
			int cnt = pDao.isshowPick(game_id);
			// isshow가 n인 경우라 감춰졌다면 Y로 변경
			if(cnt == 1) {
				cnt = pDao.showPick(game_id);
			}else {				
				PaymentVO pVO = new PaymentVO();
				pVO.setGame_id(game_id);
				pVO.setId((String)session.getAttribute("SID"));
				cnt = 0;
				cnt = pDao.addPick(pVO);
			}
			if(cnt == 1) {
				result = "OK";
			}
		}
		map.put("result", result);
		return map;
	}
	// 장바구니 추가
	@ResponseBody
	@RequestMapping(path="/addBasket.nbs", method=RequestMethod.POST, params={"game_id","game_type"})
	public Map<String, String> addBasket(ModelAndView mv, HttpSession session, String game_id, String game_type) {
		HashMap<String, String> map = new HashMap<String, String>();
		String result = "NO";
		String overlap = "NO";
		List<PaymentVO> pavo =  pDao.getBasketList((String)session.getAttribute("SID"));
		List<String> basketList = new ArrayList<String>();
		for(PaymentVO basket : pavo) {
			basketList.add(basket.getGame_id());
		}
		for(String basket : basketList) {
			if(basket.equals(game_id)) {
				// 이미 장바구니에 존재하는 게임이면
				overlap = "YES";
				result = "RETRY";
			}
		}
		
		String fullgameId = "";
		// 담으려고 하는 게임이 장바구니에 존재하지 않고 game_type이 dlc이면
		if(!overlap.equals("YES") && game_type.equals("dlc")) {
			// fullgameId를 가져와서 
			fullgameId = "App_" + sJson.getFullgameId(game_id.substring(game_id.indexOf("_") + 1));
			//라이브러리에 존재하는지
			StoreVO sVO = new StoreVO();
			sVO.setAppId(fullgameId);
			sVO.setSessionId((String) session.getAttribute("SID"));
			int haveLibrary = profileDao.countLibraryGame(sVO);
			// 장바구니에 존재하는지
			int haveBasket = pDao.countBasketGame(sVO);
			
			// 장바구니에 없거나 라이브러리에 없으면
			if(haveLibrary == 0 && haveBasket == 0) {
				// 장바구니에 isshow가 'N'로 저장되어 있는지
				int cnt = pDao.isshowBasket(game_id);
				int fullgameCnt = pDao.isshowBasket(fullgameId);
				// 둘 다 isshow가 'N'인 경우
				if(cnt == 1 && fullgameCnt == 1) {
					
					cnt = pDao.showBasket(game_id);
					fullgameCnt = pDao.showBasket(fullgameId);
					
				// dlc가 N이고 fullgame은 장바구니에 아예 없는 경우
				} else if(cnt == 1 && fullgameCnt == 0) {
					
					cnt = pDao.showBasket(game_id);
					
					PaymentVO pVO = new PaymentVO();
					pVO.setGame_id(fullgameId);
					pVO.setId((String)session.getAttribute("SID"));
					fullgameCnt = 0;
					fullgameCnt = pDao.addBasket(pVO);
				
				// fullgame이 N이고 dlc는 장바구니에 아예 없는 경우
				} else if(cnt == 0 && fullgameCnt == 1) {
					
					fullgameCnt = pDao.showBasket(fullgameId);
					
					PaymentVO pVO = new PaymentVO();
					pVO.setGame_id(game_id);
					pVO.setId((String)session.getAttribute("SID"));
					cnt = 0;
					cnt = pDao.addBasket(pVO);
				
				// 둘 다 장바구니에 아예 없어서 새로 추가해야 하는 경우 
				} else {
					// 본편
					PaymentVO fullgamePayVO = new PaymentVO();
					fullgamePayVO.setGame_id(fullgameId);
					fullgamePayVO.setId((String)session.getAttribute("SID"));
					fullgameCnt = 0;
					fullgameCnt = pDao.addBasket(fullgamePayVO);
					// dlc
					PaymentVO dlcPayVO = new PaymentVO();
					dlcPayVO.setGame_id(game_id);
					dlcPayVO.setId((String)session.getAttribute("SID"));
					cnt = 0;
					cnt = pDao.addBasket(dlcPayVO);
					
				}
				
				String together = "no";
				if(cnt == 1 && fullgameCnt == 1) {
					together = "yes";
					result = "OK";
				}
				
				map.put("together", together);
				map.put("result", result);
				return map;
			}
		}
		
		// 중복이 아닐때만 장바구니에 저장
		if(overlap.equals("YES") == false) {
			int cnt = pDao.isshowBasket(game_id);
			// isshow가 n인 경우라 감춰졌다면 Y로 변경
			if(cnt == 1) {
				cnt = pDao.showBasket(game_id);
			}else {				
				PaymentVO pVO = new PaymentVO();
				pVO.setGame_id(game_id);
				pVO.setId((String)session.getAttribute("SID"));
				cnt = 0;
				cnt = pDao.addBasket(pVO);
			}
			if(cnt == 1) {
				result = "OK";
			}
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
		System.out.println(url.substring(url.length()-8));
		if(url.substring(url.length()-8).equals("pick.nbs")) {
			System.out.println("들어감");
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
		List<PaymentVO> baList = pDao.getBasketList(id);
		List<String> basketList = new ArrayList<String>();
		for(PaymentVO pavo : baList) {
			basketList.add(pavo.getGame_id());
		}
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
    		// 결제한 게임만큼 반복
    		for(int i = 0; i < gameIdList.size(); i++) {
    			String gameId = gameIdList.get(i).substring(4,gameIdList.get(i).length()); 
    			StoreVO sVO = sJson.getDetailJson(gameId);
    			sVO.setAppId(gameIdList.get(i));
    			// 결제메일용 VO 저장
    			gameSVO.add(sVO);
    			// 라이브러리 저장용 기능
    			sVO.setSessionId(pVO.getId());
    			proDao.addLibraryGame(sVO);
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
            
            // 결제 완료 알림 보내기
            String gameName = new String();
            for(int i = 0; i < pVO.getsVOList().size(); i++) {
            	gameName += pVO.getsVOList().get(i).getTitle()+", ";
            }
            NoticeVO nVO = new NoticeVO();
            nVO.setNo(aVO.getNo());
            nVO.setId((String)session.getAttribute("SID"));
            nVO.setTitle("게임 결제가 완료되었습니다.");
            nVO.setBody(nVO.getId()+"회원님이 결제하신 게임은 "+gameName.substring(0, gameName.length()-2)+"입니다.");
            aDao.insertNotice(nVO);
            
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
	    			// 결제메일용 VO 저장
	    			gameSVO.add(sVO);
	    			// 라이브러리 저장용 기능
	    			for(String nick : pVO.getNameList()) {
	    				sVO.setSessionId(aDao.getNickInfo(nick).getId());
	    																																																																																																																																																																																																																																																																																																																																																				proDao.addLibraryGame(sVO);
	    			}
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
	            
	            // 결제 완료 알림 보내기
	            String gameName = new String();
	            for(int i = 0; i < pVO.getsVOList().size(); i++) {
	            	gameName += pVO.getsVOList().get(i).getTitle()+", ";
	            }
	            NoticeVO nVO = new NoticeVO();
	            nVO.setNo(aVO.getNo());
	            nVO.setId((String)session.getAttribute("SID"));
	            nVO.setTitle("게임 선물이 완료되었습니다.");
	            nVO.setBody(nVO.getId()+"회원님이 선물하신 게임은 "+gameName.substring(0, gameName.length()-2)+"입니다.");
	            aDao.insertNotice(nVO);
	            
	            // 선물 받는 친구에게 알림 보내기
	            for(int i = 0; i < pVO.getNameList().size(); i++) {
	            	AccountVO friendVO = new AccountVO();
	            	friendVO = aDao.getNickInfo(pVO.getNameList().get(i));
	            	NoticeVO presentVO = new NoticeVO();
	            	presentVO.setNo(friendVO.getNo());
	            	presentVO.setTitle(aVO.getNickname()+"님께서 보낸 선물!");
	            	presentVO.setBody(aVO.getNickname()+"님께서 "+friendVO.getNickname()+"님께 "+gameName.substring(0, gameName.length()-2)+"를 선물하셨습니다.\r\n"
	            			+"아래는 "+aVO.getNickname()+"님께서 보내신 메세지입니다.\r\n"
	            			+pVO.getPresentTitle()+"\r\n"
	            			+pVO.getPresentTitle());
	            	aDao.insertNotice(presentVO);
	            }
	            
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
	@RequestMapping(path="/refund.nbs", method=RequestMethod.POST, params= {"game_id","name"})
	@ResponseBody
	public AccountVO refund(PaymentVO pVO, HttpSession session, String game_id, String name) {
		AccountVO returnVO = new AccountVO();
		AccountVO aVO = new AccountVO();
		// 게임아이디, 유저아이디 받아서 2주 이내면 환불하고 삭제
		pVO.setId((String)session.getAttribute("SID"));
		aVO.setId(pVO.getId());
		// 결제ID, 낱개 게임 가격, 구매 내역 번호
		pVO = pDao.refundGame(pVO);
		pVO.setGame_id(game_id);
		pVO.setName(name);
		System.out.println(pVO);
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
					if(cnt > 0) {
						// 알림 보내기
							// 본인이 결제한 경우
						NoticeVO nVO = new NoticeVO();
						if(pVO.getBuy_no() == pVO.getAccount_no()) {
				            nVO.setNo(pVO.getBuy_no());
				            nVO.setId((String)session.getAttribute("SID"));
				            nVO.setTitle("게임 환불이 완료되었습니다.");
				            nVO.setBody(nVO.getId()+"회원님이 환불하신 게임은 "+pVO.getName()+"입니다.");
				            aDao.insertNotice(nVO);			            
				            // 선물받은 게임인 경우			            	
			            } else {
			            	nVO.setNo(pVO.getBuy_no());
			            	nVO.setId((String)session.getAttribute("SID"));
			            	nVO.setTitle(aDao.noGetNick(pVO.getAccount_no()).getNickname()+"님이 환불하셨습니다.");
			            	nVO.setBody(nVO.getId()+"회원님이 환불하신 게임은 "+pVO.getName()+"입니다.");
			            	aDao.insertNotice(nVO);			            
			            	
			            	nVO.setNo(pVO.getAccount_no());
			            	nVO.setId((aDao.noGetNick(pVO.getAccount_no()).getId()));
			            	nVO.setTitle("선물받은 게임을 환불하셨습니다.");
			            	nVO.setBody(aDao.noGetNick(pVO.getBuy_no()).getNickname()+"회원님이 선물해주신 "+pVO.getName()+"게임의 환불이 완료되었습니다.");
			            	aDao.insertNotice(nVO);			            
			            }
			            
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
