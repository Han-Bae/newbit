package com.newbit.www.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.newbit.www.vo.*;

public class DelDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;
// 계정 삭제하기 전 no로 확인
	public List<AccountVO> checkNo(){
		return sqlSession.selectList("dSQL.checkNo");
	}
	// 해당 계정 정보 삭제
		// 해당 계정의 장바구니, 찜목록 삭제
	public int delAccBasket(int no) {
		return sqlSession.delete("dSQL.delAccBasket", no);
	}
	public int delAccPick(int no) {
		return sqlSession.delete("dSQL.delAccPick", no);
	}
		// 해당 계정의 친구목록 삭제
	public int delAccMe(int no) {
		return sqlSession.delete("dSQL.delAccMe", no);
	}
	public int delAccFriend(int no) {
		return sqlSession.delete("dSQL.delAccFriend", no);
	}
		// 프사 삭제
	public int delAccProfile(int no) {
		return sqlSession.delete("dSQL.delAccProfile", no);
	}
		// 라이브러리 삭제
	public int delAccLibrary(int no) {
		return sqlSession.delete("dSQL.delAccLibrary", no);
	}
// 계정의 커뮤니티 삭제
	// 리뷰
		// 리뷰 번호 받기
	public List<Integer> getReview(int no) {
		return sqlSession.selectList("dSQL.getReview", no);
	}
		// 리뷰gb 삭제
	public int delReviewGB(int no) {
		return sqlSession.delete("dSQL.delReviewGB", no);
	}
	public int delAccReviewGB(int no) {
		return sqlSession.delete("dSQL.delAccReviewGB", no);
	}
		// 리뷰 삭제
	public int delAccReview(int no) {
		return sqlSession.delete("dSQL.delAccReview", no);
	}
	// 스크린샷
		// 스크린샷 번호 받기
	public List<Integer> getScreen(int no){
		return sqlSession.selectList("dSQL.getScreen", no);
	}
		// 스크린샷 리플삭제(계정번호)
	public int delAccSSReply(int no) {
		return sqlSession.delete("dSQL.delAccSSReply", no);
	}
		// 스크린샷 아래 댓글 삭제(스크린샷번호)
	public int delSSReply(int no) {
		return sqlSession.delete("dSQL.delSSReply", no);
	}
	// 스크린샷 관련 정보 삭제(스크린샷번호)
	public int delSSImg(int no) {
		return sqlSession.delete("dSQL.delSSImg", no);
	}
	public int delSSGb(int no) {
		return sqlSession.delete("dSQL.delSSGb", no);
	}
	// 스크린샷 삭제(계정번호)
	public int delAccSS(int no) {
		return sqlSession.delete("dSQL.delAccSS", no);
	}
// 알림 및 신고내역
	public int delNotice(int no) {
		return sqlSession.delete("dSQL.delNotice", no);
	}
	public int delReport(int no) {
		return sqlSession.delete("dSQL.delReport", no);
	}
	public int delPayHistory(int no) {
		return sqlSession.delete("dSQL.delPayHistory", no);
	}
		// 해당 계정의 이메일 확인 삭제
	public int delMail(String email) {
		return sqlSession.delete("dSQL.delMail", email);
	}
// 데이터삭제
	public int delAccount() {
		return sqlSession.delete("dSQL.delAccount");
	}
}
