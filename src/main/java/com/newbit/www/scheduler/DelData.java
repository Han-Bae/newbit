package com.newbit.www.scheduler;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.newbit.www.dao.*;
import com.newbit.www.vo.*;

public class DelData {
	@Autowired
	DelDao dDao;
	
	private static final Logger dLog = LoggerFactory.getLogger("delLog");
	
	public void delDa() {
		// isshow가 n인 유저 찾고 그만큼 반복
		List<AccountVO> delVO = dDao.checkNo();
		for(int i = 0; i < delVO.size(); i++) {
			int no = delVO.get(i).getNo();
				// 장바구니, 찜목록 삭제
				int cnt = dDao.delAccBasket(no);
					if(cnt > 0) dLog.info("계정 번호 ["+no+"] 장바구니 삭제 완료");
				cnt = dDao.delAccPick(no);
					if( cnt > 0) dLog.info("계정 번호 ["+no+"] 찜목록 삭제 완료");
				// 친구목록 삭제
				int followCnt = 0;
				followCnt = dDao.delAccMe(no);
				followCnt += dDao.delAccFriend(no);
					if(followCnt>0) dLog.info("계정 번호 ["+no+"] 친구목록 삭제 완료");
				// 프사 삭제
				cnt = dDao.delAccProfile(no);
					if(cnt>0) dLog.info("계정 번호 ["+no+"] 프로필사진 삭제 완료");
				// 라이브러리 삭제
				cnt = dDao.delAccLibrary(no);
					if(cnt>0) dLog.info("계정 번호 ["+no+"] 라이브러리 삭제 완료");
			// 리뷰 번호 받기 + 리뷰 수만큼 반복
			List<Integer> review_no = dDao.getReview(no);
			int review_cnt = 0;
			for(int j = 0; j < review_no.size(); j++) {
				// 해당 계정이 쓴 리뷰 디테일 데이터 제거
				review_cnt = dDao.delReviewGB(review_no.get(j));
			}
				review_cnt += dDao.delAccReviewGB(no);
					if(review_cnt>0) dLog.info("계정 번호 ["+no+"] 평가 세부 데이터 삭제 완료");
				cnt = dDao.delAccReview(no);
					if(cnt>0) dLog.info("계정 번호 ["+no+"] 평가 삭제 완료");
			// 스크린샷 번호 받고 + 스샷 수만큼 반복
			List<Integer> screen_no = dDao.getScreen(no);
				// 해당 계정이 쓴 댓글 먼저 제거
			cnt = dDao.delAccSSReply(no);
			if(cnt>0) dLog.info("계정 번호 ["+no+"] 님이 작성한 스크린샷 댓글 삭제 완료");
			int SSCnt = 0;
			for(int j = 0; j < screen_no.size(); j++) {
				// 해당 계정이 쓴 스크린샷 세부데이터 먼저 제거
				SSCnt = dDao.delSSImg(screen_no.get(i));
				SSCnt = dDao.delSSReply(screen_no.get(i));
				SSCnt += dDao.delSSGb(screen_no.get(i));
			}
				if(SSCnt > 0) dLog.info("계정 번호 ["+no+"] 스크린샷 세부 데이터 삭제 완료");
			cnt = dDao.delAccSS(no);
					if(cnt>0) dLog.info("계정 번호 ["+no+"] 스크린샷 삭제 완료");
			// 신고내역 삭제
			int historyCnt = 0;
			historyCnt = dDao.delNotice(no);
			historyCnt += dDao.delReport(no);
			historyCnt += dDao.delPayHistory(no);
				if(historyCnt>0) dLog.info("계정 번호 ["+no+"] 신고 내역 삭제 완료");
			// 해당 계정으로 인증된 메일 삭제
			int result = dDao.delAccount();
				if(result == 1) {
					cnt = dDao.delMail(delVO.get(i).getEmail());
					if(cnt>0) dLog.info("계정 번호 ["+no+"] 인증 메일 삭제 완료");
					dLog.info("계정 번호 ["+no+"] 탈퇴 완료");
				}
		}
	}
}
