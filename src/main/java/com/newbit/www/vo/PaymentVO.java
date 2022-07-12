package com.newbit.www.vo;
import java.util.*;

// 회원 계정(+즐겨찾기) + 장바구니 + 찜목록 + 아바타 정보 + 친구 수록한 VO

public class PaymentVO {
	private String presentTitle, presentMsg, paySel;
	private List<String> nameList;
	public String getPresentTitle() {
		return presentTitle;
	}
	public void setPresentTitle(String presentTitle) {
		this.presentTitle = presentTitle;
	}
	public String getPresentMsg() {
		return presentMsg;
	}
	public void setPresentMsg(String presentMsg) {
		this.presentMsg = presentMsg;
	}
	public List<String> getNameList() {
		return nameList;
	}
	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}
	
	public String getPaySel() {
		return paySel;
	}
	public void setPaySel(String paySel) {
		this.paySel = paySel;
	}
	@Override
	public String toString() {
		return "PaymentVO [presentTitle=" + presentTitle + ", presentMsg=" + presentMsg + ", paySel=" + paySel
				+ ", nameList=" + nameList + "]";
	}
	
	
}
