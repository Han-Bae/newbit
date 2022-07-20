package com.newbit.www.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.newbit.www.vo.StoreVO;

public class StoreJsonSimple {
	
	public StoreVO getDetailJson(String appId) {
		StoreVO sVO = new StoreVO();
		
		try {
			URL url = new URL("https://store.steampowered.com/api/appdetails?appids=" + appId + "&l=korean");
			BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String result = bf.readLine();
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
			JSONObject appJson = (JSONObject) jsonObject.get(appId);
			JSONObject appData = (JSONObject) appJson.get("data");
			
			String appType = (String) appData.get("type");
			System.out.println(appType);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return sVO;
	}
}
