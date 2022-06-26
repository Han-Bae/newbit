package com.newbit.www.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.newbit.www.dao.*;
import com.newbit.www.util.*;
import com.newbit.www.vo.*;

/**
 * 이 클래스는 댓글게시판 관련 요청을 처리할 클래스
 * @author	전은석
 * @since	2022.06.08
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.06.08	-	담당자 : 전은석
 * 									클래스 제작, 
 * 									리스트보기 요청 처리
 *
 */

@Controller
@RequestMapping("/reBoard")
public class ReBoard {
	@Autowired
	ReBoardDao rDao;
	
	/**
	 * 댓글게시판 리스트보기 요청
	 */
	@RequestMapping("/reBoardList.blp")
	public ModelAndView reBoardList(ModelAndView mv, PageUtil page, String msg) {
		// 할일
		// 데이터베이스에서 데이터 가져오고
		// PageUtil이 먼저 만들어져야하기 때문에
		// 총 게시글 수부터 가져온다.
		int total = rDao.getTotal();
		
		// PageUtil 을 셋팅해준다.
		page.setPage(total);
		
		// 리스트를 조회한다.
		List<BoardVO> list = rDao.getList(page);
		
		if(msg != null) {
			mv.addObject("MSG", msg);
		}
		
		mv.addObject("LIST", list);
		mv.addObject("PAGE", page);
		mv.setViewName("reBoard/reBoardList");
		
		return mv;
	}
	
	@RequestMapping(path="/reBoardWrite.blp", method=RequestMethod.POST, params={"id", "nowPage"})
	public ModelAndView reBoardWrite(ModelAndView mv, String id, String nowPage) {
		BoardVO bVO = rDao.getWriterInfo(id);
		
		// 데이터 넘겨주고
		mv.addObject("DATA", bVO);
		// 뷰 정하고
		mv.setViewName("reBoard/reBoardWrite");
		// 반환하고
		return mv;
	}
	
	@RequestMapping("/writeProc.blp")
	public ModelAndView writeProc(ModelAndView mv, String nowPage, BoardVO bVO) {
		int cnt = rDao.addReBoard(bVO);
		String view = "/www/reBoard/reBoardList.blp";
		if(cnt == 0) {
			// 글 등록에 실패한경우 ==> 글쓰기로 돌려보낸다.
			view = "/www/reBoard/reBoardWrite.blp";
		} else {
			// 글 등록에 성공한 경우...
			nowPage = "1";
			mv.addObject("MSG", "게시글 등록에 성공했습니다.");
		}
		
		mv.addObject("VIEW", view);
		mv.addObject("NOWPAGE", nowPage);
		
		mv.setViewName("reBoard/redirect");
		return mv;
	}
	
	// 댓글 작성 폼보기 처리함수
	@RequestMapping("/commentWrite.blp")
	public ModelAndView commentWrite(ModelAndView mv, BoardVO bVO, String nowPage, String vw) {
		bVO = rDao.getCommentData(bVO);
		
		// 데이터 셋팅하고
		mv.addObject("DATA", bVO);
		
		// 뷰 부르고
		mv.setViewName("reBoard/reBoardComment");
		return mv;
	}
	

	/**
	 * 댓글 등록 요청 처리함수 
	 */
	@RequestMapping("/commentProc.blp")
	public ModelAndView writeProc(ModelAndView mv, BoardVO bVO, String nowPage, String vw) {
		
		int result = rDao.addReBoard(bVO);
		
		if(result == 1) {
			// 댓글등록 성공
			mv.addObject("VIEW", "/www/reBoard/reBoardList.blp");
		} else {
			// 등록실패
			mv.addObject("VIEW", "/www/reBoard/commentWrite.blp");
		}
		mv.addObject("NOWPAGE", nowPage);
		
		// 뷰 부르고
		mv.setViewName("reBoard/redirect");
		return mv;
	}
	
	/**
	 *  게시글 수정 폼보기 요청 처리함수
	 */
	@RequestMapping("/reBoardEdit.blp")
	public ModelAndView reBoardEdit(ModelAndView mv, BoardVO bVO, String nowPage, String vw) {
		bVO = rDao.getEditData(bVO);
		
		mv.addObject("DATA", bVO);
		mv.setViewName("reBoard/reBoardEdit");
		return mv;
	}
	
	/**
	 * 게시글 수정 요청 처리함수
	 */
	@RequestMapping("/editProc.blp")
	public ModelAndView editProc(ModelAndView mv, BoardVO bVO, String nowPage, String vw) {
		int result = rDao.editProc(bVO);
		
		if(result == 1) {
			// 수정 성공
			mv.addObject("VIEW", "/www/reBoard/reBoardList.blp");
		} else {
			// 수정 실패
			mv.addObject("VIEW", "/www/reBoard/reBoardEdit.blp");
		}
		// 데이터 심고
		mv.addObject("NOWPAGE", nowPage);
		
		// 뷰 부르고
		mv.setViewName("reBoard/redirect");
		return mv;
	}
	
	/**
	 * 게시글 삭제 요청 처리함수
	 */
	@RequestMapping("/delReBoard.blp")
	public ModelAndView delReBoard(ModelAndView mv, BoardVO bVO, String nowPage, String vw) {
		rDao.delReBoard(bVO);
		
		// 데이터 심고
		mv.addObject("VIEW", vw);
		mv.addObject("NOWPAGE", nowPage);
		
		// 뷰 부르고
		mv.setViewName("reBoard/redirect");
		return mv;
	}
}
