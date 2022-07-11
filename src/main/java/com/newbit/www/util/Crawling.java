package com.newbit.www.util;

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
 * 			작업이력 ] 2022.07.06 - 담당자 전다빈 : 크롤링 클래스 제작
 *
 */
public class Crawling {
	
	public List<StoreVO> crawlingStoreMain(String sort) {
		ArrayList<StoreVO> list = new ArrayList<StoreVO>();
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
		
		try {
			Document document = conn.get();
			Element urlElement = document.getElementById("search_resultsRows");
			Elements aElements = urlElement.select("a");
			for (Element element : aElements) {
				StoreVO storeVO = new StoreVO();
				storeVO.setAppId(element.attr("abs:data-ds-appid").substring(element.attr("abs:data-ds-appid").lastIndexOf("/") + 1));
				storeVO.setImg(element.select("img").attr("abs:src"));
				storeVO.setTitle(element.select("span[class=\"title\"]").text());
				storeVO.setReleased(element.select("div[class=\"col search_released responsive_secondrow\"]").text());
				
				Element reviewScore = element.selectFirst("div[class=\"col search_reviewscore responsive_secondrow\"]");
				if(reviewScore.select("span").hasClass("positive")) {
					storeVO.setReviewSummary("positive");
				} else if(reviewScore.select("span").hasClass("mixed")) {
					storeVO.setReviewSummary("mixed");
				} else if(reviewScore.select("span").hasClass("negative")) {
					storeVO.setReviewSummary("negative");
				}
				
				storeVO.setDiscount(element.select("div[class=\"col search_discount responsive_secondrow\"]").text());
				
				Element price = element.selectFirst("div[class=\"col search_price  responsive_secondrow\"]");
				if(price == null) {
					price = element.selectFirst("div[class=\"col search_price discounted responsive_secondrow\"]");
					storeVO.setPrice(price.select("strike").text());
					
					String priceText = price.text();
					String disPrice = priceText.substring(priceText.lastIndexOf("₩"));
					storeVO.setDiscountPrice(disPrice);
				} else {
					storeVO.setPrice(price.text());
				}
				
				list.add(storeVO);
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
	
	/*
	 public static void main(String[] args) {
	        final String inflearnUrl = "https://store.steampowered.com/search/?filter=topsellers";
	        Connection conn = Jsoup.connect(inflearnUrl);

	        try {
	            Document document = conn.get();
	            Element el = document.getElementById("search_resultsRows");
	            Elements els = el.select("a");
	            for(Element e : els) {
	            	System.out.println(e.selectFirst("div[class=\"col search_price  responsive_secondrow\"]"));
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	   */
	  
	
}
