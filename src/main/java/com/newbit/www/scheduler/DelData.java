package com.newbit.www.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.newbit.www.dao.*;

public class DelData {
	@Autowired
	DelDao dDao;
	
	private static final Logger dLog = LoggerFactory.getLogger("delLogger");
	
	public void delDa() {
//		int result = dDao.delData();
		
//		if(result == 1) {
//			dLog.info("데이터삭제");
//		}
	}
}
