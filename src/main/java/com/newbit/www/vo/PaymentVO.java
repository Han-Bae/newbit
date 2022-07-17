package com.newbit.www.vo;
import java.util.*;

// 회원 계정(+즐겨찾기) + 장바구니 + 찜목록 + 아바타 정보 + 친구 수록한 VO

public class PaymentVO {
	private String presentTitle, presentMsg, paySel, name, id,
		imp_uid, merchant_uid, game_id, isrefund, result;
	private int no, amount, account_no, buy_no, game_price;
	private List<String> nameList, gameIdList;
	private List<Integer> noList, gamePriceList;
	
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getIsrefund() {
		return isrefund;
	}
	public void setIsrefund(String isrefund) {
		this.isrefund = isrefund;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getGame_price() {
		return game_price;
	}
	public void setGame_price(int game_price) {
		this.game_price = game_price;
	}
	public List<Integer> getGamePriceList() {
		return gamePriceList;
	}
	public void setGamePriceList(List<Integer> gamePriceList) {
		this.gamePriceList = gamePriceList;
	}
	public List<String> getGameIdList() {
		return gameIdList;
	}
	public void setGameIdList(List<String> gameIdList) {
		this.gameIdList = gameIdList;
	}
	public int getBuy_no() {
		return buy_no;
	}
	public void setBuy_no(int buy_no) {
		this.buy_no = buy_no;
	}
	public List<Integer> getNoList() {
		return noList;
	}
	public void setNoList(List<Integer> noList) {
		this.noList = noList;
	}
	public String getGame_id() {
		return game_id;
	}
	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}
	public int getAccount_no() {
		return account_no;
	}
	public void setAccount_no(int account_no) {
		this.account_no = account_no;
	}
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
	
	public String getImp_uid() {
		return imp_uid;
	}
	public void setImp_uid(String imp_uid) {
		this.imp_uid = imp_uid;
	}
	public String getMerchant_uid() {
		return merchant_uid;
	}
	public void setMerchant_uid(String merchant_uid) {
		this.merchant_uid = merchant_uid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "PaymentVO [presentTitle=" + presentTitle + ", presentMsg=" + presentMsg + ", paySel=" + paySel
				+ ", name=" + name + ", id=" + id + ", imp_uid=" + imp_uid + ", merchant_uid=" + merchant_uid
				+ ", game_id=" + game_id + ", isrefund=" + isrefund + ", result=" + result + ", no=" + no + ", amount="
				+ amount + ", account_no=" + account_no + ", buy_no=" + buy_no + ", game_price=" + game_price
				+ ", nameList=" + nameList + ", gameIdList=" + gameIdList + ", noList=" + noList + ", gamePriceList="
				+ gamePriceList + "]";
	}
	
	
}
