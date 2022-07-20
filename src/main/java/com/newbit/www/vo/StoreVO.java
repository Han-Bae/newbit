package com.newbit.www.vo;

import java.util.List;

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
	private String title, img, appId, released, reviewSummary, discount, price, discountPrice;
	/* reviewSummary => positive, mixed, negative */
	
	/* detail 페이지에서 추가로 필요한 변수 */
	private String shortDescription, detailedDescription, fullgameId, type, packageTitle;
	private List<String> developers, publishers, screenshotThumb, screenshotFull, movieThumb, movieFull;

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
	public List<String> getScreenshotThumb() {
		return screenshotThumb;
	}
	public void setScreenshotThumb(List<String> screenshotThumb) {
		this.screenshotThumb = screenshotThumb;
	}
	public List<String> getScreenshotFull() {
		return screenshotFull;
	}
	public void setScreenshotFull(List<String> screenshotFull) {
		this.screenshotFull = screenshotFull;
	}
	public List<String> getMovieThumb() {
		return movieThumb;
	}
	public void setMovieThumb(List<String> movieThumb) {
		this.movieThumb = movieThumb;
	}
	public List<String> getMovieFull() {
		return movieFull;
	}
	public void setMovieFull(List<String> movieFull) {
		this.movieFull = movieFull;
	}
	
	
	@Override
	public String toString() {
		return "StoreVO [title=" + title + ", img=" + img + ", appId=" + appId + ", released=" + released
				+ ", reviewSummary=" + reviewSummary + ", discount=" + discount + ", price=" + price
				+ ", discountPrice=" + discountPrice + ", shortDescription=" + shortDescription
				+ ", detailedDescription=" + detailedDescription + ", fullgameId=" + fullgameId + ", type=" + type
				+ ", packageTitle=" + packageTitle + ", developers=" + developers + ", publishers=" + publishers
				+ ", screenshotThumb=" + screenshotThumb + ", screenshotFull=" + screenshotFull + ", movieThumb="
				+ movieThumb + ", movieFull=" + movieFull + "]";
	}
	
}
