package com.newbit.www.scheduler;

import org.quartz.*;
import org.springframework.scheduling.quartz.*;

public class DelDataEach extends QuartzJobBean{
	private DelData delDa;
	

	@Override
	protected void executeInternal(JobExecutionContext context) 
												throws JobExecutionException {
		delDa.delDa();
	}
	
	public void setUpSal(DelData delDa) {
		this.delDa = delDa;
	}
}
