package com.newbit.www.service;

import java.io.*;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.newbit.www.vo.PaymentVO;

@Service
public class PaymentImp implements PaymentService {
	private String impKey = "5143054043557146";
	private String impSecret = "5cAg9C2HWGwSrVpy715edMeG5FntMfusCBmKAT7fAph3lVEZBF7zQfSGmK0NAb4jo4uHgNtXcQBQz47z";
	
	/// Imp클래스에서만 사용할 클래스이기에 내부에 생성
	private class Response{
		private PaymentVO response;
		public PaymentVO getResponse() {
			return response;
		}
		public void setResponse(PaymentVO response) {
			this.response = response;
		}
		@Override
		public String toString() {
			return "Response [response=" + response + "]";
		}		
	}
	private class PaymentInfo{
		private int amount;
		public int getAmount() {
			return amount;
		}
		public void setAmount(int amount) {
			this.amount = amount;
		}
		@Override
		public String toString() {
			return "PaymentInfo [amount=" + amount + "]";
		}
	}
	/////////////////////////////////////////////////////
	// 토큰 받아오기
	@Override
	public String getToken() throws IOException {
		HttpsURLConnection conn = null;
		 
		URL url = new URL("https://api.iamport.kr/users/getToken");

		conn = (HttpsURLConnection) url.openConnection();

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setRequestProperty("Accept", "application/json");
		conn.setDoOutput(true);
		
		JsonObject json = new JsonObject();
		 
		json.addProperty("imp_key", impKey);
		json.addProperty("imp_secret", impSecret);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		
		bw.write(json.toString());
		bw.flush();
		bw.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

		Gson gson = new Gson();

		String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();

		String token = gson.fromJson(response, Map.class).get("access_token").toString();

		br.close();
		conn.disconnect();

		return token;
	}
	
	// 토큰을 이용해서 결제정보 가져오기
	@Override
	public int paymentInfo(String imp_uid, String access_token) throws IOException {
	    HttpsURLConnection conn = null;
	 
	    URL url = new URL("https://api.iamport.kr/payments/" + imp_uid);
	 
	    conn = (HttpsURLConnection) url.openConnection();
	 
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Authorization", access_token);
	    conn.setDoOutput(true);
	 
	    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
	    
	    Gson gson = new Gson();
	    
	    Response response = gson.fromJson(br.readLine(), Response.class);
	    
	    br.close();
	    conn.disconnect();
	    
	    return response.getResponse().getAmount();
	}


	@Override
	public void payMentCancle(String access_token, String imp_uid, int amount, String reason) throws IOException{
		HttpsURLConnection conn = null;
		URL url = new URL("https://api.iamport.kr/payments/cancel");
 
		conn = (HttpsURLConnection) url.openConnection();
 
		conn.setRequestMethod("POST");
 
		conn.setRequestProperty("Content-type", "application/json");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Authorization", access_token);
 
		conn.setDoOutput(true);
		
		JsonObject json = new JsonObject();
 
		json.addProperty("reason", reason);
		json.addProperty("imp_uid", imp_uid);
		json.addProperty("amount", amount);
		json.addProperty("checksum", amount);
 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
 
		bw.write(json.toString());
		bw.flush();
		bw.close();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
 
		br.close();
		conn.disconnect();
	}

}
