package com.newbit.www.api;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import com.newbit.www.vo.StoreVO;

/**
 * 
 * @author 전다빈
 * @since	2022.07.06
 * @version v.1.0
 * 
 * 			작업이력 ] 2022.07.06 - 담당자 전다빈 : Jsoup을 이용한 크롤링 api 제작
 *
 */
public class StoreJsoup {
	
	
	// 스토어 최고 인기 페이지 url 컨트롤러
	// sort == null | sort != null
	public List<StoreVO> crawlingStoreTopSeller(String sort) {
		String url = "https://store.steampowered.com/search/?filter=topsellers";
		if(sort != null) {
			url = "https://store.steampowered.com/search/?sort_by=" + sort + "&filter=topsellers";
		}
		return topSellAppListCrawling(url);
	}
	// (sort == null | sort != null) & (maxPrice != null | Specials != null)
	public List<StoreVO> crawlingStoreTopSeller(String sort, String maxPriceOrSpecials) {
		String url = "";
		if(!maxPriceOrSpecials.equals("1")) {
			if(sort != null) {
				url = "https://store.steampowered.com/search/?maxprice=" + maxPriceOrSpecials + "&filter=topsellers";
			} else {
				url = "https://store.steampowered.com/search/?sort_by=" + sort + "&maxprice=" + maxPriceOrSpecials + "&filter=topsellers";
			}
		} else {
			if(sort != null) {
				url = "https://store.steampowered.com/search/?specials=" + maxPriceOrSpecials + "&filter=topsellers";
			} else {
				url = "https://store.steampowered.com/search/?sort_by=" + sort + "&specials=" + maxPriceOrSpecials + "&filter=topsellers";
			}
		}
		return topSellAppListCrawling(url);
	}
	// (sort == null | sort != null) & (maxPrice != null & Specials != null)
	public List<StoreVO> crawlingStoreTopSeller(String sort, String maxPrice, String specials) {
		String url = "https://store.steampowered.com/search/?maxprice=" + maxPrice + "&specials=" + specials + "&filter=topsellers";
		if(sort != null) {
			url = "https://store.steampowered.com/search/?sort_by=" + sort + "&maxprice=" + maxPrice + "&specials=" + specials + "&filter=topsellers";
		}
		return topSellAppListCrawling(url);
	}
	
	
	// url
	public List<StoreVO> topSellAppListCrawling(String url) {
		ArrayList<StoreVO> list = new ArrayList<StoreVO>();
		
		Connection conn = Jsoup.connect(url);
		conn.cookie("Steam_Language", "koreana");
		
		try {
			Document document = conn.get();
			Element urlElement = document.getElementById("search_resultsRows");
			Elements aElements = urlElement.select("a");
			for (Element element : aElements) {
				StoreVO sVO = new StoreVO();
				
				sVO.setAppId(element.attr("abs:data-ds-itemkey").substring(element.attr("abs:data-ds-itemkey").lastIndexOf("/") + 1));
				sVO.setImg(element.select("img").attr("abs:src"));
				
				String appType = sVO.getAppId().substring(0, sVO.getAppId().indexOf("_"));
				sVO.setTitle(element.select("span[class=\"title\"]").text());
					
				if(!appType.equals("Bundle")) {
					sVO.setReleased(element.select("div[class=\"col search_released responsive_secondrow\"]").text());
				}
				
				
				Element reviewScore = element.selectFirst("div[class=\"col search_reviewscore responsive_secondrow\"]");
				if(reviewScore.select("span").hasClass("positive")) {
					sVO.setReviewSummary("positive");
				} else if(reviewScore.select("span").hasClass("mixed")) {
					sVO.setReviewSummary("mixed");
				} else if(reviewScore.select("span").hasClass("negative")) {
					sVO.setReviewSummary("negative");
				}
				
				sVO.setDiscount(element.select("div[class=\"col search_discount responsive_secondrow\"]").text());
				
				Element price = element.selectFirst("div[class=\"col search_price  responsive_secondrow\"]");
				if(price == null) {
					price = element.selectFirst("div[class=\"col search_price discounted responsive_secondrow\"]");
					sVO.setPrice(price.select("strike").text());
					
					String priceText = price.text();
					String disPrice = priceText.substring(priceText.lastIndexOf("₩"));
					sVO.setDiscountPrice(disPrice);
				} else {
					sVO.setPrice(price.text());
				}
				
				list.add(sVO);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public List<StoreVO> crawlingStoreNewTop(String cardType) {
		ArrayList<StoreVO> list = new ArrayList<StoreVO>();
		
		String url = "https://store.steampowered.com/explore/new/";
		
		Connection conn = Jsoup.connect(url);
		conn.cookie("Steam_Language", "koreana");
		
		try {
			Document document = conn.get();
			
			if(cardType.equals("medium")) {
				Element element = document.getElementById("tab_newreleases_content");
				Elements anchor = element.select("a[class=\"tab_item  \"]");
				for(int i = 0 ; i < anchor.size() ; i++) {
					StoreVO sVO = new StoreVO();
					sVO.setAppId(anchor.get(i).attr("abs:data-ds-itemkey").substring(anchor.get(i).attr("abs:data-ds-itemkey").lastIndexOf("/") + 1));
					sVO.setImg(anchor.get(i).select("img").attr("abs:src"));
					sVO.setTitle(anchor.get(i).select("div[class=\"tab_item_name\"]").text());
					sVO.setType(anchor.get(i).select("div[class=\"tab_item_top_tags\"]").text());
					
					Element priceDiv = anchor.get(i).selectFirst("div[class=\"discount_block tab_item_discount\"]");
					if(priceDiv == null) {
						sVO.setPrice(anchor.get(i).select("div[class=\"discount_final_price\"]").text());
					} else {
						sVO.setDiscount(anchor.get(i).select("div[class=\"discount_pct\"]").text());
						sVO.setPrice(anchor.get(i).select("div[class=\"discount_original_price\"]").text());
						sVO.setDiscountPrice(anchor.get(i).select("div[class=\"discount_final_price\"]").text());
					}
					
					list.add(sVO);
				}
			} else if(cardType.equals("mini10000")) {
				Element mini10000Div = document.selectFirst("div[class=\"home_specials_ctn underten sidebar_wide\"]");
				Elements anchor = mini10000Div.getElementsByTag("a");
				for(int i = 0 ; i < anchor.size() ; i++) {
					StoreVO sVO = new StoreVO();
					sVO.setImg(anchor.get(i).select("img").attr("abs:src"));
					
					Element priceDiv = anchor.get(i).selectFirst("div[class=\"discount_block discount_block_inline discount_block_collapsable\"]");
					if(priceDiv == null) {
						sVO.setPrice(anchor.get(i).select("div[class=\"discount_final_price\"]").text());
					} else {
						sVO.setDiscount(anchor.get(i).select("div[class=\"discount_pct\"]").text());
						sVO.setPrice(anchor.get(i).select("div[class=\"discount_original_price\"]").text());
						sVO.setDiscountPrice(anchor.get(i).select("div[class=\"discount_final_price\"]").text());
					}
					
					list.add(sVO);
				}
			} else if(cardType.equals("mini5000")) {
				Element mini5000Div = document.selectFirst("div[class=\"home_specials_ctn underten underfive\"]");
				Elements anchor = mini5000Div.getElementsByTag("a");
				for(int i = 0 ; i < anchor.size() ; i++) {
					StoreVO sVO = new StoreVO();
					sVO.setImg(anchor.get(i).select("img").attr("abs:src"));
					
					Element priceDiv = anchor.get(i).selectFirst("div[class=\"discount_block discount_block_inline discount_block_collapsable\"]");
					if(priceDiv == null) {
						sVO.setPrice(anchor.get(i).select("div[class=\"discount_final_price\"]").text());
					} else {
						sVO.setDiscount(anchor.get(i).select("div[class=\"discount_pct\"]").text());
						sVO.setPrice(anchor.get(i).select("div[class=\"discount_original_price\"]").text());
						sVO.setDiscountPrice(anchor.get(i).select("div[class=\"discount_final_price\"]").text());
					}
					
					list.add(sVO);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public List<StoreVO> crawlingStoreCategory() {
		ArrayList<StoreVO> list = new ArrayList<StoreVO>();
		
		
		return list;
	}
	
	
	// 스토어 디테일 페이지 크롤링
	public StoreVO crawlingStoreDetail(String appId, StoreVO sVO) {
		String url = "https://store.steampowered.com/app/" + appId;
		
		Connection conn = Jsoup.connect(url);
		
		try {
			Document document = conn.get();
			Element element = document.selectFirst("div[class=\"summary column\"]").selectFirst("span[itemprop=\"description\"]");
			if(element != null) {
				if(element.hasClass("positive")) {
					sVO.setReviewSummary("positive");
				} else if(element.hasClass("mixed")) {
					sVO.setReviewSummary("mixed");
				} else if(element.hasClass("negative")) {
					sVO.setReviewSummary("negative");
				} 
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return sVO;
	}
	
}