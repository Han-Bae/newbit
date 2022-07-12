package com.newbit.www.controller.phk;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.RedirectView;

import com.newbit.www.dao.ReviewDao;
import com.newbit.www.dao.UploadDao;
import com.newbit.www.vo.ReviewVO;
import com.newbit.www.vo.UploadVO;

@Controller
@RequestMapping("/review")
public class Review {

	// 리뷰메인페이지를 reviewMain.nbs로 요청이 왔을때 띄워주는 기능

	@RequestMapping("/reviewMain.nbs")
	public ModelAndView reviewMain(ModelAndView mv, HttpSession session) {

		List<ReviewVO> list = rDao.getReview();
		//List<UploadVO> ulist = uDao.getScreenShot();

		// 작성자, 작성일, 평가, 평가리뷰글
		for (int i = 0; i < list.size(); i++) {
			/*
			 * System.out.println("작성자#########" + list.get(i).getAccount_no());
			 * System.out.println("작성일#########" + list.get(i).getRdate());
			 * System.out.println("게임평가#########" + list.get(i).getIsgood());
			 * System.out.println("평가리뷰글#########" + list.get(i).getBody());
			 */
		}
		// 뷰에 데이터 심고 // EL형식으로 바꾸어야 JSP에서 바로 사용할 수 있다.
		mv.addObject("LIST", list);

		mv.setViewName("review/reviewMain");
		return mv;
	}

	@Autowired
	ReviewDao rDao;
	@Autowired
	UploadDao uDao;

	/*----------------------------------------------------------------------*/

	// 평가리뷰 등록하는 기능
	@RequestMapping(path = "/reviewWrite.nbs", method = RequestMethod.POST)
	public ModelAndView joinProc(ReviewVO rVO, ModelAndView mv, RedirectView rv, HttpSession session) {

		System.out.println("#########" + rVO.getBody());

		int cnt = rDao.addReview(rVO);

		if (cnt == 1) {
			// 성공한 경우
			System.out.println("성공");
			rv.setUrl("/www/review/reviewMain.nbs");

		} else {
			rv.setUrl("/www/review/reviewMain.nbs");
		}

		mv.setView(rv);

		return mv;

	}

	// 리뷰메인페이지를 reviewMain.nbs로 요청이 왔을때 띄워주는 기능
	@RequestMapping("/gameReviewList.nbs")
	public ModelAndView gameReviewList(ModelAndView mv, ReviewVO rVO) {
		System.out.println("게임타이틀 들어오는가?============" + rVO.getGame_no());

		int gameNo = rVO.getGame_no();

		List<ReviewVO> list = rDao.getGameReview(gameNo);

		// 작성자, 작성일, 평가, 평가리뷰글
		for (int i = 0; i < list.size(); i++) {
			/*
			 * System.out.println("작성자#########" + list.get(i).getAccount_no());
			 * System.out.println("작성일#########" + list.get(i).getRdate());
			 * System.out.println("게임평가#########" + list.get(i).getIsgood());
			 * System.out.println("평가리뷰글#########" + list.get(i).getBody());
			 * System.out.println("평가리뷰 글번호#########" + list.get(i).getNo());
			 */
		}
		// 뷰에 데이터 심고 // EL형식으로 바꾸어야 JSP에서 바로 사용할 수 있다.
		mv.addObject("LIST", list);

		mv.setViewName("review/reviewMain");
		return mv;
	}

	// 리뷰 상세보기 내용 조회용
	@RequestMapping(path = "/reviewDetail.nbs", method = RequestMethod.POST, params = { "accountNo", "dreviewNo" })
	@ResponseBody
	public Map<String, Object> reviewDetail(ReviewVO rVO, HttpSession session, int accountNo, int dreviewNo) {

		Map<String, Object> map = new HashMap<String, Object>();
		String result = "NO";
		String divReview = "OK"; //JSP에서 평가가 유용한가요 DIV영역 처리용

		String sid = (String) session.getAttribute("SID");
		System.out.println("세션에서 받아온ID===================" + sid);
		System.out.println("accountNo===================" + accountNo);
		System.out.println("dreviewNo===================" + dreviewNo);

		// 세션ID를 가지고 ACCOUNT테이블에서 로그인 한 고객의 NO조회
		int no = rDao.getFindNo(sid);
		rVO.setNo(no);
		map.put("SNO", no); // 로그인한 고객의 NO

		List<ReviewVO> list = rDao.getreviewDetail(accountNo);
		List<ReviewVO> listYN = rDao.getreviewYN(dreviewNo);
		
		// 고객NO로 리뷰평가를 했는지 확인
		rVO.setDreviewNo(dreviewNo);
		int idCnt = rDao.getSelRewiewCnt(rVO);
		System.out.println("##### 고객리뷰 평가 했니?  ####" + idCnt);
		
		if(idCnt == 1) {//이미 고객리뷰를 평가한 사람인 경우
			divReview = "NO";//DIV 영역 보여주지 말자
		}

		//System.out.println("야야야----유용데이터 왔능가?" + listYN);

		for (int i = 0; i < list.size(); i++) {
			/*
			 * System.out.println("리뷰글번호===" + list.get(i).getNo());
			 * System.out.println("작성자===" + list.get(i).getAccount_no());
			 * System.out.println("작성일===" + list.get(i).getRdate());
			 * System.out.println("게임평가===" + list.get(i).getIsgood());
			 * System.out.println("평가리뷰글===" + list.get(i).getBody());
			 */

			// map이라는 제이슨 형태(키:값)로 변수 만들어서 담는다.
			map.put("NO", list.get(i).getNo()); // 평가글 번호
			map.put("ACCOUNTNO", list.get(i).getAccount_no()); // 작성자
			map.put("RDATE", list.get(i).getRdate()); // 작성일
			map.put("ISGOOD", list.get(i).getIsgood()); // 게임평가
			map.put("BODY", list.get(i).getBody()); // 리뷰글
		}

		for (int i = 0; i < listYN.size(); i++) {
			/*
			 * System.out.println("리뷰글번호===" + listYN.get(i).getReviewno());
			 * System.out.println("유용해요===" + listYN.get(i).getGood());
			 * System.out.println("유용하지않아요===" + listYN.get(i).getBad());
			 */
			// map이라는 제이슨 형태(키:값)로 변수 만들어서 담는다.
			map.put("REVIEW_NO", listYN.get(i).getReviewno()); // 평가글 번호
			map.put("GOODYN", listYN.get(i).getGood()); // 유용yes
			map.put("BADYN", listYN.get(i).getBad()); // 유용no

			 
		}

		// dao에서 가져온 리스트가 정상적으로 들어왔는지 사이즈0 보다 크면
		// 정상으로 OK 해서 result 라는 변수에 담는다.

		if (list.size() > 0) {
			result = "OK";
		}

		// jsp에서 쓰여질 result를 만든다.
		// EL형식으로 바꾸어야 에서 바로 사용할 수 있다.
		map.put("result", result);
		map.put("divReview", divReview); //평가가 유용한지 div 표현여부 값

		return map;
	}

	//
	@RequestMapping(path = "/reviewYN.nbs", method = RequestMethod.POST)
	public ModelAndView reviewYN(ReviewVO rVO, ModelAndView mv, RedirectView rv, HttpSession session) {

		int reviewcnt = 0; // 리뷰평가 정상 저장 건수

		System.out.println("[리뷰gb테이블 저장용]##### getReviewYN ####" + rVO.getReviewYN());
		System.out.println("[리뷰gb테이블 저장용]##### getDreviewNo ####" + rVO.getDreviewNo());
		String sid = (String) session.getAttribute("SID");
		System.out.println("[리뷰gb테이블 저장용]##### sid ####" + sid);

		// 세션으로 받아온 ID값을 VO형태로 저장.
		rVO.setSid(sid);

		// 세션ID를 가지고 ACCOUNT테이블에서 고객 NO조회
		int no = rDao.getFindNo(sid);
		rVO.setNo(no);

		// 고객NO로 리뷰평가를 했는지 확인
		int idCnt = rDao.getSelIdCnt(rVO);
		System.out.println("##### idCnt ####" + idCnt);

		// 리뷰 평가를 한번도 하지 않았을 경우에만 리뷰 평가 저장
		if (idCnt == 0) {
			System.out.println("##### 리뷰 평가 저장하러 가자 ####");
			reviewcnt = rDao.addReviewYN(rVO);
		} else {
			// 리뷰 평가를 한번이라도 한 경우 메인페이지로 가나?
			rv.setUrl("/www/review/reviewMain.nbs");
		}

		if (reviewcnt == 1) {// 리뷰 평가가 정상 저장된 경우 리뷰메인페이지로 가나?
			rv.setUrl("/www/review/reviewMain.nbs");
		}

		mv.setView(rv);

		return mv;

	}
	
	
	/*---------------------------------------------------------------------*/
	// 삭제기능
	@RequestMapping("/delReview.nbs")
	public ModelAndView delReview(ModelAndView mv, String id, RedirectView rv, HttpSession session, ReviewVO rVO ) {
		String sid = (String) session.getAttribute("SID");
		
		if(sid == null) {
			rv.setUrl("/www/review/reviewMain.nbs");
			mv.setView(rv);
			return mv;
		}
		
		System.out.println("세션아이디########" + sid);
		
		// 세션ID를 가지고 ACCOUNT테이블에서 고객 NO조회
		int no = rDao.getFindNo(sid);
		rVO.setNo(no);
				
		int cnt = rDao.delReview(rVO.getNo());
		
		if(cnt == 1) {
			// 세션에 기억시켜둔 데이터를 삭제하고
			session.removeAttribute("SID");
			rv.setUrl("/www/review/reviewMain.nbs");
		} else {
			rv.setUrl("/www/review/reviewMain.nbs");
		}
		
		mv.setView(rv);
		return mv;
	}
	
	

}
