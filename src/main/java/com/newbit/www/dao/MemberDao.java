package com.newbit.www.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.newbit.www.vo.MemberVO;

public class MemberDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 로그인 처리
	public int getLogin(MemberVO mVO) {
		return sqlSession.selectOne("mSQL.login", mVO);
	}
	
	// 아이디 카운트 조회
	public int getIdCnt(String id) {
		return sqlSession.selectOne("mSQL.idCnt", id);
	}
	
	// 아바타 리스트 조회 전담 처리함수
	public List<MemberVO> getAvtList(){
		return sqlSession.selectList("mSQL.avtList");
	}
	public List<MemberVO> getAvtList(String id){
		return sqlSession.selectList("mSQL.genAvtList", id);
	}
	
	// 회원정보 데이터베이스 추가작업 전담 처리함수
	public int addMember(MemberVO mVO) {
		return sqlSession.insert("mSQL.addMember", mVO);
	}
	
	// 아이디로 회원정보 조회 전담 처리 함수
	public MemberVO getIdInfo(String id) {
		return sqlSession.selectOne("mSQL.getIdInfo", id);
	}
	
	// 회원번호로 회원정보 조회 전담 처리 함수
	public MemberVO getMnoInfo(int mno) {
		return sqlSession.selectOne("mSQL.getMnoInfo", mno);
	}
	
	// 회원 리스트조회 전담 처리함수
	public List<MemberVO> membList(){
		return sqlSession.selectList("mSQL.memberList");
	}
	
	// 회원 탈퇴처리 데이터베이스 작업 전담 처리함수
	public int delMember(String id) {
		return sqlSession.update("mSQL.delMember", id);
	}
	
	// 내 정보 수정 데이터베이스 작업 전담 처리함수
	public int editMyInfo(MemberVO mVO) {
		return sqlSession.update("mSQL.editInfo", mVO);
	}
}
