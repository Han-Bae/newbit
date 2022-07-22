package com.newbit.www.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpSession;

import com.newbit.www.vo.ReviewVO;

public class ReviewDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public int addReview(ReviewVO rVO) {
		 System.out.println("dao" + rVO.getBody());
		 System.out.println("dao" + rVO.getAccount_no());
		 System.out.println("게임넘버모냐구?" + rVO.getGame_no());
		 
		 // 강제로 하드코딩할때는 set을 해서 임의로 값을 넣어준다 - 쁭히
			/*
			 * rVO.setNo(4444); //rVO.setAccount_no(1111); rVO.setGame_no(1111111111);
			 * rVO.setIsnewbit("Y"); rVO.setIsgood("G"); rVO.setIsshow("Y");
			 */

		 
		return sqlSession.insert("rSQL.addReview", rVO);
	}
	
	public List<ReviewVO> getReview() {
		return sqlSession.selectList("rSQL.getReview");
	}
	
	public List<ReviewVO> getGameReview(int gameNo) {
		System.out.println("gameNo---------------------"+gameNo);
		return sqlSession.selectList("rSQL.getGameReview", gameNo);
	}
	
	// 리뷰상세조회
	public List<ReviewVO> getreviewDetail(int accountNo) {
		return sqlSession.selectList("rSQL.getreviewDetail", accountNo);
	}

	public List<ReviewVO> getreviewYN(int dreviewNo) {
		System.out.println("dreviewNo---------------------"+dreviewNo);
		return sqlSession.selectList("rSQL.getreviewYN", dreviewNo);
	}
	
	//아이디 기준으로 ACCOUNT테이블에서 고객번호(no) 찾기
	public int getFindNo(String id) {
		return sqlSession.selectOne("rSQL.getFindNo", id);
	}
	
	public int getSelIdCnt(ReviewVO rVO) {
		return sqlSession.selectOne("rSQL.getSelIdCnt", rVO);
	}
	
	public int addReviewYN(ReviewVO rVO) {
		System.out.println("dao getReviewYN-----" + rVO.getReviewYN());
		System.out.println("dao getDreviewNo------" + rVO.getDreviewNo());
		System.out.println("dao getSid-----" + rVO.getSid());
		return sqlSession.insert("rSQL.addReviewYN", rVO);
	}
	
	// update 함수는 int를 반환하는데 업데이트가 있으면 1, 없으면 0을 반환한다.
	public int delReview(int id) {
		return sqlSession.update("rSQL.delReview", id);
	}
	
	//고객no로 리뷰를 평가한 건수 조회
	public int getSelRewiewCnt(ReviewVO rVO) {
		System.out.println("dao getNo------" + rVO.getNo());
		System.out.println("dao getDreviewNo------" + rVO.getDreviewNo());
		
		return sqlSession.selectOne("rSQL.getSelRewiewCnt", rVO);
	}
}
