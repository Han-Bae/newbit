package com.newbit.www.vo;
import java.text.SimpleDateFormat;
// 회원 계정(+즐겨찾기) + 장바구니 + 찜목록 + 아바타 정보 + 친구 수록한 VO
import java.util.Date;

public class AccountVO {
	private int no, game_number, tag_number, friend_number, cnt;
	private String id, pw, nickname, email, istype, isshow, isnewbit, sdate, savename;
	private Date joindate;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getGame_number() {
		return game_number;
	}
	public void setGame_number(int game_number) {
		this.game_number = game_number;
	}
	public int getTag_number() {
		return tag_number;
	}
	public void setTag_number(int tag_number) {
		this.tag_number = tag_number;
	}
	public int getFriend_number() {
		return friend_number;
	}
	public void setFriend_number(int friend_number) {
		this.friend_number = friend_number;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIstype() {
		return istype;
	}
	public void setIstype(String istype) {
		this.istype = istype;
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
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public void setSdate() {
		SimpleDateFormat form = new SimpleDateFormat("YYYY년 MM월 dd일 HH:mm:ss");
		sdate = form.format(joindate);
	}
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}
	public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
		setSdate();
	}
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "AccountVO [no=" + no + ", game_number=" + game_number + ", tag_number=" + tag_number
				+ ", friend_number=" + friend_number + ", id=" + id + ", pw=" + pw + ", nickname=" + nickname
				+ ", email=" + email + ", istype=" + istype + ", isshow=" + isshow + ", isnewbit=" + isnewbit
				+ ", sdate=" + sdate + ", savename=" + savename + ", joindate=" + joindate + "]";
	}
	
}