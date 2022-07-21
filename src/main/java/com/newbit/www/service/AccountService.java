package com.newbit.www.service;

import java.net.*;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.newbit.www.vo.*;

/**
 * 이 클래스는 계정 관리 전반의 서비스에 대한 클래스
 * 
 * @author 김태현
 * @since 2022.06.30
 * @version v.1.0
 * 
 *          작업이력 ] 2022.06.30 - 담당자 : 김태현 -> 클래스제작
 */
public class AccountService {
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private String myIP;
	
	private PaymentVO pVO;
	
	
	public PaymentVO getpVO() {
		return pVO;
	}
	public void setpVO(PaymentVO pVO) {
		this.pVO = pVO;
	}
	
	
	// 메일로 전송하기 위해 ID의 일부만 표시해주는 함수
	public char[] convertID(String id) {
		char[] myID = new char[id.length()];
		for(int i = 0; i < id.length(); i++) {
			if(i < (int)((id.length()*(2./3.)))) {
				myID[i] = id.charAt(i);						
			}	else {
				myID[i] = '*';
			}
		}
		return myID;
	}	
	// 메일 전송
	public void sendMail(AccountVO aVO) {
			// 메일 전송 선작업
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				 return new PasswordAuthentication("taehk1011@gmail.com", "mgphcsbdzbpnvgag");
			}
		});
		setMyIP();	// IP주소 받기
			// 게임 결제 시 정보 전송
		if(aVO.getpVO() != null) {
			sendGame(aVO, session);
		}
		else {
			// 아이디찾기 메일이면
			if(aVO.getConvert_id() != null) {
				sendID(aVO, session);
			}else {
				sendJoin(aVO, session);
			}
		}
	}
	
	// 아이디 찾기 메일 내용
	public void sendID(AccountVO aVO, Session session) {

		String sid = new String(aVO.getConvert_id());
		String receiver = aVO.getEmail(); // 메일 받을 주소
		String content = "<div style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 540px; height: 600px; border-top: 4px solid purple; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">"
+ "	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">"
+ "		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">NewBit Store</span><br />"
+ "		<span style=\"color: purple;\">아이디 확인</span> 안내입니다."
+ "	</h1>"
+ "	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">"
+ "		안녕하세요.<br />"
+ "		회원님의 아이디 요청 확인 메일입니다.<br />"
+ "		아래 <b style=\"color: purple;\">'아이디 확인'</b> 버튼을 클릭한 뒤, 다시 로그인하세요.<br />"
+ "		감사합니다."
+ "	</p>"
+ ""
+ "	<p style=\"font-size: 16px; margin: 40px 5px 20px; line-height: 28px;\">"
+ "		회원님 아이디: <br />"
+ "		<span style=\"font-size: 24px;\">"+sid+"</span>"
+ "	</p>"
+ "	<a style=\"color: #FFF; text-decoration: none; text-align: center;\" href=\"http://"+myIP+"/www/account/login.nbs\" target=\"_blank\"><p style=\"display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; background: purple; line-height: 45px; vertical-align: middle; font-size: 16px;\">아이디 확인</p></a>"
+ "	<div style=\"border-top: 1px solid #DDD; padding: 5px;\">\r\n"
+ "		<p style=\"font-size: 13px; line-height: 21px; color: #555;\">\r\n"
+ "			만약 버튼이 정상적으로 클릭되지 않는다면, 아래 링크를 복사하여 접속해 주세요.<br />\r\n"
+ "			http://"+myIP+"/www/account/login.nbs"
+ "\r\n"
+ "		</p>\r\n"
+ "	</div>\r\n"
+ "</div>";
		String title = "아이디 체크 메일입니다.";
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("taehk1011@gmail.com", "NewBit Store", "utf-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html; charset=utf-8");

			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 회원가입 메일
	public void sendJoin(AccountVO aVO, Session session) {
		String receiver = aVO.getEmail(); // 메일 받을 주소
		String title = "회원 인증 메일입니다.";
		String content = "<div style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 540px; height: 600px; border-top: 4px solid purple; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">\r\n"
				+ "	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">\r\n"
				+ "		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">NewBit Store</span><br />\r\n"
				+ "		<span style=\"color: purple; \">메일인증</span> 안내입니다.\r\n"
				+ "	</h1>\r\n"
				+ "	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">\r\n"
				+ "		안녕하세요.<br />\r\n"
				+ "		NewBit Store에 가입해 주셔서 진심으로 감사드립니다.<br />\r\n"
				+ "		아래 <b style=\"color: purple;\">'메일 인증'</b> 버튼을 클릭하여 회원가입을 완료해 주세요.<br />\r\n"
				+ "		감사합니다.\r\n"
				+ "	</p>\r\n"
				+ "\r\n"
				+ "	<a style=\"color: #FFF; text-decoration: none; text-align: center;\" href=\"http://"+myIP+"/www/account/checkMail.nbs?ck="+aVO.getCk_mail()
				+ "\" target=\"_blank\"><p style=\"display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; background: purple; line-height: 45px; vertical-align: middle; font-size: 16px;\">메일 인증</p></a>\r\n"
				+ "\r\n"
				+ "	<div style=\"border-top: 1px solid #DDD; padding: 5px;\">\r\n"
				+ "		<p style=\"font-size: 13px; line-height: 21px; color: #555;\">\r\n"
				+ "			만약 버튼이 정상적으로 클릭되지 않는다면, 아래 링크를 복사하여 접속해 주세요.<br />\r\n"
				+ "			http://"+myIP+"/www/account/checkMail.nbs?ck"+aVO.getCk_mail()
				+ "\r\n"
				+ "		</p>\r\n"
				+ "	</div>\r\n"
				+ "</div>";
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("taehk1011@gmail.com", "NewBit Store", "utf-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html; charset=utf-8");

			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		// 게임 결제 메일
		public void sendGame(AccountVO aVO, Session session) {
			String receiver = aVO.getEmail(); // 메일 받을 주소
			String title = "NewBit 결제 메일입니다.";
			String content = "<div style=\"background: #1e2024;color: white;font-family:'Apple SD Gothic Neo','sans-serif'!important;width:540px;height:600px;border-top:4px solid purple;margin:100px auto;padding:30px 0;box-sizing:border-box\">\r\n"
					+ "	<h1 style=\"margin:0;background: #800080;padding:0 5px;font-size:28px;font-weight:400\">\r\n"
					+ "		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">NewBit Store</span><br />\r\n"
					+ "		<span style=\"color: #1e2024; \"><b>결제 완료</b></span> 안내입니다.\r\n"
					+ "	</h1>\r\n"
					+ "	<p style=\"margin-left: 10px; font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">\r\n"
					+ "		안녕하세요. "+aVO.getNickname()+" 님<br />\r\n"
					+ "		결제완료 메일입니다.<br />\r\n"
					+ "		<b style=\"color: purple;\">'결제된 게임'</b><br />\r\n";
			// 결제한 게임 수만큼 반복
			for(int i = 0; i < aVO.getpVO().getGameIdList().size(); i++) {
				content += "<div style=\"display:flex; margin-bottom:10px; width: 100%; justify-content:space-around;"
						+ " font-size: 15pt;\"> <img class=\"card-img-left\" src=\""+aVO.getpVO().getsVOList().get(i).getImg()+"\" width=\"200px\" height=\"100%\">"
						+ "<div style=\"flex-driection: row; margin-left: 20px;\"><div>게임명 : " +aVO.getpVO().getsVOList().get(i).getTitle() +"<br /></div>\r\n"
						+"<div>&nbsp;게임 가격 : " +aVO.getpVO().getGamePriceList().get(i)+"원<br />\r\n</div></div></div>";
			}
			// 선물을 보낸 경우엔 친구 리스트 추가
			if(aVO.getpVO().getNameList() != null) {
				content += "친구 ";
				for(int i = 0; i < aVO.getpVO().getNameList().size(); i++) {
						content += aVO.getpVO().getNameList().get(i)+", ";
				}
				content = content.substring(0, content.length()-2);
				content += "님에게 선물하셨습니다.<br />\r\n";
			}
			content +="		<h3 style=\"text-align:center\">감사합니다.</h3>\r\n"
					+ "	</p>\r\n"
					+ "\r\n"
					+ "</div>";
			Message message = new MimeMessage(session);
			try {
				message.setFrom(new InternetAddress("taehk1011@gmail.com", "NewBit Store", "utf-8"));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
				message.setSubject(title);
				message.setContent(content, "text/html; charset=utf-8");
				
				Transport.send(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
//	IP 확인용
	public String getMyIP() {
		return myIP;
	}
	// 메일 링크 전송용 서버IP확인
	public void setMyIP(String myIP) {
		this.myIP = myIP;
	}
	public void setMyIP() {
		String test = "";
		InetAddress local = null;
		try {
			local = InetAddress.getLocalHost();
		}
		catch ( UnknownHostException e ) {
			e.printStackTrace();
		}
			
		if( local == null ) {
			test =  "";
		}
		else {
			String ip = local.getHostAddress();
			test = ip;
		}
		this.myIP = test;
	}
}
