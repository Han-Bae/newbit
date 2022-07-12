package com.newbit.www.service;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@Controller
public class PayService {

	private IamportClient api;
	
	@GetMapping("/iamport")
	public String iamport(){
		return "index"; 
	} 
	
	public PayService() {
		this.api = new IamportClient("5143054043557146",
				"5cAg9C2HWGwSrVpy715edMeG5FntMfusCBmKAT7fAph3lVEZBF7zQfSGmK0NAb4jo4uHgNtXcQBQz47z");
	}

	@ResponseBody
	@RequestMapping(value="/verifyIamport/{imp_uid}")
	public IamportResponse<Payment> paymentByImpUid(Model model
			, Locale locale	, HttpSession session
			, @PathVariable(value= "imp_uid") String imp_uid) throws IamportResponseException, IOException
	{	
			return api.paymentByImpUid(imp_uid);
	}
	
}