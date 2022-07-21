package com.newbit.www.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.newbit.www.vo.StoreVO;

/**
 * 
 * @author 전다빈
 * @since	2022.07.21
 * @version v.1.0
 * 
 * 			작업이력 ] 2022.07.21 - 담당자 전다빈 : json-simple을 이용한 api 제작
 *
 */
public class ProfileJsonSimple {

	public List<StoreVO> getLibraryJson(List<StoreVO> list) {
		for(int i = 0 ; i < list.size() ; i++) {
			try {
				String appId = list.get(i).getAppId().substring(list.get(i).getAppId().indexOf("_") + 1);
				URL url = new URL("https://store.steampowered.com/api/appdetails?appids=" + appId + "&l=korean");
				BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
				String result = bf.readLine();
				
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
				JSONObject appJson = (JSONObject) jsonObject.get(appId);
				JSONObject appData = (JSONObject) appJson.get("data");
				
				list.get(i).setTitle((String) appData.get("name"));
				list.get(i).setImg((String) appData.get("header_image"));
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return list;
	}
}
