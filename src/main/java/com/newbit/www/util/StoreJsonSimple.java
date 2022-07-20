package com.newbit.www.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.newbit.www.vo.StoreVO;

public class StoreJsonSimple {
	
	public StoreVO getDetailJson(String appId) {
		StoreVO sVO = new StoreVO();
		
		try {
			// json url
			URL url = new URL("https://store.steampowered.com/api/appdetails?appids=" + appId + "&l=korean");
			BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String result = bf.readLine();
			
			JSONParser jsonParser = new JSONParser();
			// JSONObject; 예시 => data: {}
			// JSONArray;  예시 => developers: []
			JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
			JSONObject appJson = (JSONObject) jsonObject.get(appId);
			JSONObject appData = (JSONObject) appJson.get("data");
			
			sVO.setType((String) appData.get("type"));
			sVO.setTitle((String) appData.get("name"));
			sVO.setDetailedDescription((String) appData.get("detailed_description"));
			if(((String) appData.get("type")).equals("dlc")) {
				sVO.setFullgameId((String) ((JSONObject) appData.get("fullgame")).get("appid"));
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
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return sVO;
	}
}
