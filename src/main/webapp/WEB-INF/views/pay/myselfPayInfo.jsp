<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<!-- 
		메인
		
		제작자 : 김태현
		since : 2022.07.16
		version : v.1.0
	-->
<html>
<head>
<meta charset="UTF-8">
<title>결제 시작</title>
	<link rel="icon" type="image/png" sizes="32x32" href="/www/img/favicon/favicon.ico">
<!-- head에서 꼭 넣어야 하는것 아래부터 -->
	<link rel="stylesheet" type="text/css" href="/www/css/theme/black-dashboard.css">
	<link rel="stylesheet" type="text/css" href="/www/css/theme/nucleo-icons.css">
	<link rel="stylesheet" type="text/css" href="/www/css/components/header.css">
	<link rel="stylesheet" type="text/css" href="/www/css/store/pay.css">
	<script src="https://kit.fontawesome.com/e0f46f82c6.js"></script>
<!-- 위까지 -->
	<script type="text/javascript" src="/www/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="/www/js/bootstrap.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<!-- iamport.payment.js -->
  <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
	<script type="text/javascript" src="/www/js/components/header.js"></script>
	<script type="text/javascript" src="/www/js/pay/payment.js"></script>
</head>

<body>
	
	<!-- 여기부터 -->
	<!-- 
		헤더
		
		제작자 : 전다빈
		since : 2022.06.29
		version : v.1.0
	-->
	<header>
		<div class="header-line"></div>
		<div class="header-logo">
			<img src="/www/img/logo.png" height="80px">
		</div>
		
		<ul class="main-nav">
			<li>
				<a class="nav-link active" href="/www/store/games.nbs">
					<i class="tim-icons icon-cart main-nav-icon"></i>
					<span class="main-nav-text">Store</span>
					<div class="indicator"></div>
				</a>
			</li>
			<li>
				<a class="nav-link active" href="#">
					<i class="tim-icons icon-controller"></i>
					<span class="main-nav-text">Library</span>
					<div class="indicator"></div>
				</a>
			</li>
			<li>
				<a class="nav-link active" href="#">
					<i class="tim-icons icon-world"></i>
					<span class="main-nav-text">Community</span>
					<div class="indicator"></div>
				</a>
			</li>
			<li>
				<a class="nav-link active" href="#">
					<i class="fa-solid fa-ghost"></i>
					<span class="main-nav-text">Newbit</span>
					<div class="indicator"></div>
				</a>
			</li>
		</ul>
		<div class="user-nav">
<c:if test="${empty SID}">
			<a href="/www/account/login.nbs">Log In</a>
</c:if>
<c:if test="${not empty SID}">
			<a href="/www/account/logout.nbs">Log Out</a>
			<a href="#"><i class="tim-icons icon-single-02"></i></a>
</c:if>
			<a href="/www/payment/basket.nbs"><i class="tim-icons icon-basket-simple"></i></a>
			<a href="/www/payment/pick.nbs"><i class="tim-icons icon-basket-simple"></i></a>
		</div>
	</header>
	<!-- 여기까지 -->
	<main>
<c:if test="${not empty stat}">
		<input type="hidden" id=self_stat value="${stat}"></c:if>
<form method="POST" action="/www/payment/myselfPayCheck.nbs"
		name="frm_info" id="frm_info" class="frm">
		<input type="hidden" name="paySel">
</form>
		<div class="store-top pay-top">
			<h4>결제정보 확인</h4>
			<h5>▶</h5>
			<h4>확인 및 구매</h4>
		</div>
		<hr class="payStat">
		<div class="payMain">
			<div class="row">
				<div style="padding-top: 20px; margin-bottom:40px;"><h2 style="margin-left: 20px; margin-bottom: 10px;">결제 수단 선택</h2>
					<div class="selectPay" >
						<select class="form-select" id="selPay" name="selPay">
							<option selected disabled>결제수단을 선택해주세요.</option>
							<option value="card">일반 카드결제</option>
							<option value="kakao">카카오페이</option>
						</select>
					</div>
			  	</div>
				<!-- 선물내용 -->
				<div class="payInfo" style="display:none;">
					<h4 style="margin-left: 40px">제공되는 결제 수단은 아래와 같습니다.</h4>
					<div class="payMain">
						<img id="howPay" style="width:20%; height:100%; margin-bottom: 50px;">
					</div>
				</div>
			</div> 
				<div class="conti">
					<button type="button" id="cbtn3" onclick='next3()' class="btn btn-success animation-on-hover">Continue</button>
				</div>
		</div>
	</main>
</body>
</html>