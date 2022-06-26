package com.newbit.www.dao;

import java.util.List;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.newbit.www.util.*;
import com.newbit.www.vo.*;

/**
 * 	이 클래스는 댓글게시판 관련 데이터베이스 전담 처리함수들로 구성된 클래스
 * @author	전은석
 * @since	2022.06.08
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.06.08	- 담당자 : 전은석
 * 									클래스 제작, 리스트 조회
 *
 */
public class ReBoardDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 게시글 리스트 조회 전담 처리함수
	public List<BoardVO> getList(PageUtil page){
		return sqlSession.selectList("rSQL.getList", page);
	}
	
	// 총 게식들 수 조회 전담 처리함수
	public int getTotal() {
		return sqlSession.selectOne("rSQL.getTotal");
	}
	
	// 장성자 데이터 조회 전담 처리함수
	public BoardVO getWriterInfo(String id) {
		return sqlSession.selectOne("rSQL.getWriterInfo", id);
	}
	
	// 게시글 추가 데이터베이스 작업 전담 처리함수(댓글 포함)
	public int addReBoard(BoardVO bVO) {
		return sqlSession.insert("rSQL.addReBoard", bVO);
	}
	
	// 댓글폼 데이터 조회 전담 처리함수
	public BoardVO getCommentData(BoardVO bVO) {
		return sqlSession.selectOne("rSQL.commentData", bVO);
	}
	
	// 게시글 수정데이터 조회 전담 처리함수
	public BoardVO getEditData(BoardVO bVO) {
		return sqlSession.selectOne("rSQL.editData", bVO);
	}
	
	// 게시글 수정 등록 전담 처리함수
	public int editProc(BoardVO bVO) {
		return sqlSession.update("rSQL.editReBoard", bVO);
	}
	
	// 게시글 삭제 전담 처리함수
	public int delReBoard(BoardVO bVO) {
		return sqlSession.update("rSQL.delReBoard", bVO);
	}
}
