package com.newbit.www.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 전다빈
 * @since	2022.07.06
 * @version v.1.0
 * 
 * 			작업이력 ] 2022.07.06 - 담당자 전다빈 : 클래스 제작
 *
 */

public class StoreVO {
	private String title, img, appId, released, reviewSummary, discount, price, discountPrice, sessionId;
	/* reviewSummary => positive, mixed, negative */
	
	/* detail 페이지에서 추가로 필요한 변수 */
	private String shortDescription, detailedDescription, fullgameId, fullgameTitle, type, packageTitle, isFree;
	private List<String> developers, publishers, tags;
	private Map<String, String> screenshot, movie;
	private int pickCount, basketCount;
	
	/* library 페이지에 추가로 필요한 변수 */
	private Date dateFormatBuydate;
	private String buydate;
	private int diffDate;

	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getReleased() {
		return released;
	}
	public void setReleased(String released) {
		this.released = released;
	}
	public String getReviewSummary() {
		return reviewSummary;
	}
	public void setReviewSummary(String reviewSummary) {
		this.reviewSummary = reviewSummary;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
	
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getDetailedDescription() {
		return detailedDescription;
	}
	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}
	public String getFullgameId() {
		return fullgameId;
	}
	public void setFullgameId(String fullgameId) {
		this.fullgameId = fullgameId;
	}
	public String getFullgameTitle() {
		return fullgameTitle;
	}
	public void setFullgameTitle(String fullgameTitle) {
		this.fullgameTitle = fullgameTitle;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPackageTitle() {
		return packageTitle;
	}
	public void setPackageTitle(String packageTitle) {
		this.packageTitle = packageTitle;
	}
	public String getIsFree() {
		return isFree;
	}
	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}
	public Date getDateFormatBuydate() {
		return dateFormatBuydate;
	}
	public List<String> getDevelopers() {
		return developers;
	}
	public void setDevelopers(List<String> developers) {
		this.developers = developers;
	}
	public List<String> getPublishers() {
		return publishers;
	}
	public void setPublishers(List<String> publishers) {
		this.publishers = publishers;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public Map<String, String> getScreenshot() {
		return screenshot;
	}
	public void setScreenshot(Map<String, String> screenshot) {
		this.screenshot = screenshot;
	}
	public Map<String, String> getMovie() {
		return movie;
	}
	public void setMovie(Map<String, String> movie) {
		this.movie = movie;
	}
	public int getPickCount() {
		return pickCount;
	}
	public void setPickCount(int pickCount) {
		this.pickCount = pickCount;
	}
	public int getBasketCount() {
		return basketCount;
	}
	public void setBasketCount(int basketCount) {
		this.basketCount = basketCount;
	}
	
	
	
	public void setDateFormatBuydate(Date FormatBuydate) {
		this.dateFormatBuydate = FormatBuydate;
		
		setBuydate();
		
		Date now = new Date();
		int diffDate = (int) (((now.getTime() - FormatBuydate.getTime()) / 1000) / (24 * 60 * 60));
		setDiffDate(diffDate);
		
	}
	public String getBuydate() {
		return buydate;
	}
	public void setBuydate(String buydate) {
		this.buydate = buydate;
	}
	public void setBuydate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
		buydate = format.format(dateFormatBuydate);
	}
	public int getDiffDate() {
		return diffDate;
	}
	public void setDiffDate(int diffDate) {
		this.diffDate = diffDate;
	}
	
	
}
