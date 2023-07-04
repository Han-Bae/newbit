package com.newbit.www.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.newbit.www.vo.PaymentVO;
import com.newbit.www.vo.StoreVO;

/**
 * 
 * 	@author 전다빈
 * 	@version v.1.0
 * 
 * 			담당자 : 전다빈
 * 			
 */

public class ProfileDao {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	public List<StoreVO> getLibrary(String id) {
		return sqlSession.selectList("profileSQL.getLibrary", id);
	}
	
	public int countLibraryGame(StoreVO storeVO) {
		return sqlSession.selectOne("profileSQL.countLibraryGame", storeVO);
	}
	
	public List<String> getPayHistoryList(String id) {
		return sqlSession.selectList("profileSQL.getPayHistoryList", id);
	}
	
	public int addLibraryGame(StoreVO storeVO) {
		return sqlSession.insert("profileSQL.addLibraryGame", storeVO);
	}
	
	public int delLibrary(PaymentVO pVO) {
		return sqlSession.update("profileSQL.delLibrary", pVO);
	}
}
