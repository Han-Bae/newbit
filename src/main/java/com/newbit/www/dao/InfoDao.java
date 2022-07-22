package com.newbit.www.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.newbit.www.vo.*;

public class InfoDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public InfoVO myInfoData(String id) {
		return sqlSession.selectOne("inSQL.myInfo", id);
	}

}
