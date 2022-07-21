package com.newbit.www.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.newbit.www.vo.StoreVO;

/**
 * 
 * @author 전다빈
 * @since	2022.07.20
 * @version v.1.0
 * 
 * 			작업이력 ] 2022.07.20 - 담당자 전다빈 : json-simple을 이용한 api 제작
 *
 */

public class StoreJsonSimple {
	
	public StoreVO getDetailJson(String appId) {
		StoreVO sVO = new StoreVO();
		
		try {
			// json url
			URL url = new URL("https://store.steampowered.com/api/appdetails?appids=" + appId + "&l=korean");
			InputStreamReader isr = new InputStreamReader(url.openStream(), "UTF-8");
			BufferedReader bf = new BufferedReader(isr);
			String result = bf.readLine();
			
			JSONParser jsonParser = new JSONParser();
			// JSONObject; 예시 => data: {}
			// JSONArray;  예시 => developers: []
			JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
			JSONObject appJson = (JSONObject) jsonObject.get(appId);
			JSONObject appData = (JSONObject) appJson.get("data");
			
			sVO.setType((String) appData.get("type"));
			sVO.setTitle((String) appData.get("name"));
			sVO.setDetailedDescription((String) appData.get("about_the_game"));
			
			if(((String) appData.get("type")).equals("dlc")) {
				sVO.setFullgameId((String) ((JSONObject) appData.get("fullgame")).get("appid"));
				sVO.setFullgameTitle((String) ((JSONObject) appData.get("fullgame")).get("name"));
			} else if(((String) appData.get("type")).equals("game")) {
				sVO.setShortDescription((String) appData.get("short_description"));
			}
			
			sVO.setImg((String) appData.get("header_image"));
			
			ArrayList<String> developers = new ArrayList<String>();
			JSONArray devArr = (JSONArray) appData.get("developers");
			for(int i = 0 ; i < devArr.size() ; i++) {
				developers.add((String) devArr.get(i));
			}
			sVO.setDevelopers(developers);
			
			ArrayList<String> publishers = new ArrayList<String>();
			JSONArray publArr = (JSONArray) appData.get("publishers");
			for(int i = 0 ; i < publArr.size() ; i++) {
				publishers.add((String) publArr.get(i));
			}
			sVO.setPublishers(publishers);
			
			ArrayList<String> tags = new ArrayList<String>();
			JSONArray tagArr = (JSONArray) appData.get("genres");
			for(int i = 0 ; i < tagArr.size() ; i++) {
				tags.add((String) ((JSONObject) tagArr.get(i)).get("description"));
			}
			sVO.setTags(tags);
			
			Boolean isFree = (Boolean) appData.get("is_free");
			if(isFree == false) {
				JSONObject priceOverview = (JSONObject) appData.get("price_overview");
				String discount = String.valueOf(priceOverview.get("discount_percent"));
				sVO.setDiscount(discount);
				if(discount.equals("0")) {
					sVO.setPrice((String) priceOverview.get("final_formatted"));
				} else {
					sVO.setPrice((String) priceOverview.get("initial_formatted"));
					sVO.setDiscountPrice((String) priceOverview.get("final_formatted"));
				}
				
				sVO.setPackageTitle((String) ((JSONObject) ((JSONArray) appData.get("package_groups")).get(0)).get("title"));
			}
			
			/* 장르 추가 */
			HashMap<String, String> ssMap = new HashMap<String, String>();
			JSONArray screenshots = (JSONArray) appData.get("screenshots");
			for(int i = 0 ; i < screenshots.size() ; i++) {
				ssMap.put((String) ((JSONObject) screenshots.get(i)).get("path_thumbnail"), (String) ((JSONObject) screenshots.get(i)).get("path_full"));
			}
			sVO.setScreenshot(ssMap);
			
			HashMap<String, String> mvMap = new HashMap<String, String>();
			JSONArray movies = (JSONArray) appData.get("movies");
			if(movies != null) {
				for(int i = 0 ; i < movies.size() ; i++) {
					mvMap.put((String) ((JSONObject) movies.get(i)).get("thumbnail"),
							(String) ((JSONObject) ((JSONObject) movies.get(i)).get("mp4")).get("480"));
				}
				sVO.setMovie(mvMap);
			}
			
			sVO.setReleased((String) ((JSONObject) appData.get("release_date")).get("date"));
			
			bf.close();
			isr.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return sVO;
	}
	
	public String getFullgameImg(String appId) {
		String fullgameImg = "";
		
		try {
			URL url = new URL("https://store.steampowered.com/api/appdetails?appids=" + appId + "&l=korean");
			InputStreamReader isr = new InputStreamReader(url.openStream(), "UTF-8");
			BufferedReader bf = new BufferedReader(isr);
			String result = bf.readLine();
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
			JSONObject appJson = (JSONObject) jsonObject.get(appId);
			JSONObject appData = (JSONObject) appJson.get("data");
			
			fullgameImg = (String) appData.get("header_image");
			
			bf.close();
			isr.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return fullgameImg;
	}
}
