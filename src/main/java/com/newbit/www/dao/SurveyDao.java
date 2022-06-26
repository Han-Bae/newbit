package com.newbit.www.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.newbit.www.vo.*;

public class SurveyDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 참여하지 않은 설문 갯수 조회 전담 처리함수
	public int getCount(SurveyVO sVO) {
		return sqlSession.selectOne("sSQL.remainSurvey", sVO);
	}
	
	// 진행중인 설문 리스트 조회 전담 처리함수
	public List<SurveyVO> getIngList(String id){
		return sqlSession.selectList("sSQL.ingList", id);
	}
	
	// 종료된 설문 리스트 조회 전담 처리함수
	public List<SurveyVO> getOldList(){
		return sqlSession.selectList("sSQL.oldList");
	}
	
	// 설문주제번호로 문항리스트 조회 전담 처리함수
	public List<SurveyVO> getQuestList(int sino){
		return sqlSession.selectList("sSQL.questList", sino);
	}
	
	// 설문 문항번호로 설문보기리스트 조회 전담 처리함수
	public List<SurveyVO> getBogiList(int upno){
		return sqlSession.selectList("sSQL.bogiList", upno);
	}
	
	// 계층형 질의로 설문문항보기리스트 조회 전담 처리함수
	public List<SurveyVO> getQList(int sino){
		return sqlSession.selectList("sSQL.qList", sino);
	}
	
	// 설문응답 입력 전담 처리함수
	public int addSurvey(SurveyVO sVO) {
		return sqlSession.insert("sSQL.addSurvey", sVO);
	}
	
	// 설문주제번호로 설문결과조회 전담 처리함수
	public List<SurveyVO> getResultList(int sino){
		return sqlSession.selectList("sSQL.resultList", sino);
	}
	
	// 설문문항의 보기 결과 조회 전담 처리함수
	public List<SurveyVO> getBogiResult(int sqno){
		return sqlSession.selectList("sSQL.bogiResult", sqno);
	}
}
