package com.newbit.www.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.newbit.www.vo.AccountVO;
import com.newbit.www.vo.NoticeVO;

public class AccountDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;
// 닉네임 받기
	public AccountVO noGetNick(int no) {
		return sqlSession.selectOne("aSQL.noGetNick", no);
	}
// 로그인
	// 로그인 처리
	public int getLogin(AccountVO aVO) {
		return sqlSession.selectOne("aSQL.login", aVO);
	}
	
	// 로그인 처리
	public AccountVO getType(String id) {
		return sqlSession.selectOne("aSQL.getType", id);
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
	
// 닉네임 중복 체크
	public int getNickCnt(String nickname) {
		return sqlSession.selectOne("aSQL.nickCnt", nickname);
	}
// 이메일 관련
	// 이메일 중복 체크
	public int getEmailCnt(String email) {
		return sqlSession.selectOne("aSQL.emailCnt", email);
	}
	// 등록 전 확인
	public int registCk(AccountVO aVO) {
		return sqlSession.selectOne("aSQL.registCk", aVO);
	}
	// 이메일 등록
	public int insertMail(AccountVO aVO) {
		return sqlSession.insert("aSQL.insertMail", aVO);
	}
	// 이메일 인증 체크
	public int selCkMail(AccountVO aVO) {
		return sqlSession.selectOne("aSQL.selCkMail", aVO);
	}
	// 체크 이후 이메일 받기
	public String getEmail(AccountVO aVO) {
		return sqlSession.selectOne("aSQL.getEmail", aVO);
	}
	
	// 이메일 가입 허가
	public int editOkMail(AccountVO aVO) {
		return sqlSession.update("aSQL.editOkMail", aVO);
	}
	// 회원가입 실패시 되돌리기
	public int editNoMail(AccountVO aVO) {
		return sqlSession.update("aSQL.editNoMail", aVO);
	}
	
// 회원가입
	public int insertJoin(AccountVO aVO) {
		return sqlSession.insert("aSQL.insertJoin", aVO);
	}

	// 결제용 유저 정보(닉네임, 이메일)
	public AccountVO selAccountInfo(AccountVO aVO) {
		return sqlSession.selectOne("aSQL.selAccountInfo", aVO);
	}
	public AccountVO getNickInfo(String nickname) {
		return sqlSession.selectOne("aSQL.getNickInfo", nickname);
	}
// 알림 내역 불러오기
	public List<NoticeVO> getNotice(String id){
		return sqlSession.selectList("aSQL.getNotice", id);
	}
// 알림 내역 저장
	public int insertNotice(NoticeVO nVO) {
		return sqlSession.insert("aSQL.insertNotice", nVO);
	}
	// 회원가입 실패시 되돌리기
	public int checkNotice(int no) {
		return sqlSession.update("aSQL.checkNotice", no);
	}
}
