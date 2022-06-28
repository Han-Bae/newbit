package com.newbit.www.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.newbit.www.vo.AccountVO;

public class AccountDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 로그인 처리
	public int getLogin(AccountVO aVO) {
		return sqlSession.selectOne("aSQL.login", aVO);
	}
	
	// 아이디 카운트 조회
	public int getIdCnt(String id) {
		return sqlSession.selectOne("aSQL.idCnt", id);
	}
	/*
	 * // 아바타 리스트 조회 전담 처리함수 public List<AccountVO> getAvtList(){ return
	 * sqlSession.selectList("aSQL.avtList"); } public List<AccountVO>
	 * getAvtList(String id){ return sqlSession.selectList("aSQL.genAvtList", id); }
	 * 
	 * // 회원정보 데이터베이스 추가작업 전담 처리함수 public int addAccount(AccountVO aVO) { return
	 * sqlSession.insert("aSQL.addAccount", aVO); }
	 * 
	 * // 아이디로 회원정보 조회 전담 처리 함수 public AccountVO getIdInfo(String id) { return
	 * sqlSession.selectOne("aSQL.getIdInfo", id); }
	 * 
	 * // 회원번호로 회원정보 조회 전담 처리 함수 public AccountVO getMnoInfo(int mno) { return
	 * sqlSession.selectOne("aSQL.getMnoInfo", mno); }
	 * 
	 * // 회원 리스트조회 전담 처리함수 public List<AccountVO> membList(){ return
	 * sqlSession.selectList("aSQL.AccountList"); }
	 * 
	 * // 회원 탈퇴처리 데이터베이스 작업 전담 처리함수 public int delAccount(String id) { return
	 * sqlSession.update("aSQL.delAccount", id); }
	 * 
	 * // 내 정보 수정 데이터베이스 작업 전담 처리함수 public int editMyInfo(AccountVO aVO) { return
	 * sqlSession.update("aSQL.editInfo", aVO); }
	 */
}
