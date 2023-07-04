package com.newbit.www.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;



public class UploadVO {
	
	private String result, dir, oriname, savename, isshow, isnewbit, rdate;
	private List<UploadVO> list;
	private MultipartFile[] file ;
	private long len;
	private Integer game_no;
	private int account_no, no;
	private Date resitdate;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getOriname() {
		return oriname;
	}
	public void setOriname(String oriname) {
		this.oriname = oriname;
	}
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}
	public long getLen() {
		return len;
	}
	public void setLen(long len) {
		this.len = len;
	}
	public List<UploadVO> getList() {
		return list;
	}
	public void setList(List<UploadVO> list) {
		this.list = list;
	}
	public MultipartFile[] getFile() {
		return file;
	}
	public void setFile(MultipartFile[] file) {
		this.file = file;
	}
	
	public int getAccount_no() {
		return account_no;
	}
	public void setAccount_no(int account_no) {
		this.account_no = account_no;
	}
	public Integer getGame_no() {
		return game_no;
	}
	public void setGame_no(Integer game_no) {
		this.game_no = game_no;
	}
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	public String getIsnewbit() {
		return isnewbit;
	}
	public void setIsnewbit(String isnewbit) {
		this.isnewbit = isnewbit;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Date getResitdate() {
		return resitdate;
	}
	public void setResitdate(Date resitdate) {
		this.resitdate = resitdate;
		setRdate();
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy/MM/dd");
		rdate = form.format(resitdate);
	}
}
