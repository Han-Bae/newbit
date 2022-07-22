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
	
	public List<StoreVO> crawlingStoreMain(String sort) {
		String url = "https://store.steampowered.com/search/?filter=topsellers";
		if(sort != null) {
			switch(sort) {
			case "":
				break;
			case "releasedDESC":
				url = "https://store.steampowered.com/search/?sort_by=Released_DESC&filter=topsellers";
				break;
			case "nameASC":
				url = "https://store.steampowered.com/search/?sort_by=Name_ASC&filter=topsellers";
				break;
			case "priceASC":
				url = "https://store.steampowered.com/search/?sort_by=Price_ASC&filter=topsellers";
				break;
			case "priceDESC":
				url = "https://store.steampowered.com/search/?sort_by=Price_DESC&filter=topsellers";
				break;
			case "reviewsDESC":
				url = "https://store.steampowered.com/search/?sort_by=Reviews_DESC&filter=topsellers";
				break;
			}
		}
		Connection conn = Jsoup.connect(url);
		
		ArrayList<StoreVO> list = new ArrayList<StoreVO>();
		
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
	
	
	public List<StoreVO> crawlingStoreCategory() {
		ArrayList<StoreVO> list = new ArrayList<StoreVO>();
		
		
		return list;
	}
	
	
}