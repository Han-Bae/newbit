<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<!-- 
		메인
		
		제작자 : 김태현
		since : 2022.07.06
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
		<div class="store-top pay-top">
<c:if test="${not empty stat}">
		<input type="hidden" id=stat value="${stat}">
</c:if>
<form method="POST" action="/www/payment/payFormInfo.nbs"
		name="frm_meno" id="frm_memo" class="frm">
<c:forEach var="name" items="${nameList}" varStatus="status">
			<!-- 전송용 -->
    	<input type="hidden" name="name" value="${name}">
</c:forEach>
		<input type="hidden" name="nameList">
		<input type="hidden" name="presentTitle">
		<input type="hidden" name="presentMsg">

</form>
			<h4>선물 보내기</h4><h5>▶</h5>
			<h4>메모작성</h4><h5>▶</h5>
			<h4>결제 정보</h4><h5>▶</h5>
			<h4>확인 및 구매</h4>
		</div>
		<hr class="payStat">
		<div class="payMain">
			<div class="row">
				<div style="padding-top: 20px; margin-bottom:40px;"><h2 style="margin-left: 20px; margin-bottom: 10px;">친구에게 마음을 전달하세요.</h2>
					<input type="text" class="form-control" readonly
			  			name="friendList" style="margin-left: 40px; max-width:350px;" id="friendList"></input>
			  		<p style="margin-left: 40px;" id="friendList"></p>
			  		<input type="text" class="form-control" placeholder="메세지 제목"
			  			name="friend-title" style="margin-left: 40px; max-width:350px;" id="friend-title"></input>
			  	</div>
				<!-- 선물내용 -->
				<div class="friend-main">
					<label style="margin-left: 30px;">선물 메세지 (160자 제한), 현재 <span id="restText">160</span>자 남음</label><br>
					<textarea class="textarea" id="friendBody" name="friendBody" rows="10" cols="60" 
						placeholder="메세지를 입력해주세요." required maxlength="160">게임 재미있게 즐기세요!</textarea>
				</div>
			</div> 
				<div class="conti">
					<button type="button" id="cbtn2" onclick='next2()' class="btn btn-success animation-on-hover">Continue</button>
				</div>
		</div>
	</main>
</body>
</html>