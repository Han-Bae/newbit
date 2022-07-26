package com.newbit.www.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.newbit.www.vo.StoreVO;

public class StoreDao {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	public int getPickCount(StoreVO storeVO) {
		return sqlSession.selectOne("storeSQL.getPickCount", storeVO);
	}
	
	public int getBasketCount(StoreVO storeVO) {
		return sqlSession.selectOne("storeSQL.getBasketCount", storeVO);
	}
	
	public String getAccountTag(String id) {
		return sqlSession.selectOne("storeSQL.getAccountTag", id);
	}
}
