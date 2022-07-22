package com.newbit.www.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpSession;

import com.newbit.www.vo.ReviewVO;
import com.newbit.www.vo.UploadVO;

public class UploadDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	//스크린샷 저장
	public int addSshot(UploadVO uVO) {
		System.out.println("[DAO]파일오나요========================"+uVO.getFile());
		System.out.println("[DAO]getAccount_no========================"+uVO.getAccount_no());
		System.out.println("[DAO]getGame_no========================"+uVO.getGame_no());
		System.out.println("[DAO]저장할 파일명=================="+uVO.getSavename());
		return sqlSession.insert("uSQL.insertSShot", uVO);
	}
	
	
	public List<UploadVO> getScreenShot() {
		return sqlSession.selectList("rSQL.getScreenShot");
	}
	
}
