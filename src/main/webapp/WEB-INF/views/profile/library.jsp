<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Newbit Game</title>
	<link rel="icon" type="image/png" sizes="32x32" href="/www/img/favicon/favicon.ico">
	<link rel="stylesheet" type="text/css" href="/www/css/theme/black-dashboard.css">
	<link rel="stylesheet" type="text/css" href="/www/css/theme/nucleo-icons.css">
	<link rel="stylesheet" type="text/css" href="/www/css/components/header.css">
	<link rel="stylesheet" type="text/css" href="/www/css/profile/library.css">
	<script src="https://kit.fontawesome.com/e0f46f82c6.js"></script>
	<script type="text/javascript" src="/www/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="/www/js/components/header.js"></script>
	<script type="text/javascript" src="/www/js/profile/profile.js"></script>
</head>
<body>
	
	<!-- 
		제작자 : 전다빈
		since : 2022.06.29
		version : v.1.0
		
		헤더 제작 - 22.06.29
	-->
	<header>
		<div class="header-line"></div>
		<div class="header-logo">
			<img src="/www/img/logo.png" height="80px">
		</div>
		
		<ul class="main-nav">
			<li>
				<a class="nav-link active" href="/www/store/">
					<i class="tim-icons icon-cart"></i>
					<span class="main-nav-text">Store</span>
					<div class="indicator"></div>
				</a>
			</li>
			<li>
				<a class="nav-link active" href="/www/profile/library.nbs">
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
			<a href="/www/payment/pick.nbs"><i class="tim-icons icon-shape-star"></i></a>
		</div>
		
		<a href="/www/account/notice.nbs" id="noticeBtn"><i class="tim-icons icon-alert-circle-exc"></i></a>
	</header>
	
	<main>
		<div class="library">
		
			<div class="library--games">
<c:if test="${not empty LIST}">
	<c:forEach var="data" items="${LIST}">
				<div class="card" id="${data.appId}" >
					<img class="card-img-left" src="${data.img}">
					<div>
						<div class="game-title-info">
							<h4>${data.title}</h4>
							<span class="whenGame">${data.buydate} 구매</span>
						</div>
						<button type="button" class="btn btn-primary animation-on-hover goTOStoreBtn">상점 페이지</button>
		<c:if test="${data.diffDate lt 14}">
						<button type="button" class="btn btn-warning animation-on-hover refundBtn">환불 신청</button>
		</c:if>
					</div>
				</div>
	</c:forEach>		
</c:if>			
<c:if test="${empty LIST}">
				<div class="emptyLibrary">
					<i class="tim-icons icon-simple-remove"></i>
					<h4>아직 구매한 게임이 없어요!</h4>
				</div>
</c:if>
			</div>
		</div>
	</main>
	
</body>
</html>