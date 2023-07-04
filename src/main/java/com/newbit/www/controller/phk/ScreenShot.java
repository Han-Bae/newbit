package com.newbit.www.controller.phk;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.RedirectView;

import com.newbit.www.dao.ReviewDao;
import com.newbit.www.service.UploadService;
import com.newbit.www.vo.ReviewVO;
import com.newbit.www.vo.UploadVO;

@Controller
@RequestMapping("/sshot")
public class ScreenShot {

	@Autowired
	ReviewDao rDao;
	@Autowired
	UploadService uSrvc;

	/*----------------------------------------------------------------------*/

	//스크린샷 업로드
	  @RequestMapping("/uploadScreenShot.nbs") public ModelAndView
	  boardWriteProc(ModelAndView mv, UploadVO uVO, ReviewVO rVO, RedirectView rv, HttpSession session) {
		System.out.println("스샷 저장용 file------------------------"+uVO.getFile());
		System.out.println("[스샷 저장용]##### getAccount_no ####" + rVO.getAccount_no());
		uVO.setGame_no(1111111);
		uVO.setAccount_no(rVO.getAccount_no());
		  
	  try { 
	System.out.println("--------------------------------등록하러가쟈");
	  uSrvc.addUpliadFile(uVO); // 등록작업에 성공한 경우 
	  uVO.setResult("OK");
	  rv.setUrl("/www/review/reviewMain.nbs"); 
	  } catch(Exception e) { // 등록에 실패한 경우
	  uVO.setResult("NO"); 
	  rv.setUrl("/www/review/reviewMain.nbs");
	  e.printStackTrace(); }
	  
	  mv.setView(rv);
	  
	  return mv; }
	 
	
	
	

}
