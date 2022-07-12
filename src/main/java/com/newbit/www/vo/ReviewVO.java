package com.newbit.www.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;


public class ReviewVO {
	
	private int no, account_no, good, bad, reviewno;
	private Integer game_no, dreviewNo;
	private String body, isnewbit, isgood, isshow, rdate, reviewYN, sid;
	private Date resitdate;
	private List<ReviewVO> list;
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getIsnewbit() {
		return isnewbit;
	}
	public void setIsnewbit(String isnewbit) {
		this.isnewbit = isnewbit;
	}
	public String getIsgood() {
		return isgood;
	}
	public void setIsgood(String isgood) {
		this.isgood = isgood;
	}
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
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
	public List<ReviewVO> getList() {
		return list;
	}
	public void setList(List<ReviewVO> list) {
		this.list = list;
	}
	public String getReviewYN() {
		return reviewYN;
	}
	public void setReviewYN(String reviewYN) {
		this.reviewYN = reviewYN;
	}
	
	public Integer getDreviewNo() {
		return dreviewNo;
	}
	public void setDreviewNo(Integer dreviewNo) {
		this.dreviewNo = dreviewNo;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	public int getReviewno() {
		return reviewno;
	}
	public void setReviewno(int reviewno) {
		this.reviewno = reviewno;
	}
}
