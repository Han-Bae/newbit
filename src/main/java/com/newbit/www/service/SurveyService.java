package com.newbit.www.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import com.newbit.www.dao.*;
import com.newbit.www.vo.*;

/**
 * 이 클래스는 설문조사에 관련된 부가적인 기능을 처리할 클래스
 * 
 * @author 전은석
 * @since 2022.06.13
 * @version v.1.0
 * 
 *          작업이력 ] 2022.06.13 - 담당자 : 전은석 클래스제작
 *
 */

public class SurveyService {
	/*
	 * @Autowired SurveyDao sDao;
	 * 
	 * // 설문주제 번호로 설문데이터 작업 셋팅 함수 public void setBogi(SurveyVO sVO) { int sino =
	 * sVO.getSino(); List<SurveyVO> list = sDao.getQuestList(sino); // 문항 리스트 꺼내기
	 * 작업
	 * 
	 * sVO.setBogi(list);
	 * 
	 * // 문항에 해당하는 보기 셋팅 작업 for(SurveyVO vo : list) { int qno = vo.getSqno();
	 * List<SurveyVO> bogi = sDao.getBogiList(qno);
	 * 
	 * vo.setBogi(bogi); } }
	 * 
	 * // 문항과 보기를 동시에 조회해서 분리 처리하는 전담 함수 public void settingList(SurveyVO sVO) { int
	 * sino = sVO.getSino();
	 * 
	 * List<SurveyVO> qlist = sDao.getQList(sino);
	 * 
	 * ArrayList<SurveyVO> munjae = new ArrayList<SurveyVO>();
	 * 
	 * // 문항만 꺼내서 리스트 채우기 for(SurveyVO vo : qlist) { if(vo.getSqno() ==
	 * vo.getUpno()) { munjae.add(vo); } }
	 * 
	 * // 보기만꺼내서 위의 문항리스트의 vo에 채우기 for(SurveyVO vo : munjae) { ArrayList<SurveyVO>
	 * tmp = new ArrayList<SurveyVO>(); for(SurveyVO bogi : qlist) { if(vo.getSqno()
	 * == bogi.getUpno() && vo.getSqno() != bogi.getSqno()) { tmp.add(bogi); } }
	 * vo.setBogi(tmp); }
	 * 
	 * sVO.setBogi(munjae);
	 * 
	 * }
	 * 
	 * // 전체 응답 입력 처리 서비스 함수
	 * 
	 * @Transactional public boolean addAllDap(SurveyVO sVO) { // 응답 번호를 기억하는 배열을
	 * 꺼낸다. int[] dapArr = sVO.getDap();
	 * 
	 * 
	 * // 작동 확인용 테스트 카운트변수 // int cnt = 0;
	 * 
	 * for(int qno : dapArr) {
	 * 
	 * // 트랜젝션 확인 테스트용 코드 if(cnt++ == 2) { qno = 1111111; }
	 * 
	 * sVO.setSqno(qno); sDao.addSurvey(sVO); }
	 * 
	 * return true; }
	 * 
	 * // 트랜잭션 적용 처리작업 호출 함수 public boolean applyTx(SurveyVO sVO) { boolean bool =
	 * false; try { bool = addAllDap(sVO); } catch(Exception e) { bool = false; }
	 * 
	 * return bool; }
	 * 
	 * // 설문 결과 데이터 가져오기 서비스 함수 public void resultService(SurveyVO sVO) { //
	 * 데이터베이스에서 데이터 가져오고 List<SurveyVO> list = sDao.getResultList(sVO.getSino()); //
	 * 위의 리스트는 문항과 보기의 정보가 혼합되어서 만들어진 리스트이다. // 따라서 문항과 보기를 분리해야 한다.
	 * 
	 * // 문항정보들을 기억할 리스트 List<SurveyVO> munhang = new ArrayList<SurveyVO>();
	 * for(SurveyVO vo : list) { if(vo.getSqno() == vo.getUpno()) { // 이 경우는 문항에
	 * 해당하므로 ... munhang.add(vo); } }
	 * 
	 * // 문항의 보기리스트 채우기 for(SurveyVO vo : munhang) { List<SurveyVO> bogi = new
	 * ArrayList<SurveyVO>(); for(SurveyVO data : list) { if(vo.getSqno() ==
	 * data.getUpno() && data.getSqno() != data.getUpno()) { // data 는 문항에 해당하는 보기
	 * 정보이므로 추가해준다. bogi.add(data); } }
	 * 
	 * // 문항에 해당하는 보기 리스트가 완성 됬으므로 VO에 추가해준다. vo.setBogi(bogi); }
	 * 
	 * // 문항들 정보 채워주고 sVO.setBogi(munhang); } public void getResult(SurveyVO sVO) {
	 * // 데이터베이스에서 데이터 가져오고 List<SurveyVO> list = sDao.getResultList(sVO.getSino());
	 * // 위의 리스트는 문항과 보기의 정보가 혼합되어서 만들어진 리스트이다. // 따라서 문항과 보기를 분리해야 한다.
	 * 
	 * // 문항정보들을 기억할 리스트 sVO.setBogi(new ArrayList<SurveyVO>()); for(SurveyVO vo :
	 * list) { if(vo.getSqno() == vo.getUpno()) { // 이 경우는 문항에 해당하므로 ...
	 * vo.setBogi(new ArrayList<SurveyVO>()); sVO.getBogi().add(vo); }
	 * 
	 * if(vo.getSqno() != vo.getUpno()) { // 문항의 보기리스트 채우기
	 * sVO.getBogi().get(sVO.getBogi().size() - 1).getBogi().add(vo); } } }
	 * 
	 * // 질의명령을 나눠서 설문 결과 가져오기 public void setMunhangList(SurveyVO sVO) { // sVO에
	 * 문항리스트 셋팅 sVO.setBogi(sDao.getQuestList(sVO.getSino()));
	 * 
	 * // 각 문항정보에 보기리스트 셋팅 for(SurveyVO bogi : sVO.getBogi()) {
	 * bogi.setBogi(sDao.getBogiResult(bogi.getSqno())); } }
	 */
}
