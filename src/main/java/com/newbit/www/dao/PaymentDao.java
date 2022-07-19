package com.newbit.www.dao;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.newbit.www.vo.PaymentVO;

public class PaymentDao {

	@Autowired
	SqlSessionTemplate sqlSession;
// 찜목록 불러오기
	public List<String> getPickList(String id) {
		return sqlSession.selectList("pSQL.getPickList", id);
	}
	
// 선물 줄 친구 리스트
	public List<Integer> selFollower(String id) {
		return sqlSession.selectList("pSQL.selFollow", id);
	}
	public List<String> getFriendInfo(List<Integer> list){
		return sqlSession.selectList("pSQL.selInfo", list);
	}
	

// 결제
	// 결제자 no 찾기
	public int getNo(String id){
		return sqlSession.selectOne("pSQL.getNo", id);
	}
	// 선물해줄 친구 no리스트 찾기
	public List<Integer> getNoList(List<String> list){
		return sqlSession.selectList("pSQL.getNoList", list);
	}
	
	// 결제 내역 저장	- 나중에 중복 게임 제거 필요 + 라이브러리 등록
	public int savePay(PaymentVO pVO) {
		return sqlSession.insert("pSQL.savePay", pVO);
	}
	
	// 환불 체크용 데이터 뽑기
	public PaymentVO refundGame(PaymentVO pVO) {
		return sqlSession.selectOne("pSQL.refundGame", pVO);
	}
	
	// 환불 완료
	public int refundSuccess(PaymentVO pVO) {
		return sqlSession.update("pSQL.refundSuccess", pVO);
	}
}	