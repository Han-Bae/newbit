package com.newbit.www.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.newbit.www.vo.StoreVO;

public class StoreDao {

	@Autowired
	SqlSessionTemplate sqlSession;
	
}
