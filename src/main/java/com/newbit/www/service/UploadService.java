package com.newbit.www.service;

import java.io.File;
import java.util.*;

import org.springframework.beans.factory.annotation.* ;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.newbit.www.dao.UploadDao;
import com.newbit.www.util.FileUtil;
import com.newbit.www.vo.ReviewVO;
import com.newbit.www.vo.UploadVO;

public class UploadService {
	
	@Autowired
	UploadDao uDao;
	

	// 데이터베이스 입력작업 전담 처리함수
	@Transactional
	public void addUpliadFile(UploadVO uVO) {
		System.out.println("file 어디니=================="+uVO.getFile());	
		
		
		  if(uVO.getFile() != null) { // 파일정보테이블에 파일정보들 입력하고(반복) 
			  ArrayList<UploadVO> list = uploadProc(uVO.getFile());
			  
			  
				/*
				 * for (int i = 0; i < list.size(); i++) {
				 * System.out.println("[sv]저장할 파일명=================="+list.get(i).getSavename())
				 * ;
				 * System.out.println("[sv]원래파일명========================"+list.get(i).getOriname
				 * ());
				 * System.out.println("[sv]getAccount_no========================"+list.get(i).
				 * getAccount_no());
				 * System.out.println("[sv]getGame_no========================"+list.get(i).
				 * getGame_no()); u.setSavename(uVO.getSavename());
				 * u.setAccount_no(uVO.getAccount_no()); u.setGame_no(uVO.getGame_no()); }
				 */
			  
			  // 데이터 입력작업을 파일 갯수만큼 반복해준다.\ 
				  for(UploadVO u : list) {
					System.out.println("[sv]저장할 파일명=================="+list.get(0).getSavename());
					System.out.println("[sv]원래파일명========================"+list.get(0).getOriname());
					System.out.println("[sv]getAccount_no========================"+uVO.getAccount_no());
					System.out.println("[sv]getGame_no========================"+uVO.getGame_no());
						 
					  u.setSavename(list.get(0).getSavename()); 
					  u.setAccount_no(uVO.getAccount_no());
					  u.setGame_no(uVO.getGame_no()); 
				  }
				  
				  for(UploadVO u : list) { 
					  uDao.addSshot(u); 
				}
				 
		  }
		  
		  
		 
		
			/*
			 * if(uVO.getFile() != null) {
			 * System.out.println("file=================="+uVO.getFile());
			 * System.out.println("저장할 파일명=================="+uVO.getSavename());
			 * 
			 * uDao.addSshot(uVO); }
			 */
	}
	
	// 다중 파일 업로드 기능 처리함수
	public ArrayList<UploadVO> uploadProc(MultipartFile[] file){
		ArrayList<UploadVO> list = new ArrayList<UploadVO>();
		
		for(MultipartFile f : file) {
			list.add(uploadProc(f));
		}
		
		return list;
	}
	
	// 단일 파일 업로드 처리함수
	public UploadVO uploadProc(MultipartFile file) {
		// 반환값 변수
		UploadVO uVO = uploadProc(file, "/upload");
		
		return uVO;
	}
	
	
	public UploadVO uploadProc(MultipartFile file, String dir) {
		/*
			이 함수가 파일을 업로드 하기 위해서는 
			컨트롤러에서 업로드할 파일의 정보를 받아와야 한다.
			
			여러개의 파일중 이 함수에서는 한개의 파일만 처리하도록 하자.
		 */
		System.out.println("dir============================="+dir);
		
		// 반환값 변수
		UploadVO uVO = new UploadVO();
		
		// 저장 경로를 기억할 변수
		String path = this.getClass().getResource("").getPath();
		path = path.substring(0, path.indexOf("/WEB-INF")) + "/resources" + dir;
		
		//uVO.setDir("/www" + dir + "/");
		
		System.out.println("저장경로============================="+path);
		
		// 파일 크기
		long len = file.getSize();
		uVO.setLen(len);
		
		// 파일의 원 이름을 꺼내오고
		String oldName = file.getOriginalFilename();
		if(oldName == null) {
			return uVO;
		}
		System.out.println("파일 원래이름============================="+oldName);
		
		uVO.setOriname(oldName);
		
		// 저장이름 만들고
		String rename = FileUtil.rename(path, oldName);
		System.out.println("파일 저장이름============================="+rename);
		
		uVO.setSavename(rename);
		System.out.println("파일 저장이름[get]============================="+uVO.getSavename());
		
		// 파일 업로드하고
		try {
			File f = new File(path, rename);
			
			file.transferTo(f); //  파일 내용 기록...
			System.out.println("파일 저장하러옴=============================");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return uVO;
	}
	
}
