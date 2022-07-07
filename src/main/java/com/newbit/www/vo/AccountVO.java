package com.newbit.www.vo;
import java.text.SimpleDateFormat;
import java.util.*;

// 회원 계정(+즐겨찾기) + 장바구니 + 찜목록 + 아바타 정보 + 친구 수록한 VO

public class AccountVO {
	private int no, game_number, tag_number, friend_number, cnt, ck_mail;
	private String id, pw, nickname, email, istype, isshow, isnewbit,
		sdate, savename, isokay, result, presentMsg,
		icon, title, msg, stat, url;	// SwalAlert용
	private char[] convert_id;
	private Date joindate;
	private ArrayList nameList;
	
	
	public ArrayList getNameList() {
		return nameList;
	}
	public void setNameList(ArrayList nameList) {
		this.nameList = nameList;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getIsokay() {
		return isokay;
	}
	public void setIsokay(String isokay) {
		this.isokay = isokay;
	}
	public char[] getConvert_id() {
		return convert_id;
	}
	public void setConvert_id(char[] convert_id) {
		this.convert_id = convert_id;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
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
	
	public int getCk_mail() {
		return ck_mail;
	}
	public void setCk_mail(int ck_mail) {
		this.ck_mail = ck_mail;
	}
	
	public String getPresentMsg() {
		return presentMsg;
	}
	public void setPresentMsg(String presentMsg) {
		this.presentMsg = presentMsg;
	}
	
	@Override
	public String toString() {
		return "AccountVO [no=" + no + ", game_number=" + game_number + ", tag_number=" + tag_number
				+ ", friend_number=" + friend_number + ", cnt=" + cnt + ", ck_mail=" + ck_mail + ", id=" + id + ", pw="
				+ pw + ", nickname=" + nickname + ", email=" + email + ", istype=" + istype + ", isshow=" + isshow
				+ ", isnewbit=" + isnewbit + ", sdate=" + sdate + ", savename=" + savename + ", isokay=" + isokay
				+ ", result=" + result + ", presentMsg=" + presentMsg + ", icon=" + icon + ", title=" + title + ", msg="
				+ msg + ", stat=" + stat + ", url=" + url + ", convert_id=" + Arrays.toString(convert_id)
				+ ", joindate=" + joindate + ", nameList=" + nameList + "]";
	}
	
}
