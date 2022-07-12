package com.newbit.www.dao;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentDao {

	@Autowired
	SqlSessionTemplate sqlSession;
	
// 선물줄 친구 리스트
	public List<Integer> selFollower(String id) {
		return sqlSession.selectList("pSQL.selFollow", id);
	}
	public List<String> getFriendInfo(List<Integer> list){
		return sqlSession.selectList("pSQL.selInfo", list);
	}
}	