package com.newbit.www.vo;
import java.text.SimpleDateFormat;
import java.util.*;

// 회원 계정(+즐겨찾기) + 장바구니 + 찜목록 + 아바타 정보 + 친구 수록한 VO

public class AccountVO {
	private int no, tag_number, friend_number, cnt, ck_mail, tag;
	private String game_id, id, pw, nickname, email, istype, isshow, isnewbit, tel,
		sdate, savename, isokay, result,
		icon, title, msg, stat, url;	// SwalAlert용
	private char[] convert_id;
	private Date joindate;
	private PaymentVO pVO;
	private List<AccountVO> numList;
	private List<NoticeVO> nVOList;
	
	
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public List<NoticeVO> getnVOList() {
		return nVOList;
	}
	public void setnVOList(List<NoticeVO> nVOList) {
		this.nVOList = nVOList;
	}
	public PaymentVO getpVO() {
		return pVO;
	}
	public void setpVO(PaymentVO pVO) {
		this.pVO = pVO;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public List<AccountVO> getNumList() {
		return numList;
	}
	public void setNumList(List<AccountVO> numList) {
		this.numList = numList;
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
	public String getGame_id() {
		return game_id;
	}
	public void setGame_id(String game_id) {
		this.game_id = game_id;
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
	
	@Override
	public String toString() {
		return "AccountVO [no=" + no + ", tag_number=" + tag_number + ", friend_number=" + friend_number + ", cnt="
				+ cnt + ", ck_mail=" + ck_mail + ", tag=" + tag + ", game_id=" + game_id + ", id=" + id + ", pw=" + pw
				+ ", nickname=" + nickname + ", email=" + email + ", istype=" + istype + ", isshow=" + isshow
				+ ", isnewbit=" + isnewbit + ", tel=" + tel + ", sdate=" + sdate + ", savename=" + savename
				+ ", isokay=" + isokay + ", result=" + result + ", icon=" + icon + ", title=" + title + ", msg=" + msg
				+ ", stat=" + stat + ", url=" + url + ", convert_id=" + Arrays.toString(convert_id) + ", joindate="
				+ joindate + ", pVO=" + pVO + ", numList=" + numList + ", nVOList=" + nVOList + "]";
	}
	
}
