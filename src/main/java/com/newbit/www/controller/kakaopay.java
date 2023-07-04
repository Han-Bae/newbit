package com.newbit.www.controller;

import java.io.*;
import java.net.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
@RequestMapping("/kakao")
public class kakaopay {
	@RequestMapping("pay2.blp")
	public ModelAndView serve(ModelAndView mv) {
		mv.setViewName("/serve");
		return mv;
	}
	@RequestMapping("/pay.blp")
	@ResponseBody
	public String kakaopay() {
		try {
			URL address = new URL("https://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection scon = (HttpURLConnection)address.openConnection();
			scon.setRequestMethod("POST");
			scon.setRequestProperty("Authorization", "KakaoAK 59a80d41e2423e8eb2c236f3bcc79794");
			scon.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			//scon.setDoInput(true);//<- 원래 두인풋은 디폴트가 true임, 이 줄은 없어도 관계없음
			scon.setDoOutput(true);   
			String param = "cid=TC0ONETIME"
					+ "&partner_order_id=testo01"
					+ "&partner_user_id=testu01"
					+ "&item_name=하나둘"
					+ "&quantity=1"
					+ "&total_amount=2200"
					+ "&vat_amount=200"
					+ "&tax_free_amount=0"
					+ "&approval_url=http://localhost/www/"
					+ "&fail_url=http://localhost/www/"
					+ "&cancel_url=http://localhost/www/";
			OutputStream  out = scon.getOutputStream();
			DataOutputStream dout = new DataOutputStream(out);
			dout.writeBytes(param);
			dout.close();
			
			int result = scon.getResponseCode();
			
			InputStream in;
			if(result == 200) {
				in = scon.getInputStream();
			}else {
				in = scon.getErrorStream();
			}
			InputStreamReader reader = new InputStreamReader(in);
			BufferedReader bfreader = new BufferedReader(reader);
			return bfreader.readLine();
		}/*catch(Exception e){
			e.printStackTrace();
		}*/catch(MalformedURLException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return "{\"result\":\"NO\"}";
	}
}