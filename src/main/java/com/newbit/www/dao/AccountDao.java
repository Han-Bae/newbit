package com.newbit.www.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.newbit.www.vo.AccountVO;

public class AccountDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;
// 로그인
	// 로그인 처리
	public int getLogin(AccountVO aVO) {
		return sqlSession.selectOne("aSQL.login", aVO);
	}
	
	// 아이디 카운트 조회
	public int getIdCnt(String id) {
		return sqlSession.selectOne("aSQL.idCnt", id);
	}
	
// 아이디 찾아주기
	// 아이디 찾기 유저 체크
	public int getFidCnt(AccountVO aVO) {
		return sqlSession.selectOne("aSQL.selFidCnt", aVO);
	}
	
	// 아이디 찾아주기
	public String getFidID(AccountVO aVO) {
		return sqlSession.selectOne("aSQL.selFidID", aVO);
	}
	
// 비밀번호 찾기(재설정)
	// 비밀번호 찾기 유저 체크
	public int getFpwCnt(AccountVO aVO) {
		return sqlSession.selectOne("aSQL.selFpwCnt", aVO);
	}
	// 비밀번호 재설정
	public int editPW(AccountVO aVO) {
		return sqlSession.update("aSQL.editPW", aVO);
	}
}
