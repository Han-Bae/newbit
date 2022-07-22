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
	<link rel="stylesheet" type="text/css" href="/www/css/store/store.css">
	<script src="https://kit.fontawesome.com/e0f46f82c6.js"></script>
	<script type="text/javascript" src="/www/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="/www/js/components/header.js"></script>
	<script type="text/javascript" src="/www/js/store/store.js"></script>
</head>
<body class="store-main-body">
	
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
				<a class="nav-link active" href="/www/review/reviewMain.nbs">
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
			<a href="/www/info/ProfileUpdate.nbs"><i class="tim-icons icon-single-02"></i></a>
</c:if>
			<a href="/www/payment/basket.nbs"><i class="tim-icons icon-basket-simple"></i></a>
			<a href="/www/payment/pick.nbs"><i class="tim-icons icon-shape-star"></i></a>
		</div>
		
		<a href="/www/account/notice.nbs" id="noticeBtn"><i class="tim-icons icon-alert-circle-exc"></i></a>
	</header>
	
	<!-- steam 웹사이트 참고 -->
	<main>
		<div class="store-top">
			<button type="button" class="btn btn-warning animation-on-hover">Favorites</button>
			<button type="button" id="allGameBtn" class="btn btn-primary animation-on-hover">All Games</button>
			<button type="button" id="storeCategoryBtn" class="btn btn-info animation-on-hover">Category</button>
			<form method="GET" action="#" class="store-search">
				<input type="text" class="form-control store-input" placeholder="Search">
				<button class="btn btn-primary animation-on-hover"><i class="tim-icons icon-zoom-split"></i></button>
			</form>
		</div>
		
		<hr>
		
		<div class="store-games">
			<div class="store-games__tab">
				<button type="button" class="btn" id="TopSellersBtn">최고 인기</button>
				<button type="button" class="btn" id="newTopSellerBtn">신규 인기</button>
				<button type="button" class="btn">현재 할인</button>
			</div>
			
			
			<div class="store-new">
				
				<div class="store-new--games">
				
					<div class="games">
<c:forEach var="medium" items="${medium}">					
						<div class="card game" id="${medium.appId}">
							<img class="card-img-left" src="${medium.img}">
							<div>
								<div class="new-title-info">
									<h4 class="new-game-h4">${medium.title}</h4>
									<span class="new-game-tag">${medium.type}</span>
								</div>
								
								<div class="gameDiscount">
	<c:if test="${not empty medium.discount}">
									<span class="howSale">${medium.discount}</span>
	</c:if>
									<div class="gamePrice">
	<c:if test="${empty medium.discountPrice}">
										<span class="nowPrice">${medium.price}</span>
	</c:if>
	<c:if test="${not empty medium.discountPrice}">
										<span class="originalPrice"><del>${medium.price}</del></span>
										<span class="nowPrice">${medium.discountPrice}</span>
	</c:if>
									</div>
								</div>
							</div>
						</div>
</c:forEach>
					</div>
				</div>
					
				<div class="new-low-price-games">
				
					<div class="card card-nav-tabs text-center">
						<div class="card-header">₩ 10,000 이하</div>
						<div class="new-low-price-game">
<c:forEach var="mini10000" items="${mini10000}">
							<div class="card game">
								<img src="${mini10000.img}">
								<div>
	<c:if test="${not empty mini10000.discount}">
									<span class="howSale">${mini10000.discount}</span>
	</c:if>
	<c:if test="${empty mini10000.discountPrice}">
									<span class="nowPrice">${mini10000.price}</span>
	</c:if>
	<c:if test="${not empty mini10000.discountPrice}">
									<span class="originalPrice"><del>${mini10000.price}</del></span>
									<span class="nowPrice">${mini10000.discountPrice}</span>
	</c:if>
								</div>
							</div>
</c:forEach>
						</div>
					</div>
					
					<div class="card card-nav-tabs text-center">
						<div class="card-header">₩ 5,000 이하</div>
						<div class="new-low-price-game">
						
<c:forEach var="mini5000" items="${mini5000}">
							<div class="card game">
								<img src="${mini5000.img}">
								<div>
	<c:if test="${not empty mini5000.discount}">
									<span class="howSale">${mini5000.discount}</span>
	</c:if>
									<div class="gamePrice">
		<c:if test="${empty mini5000.discountPrice}">
										<span class="salePrice">${mini5000.price}</span>
	</c:if>
		<c:if test="${not empty mini5000.discountPrice}">
										<span class="originalPrice"><del>${mini5000.price}</del></span>
										<span class="salePrice">${mini5000.discountPrice}</span>
	</c:if>
									</div>
								</div>
							</div>
</c:forEach>
						</div>
					</div>
					
				</div>
				
			</div>
		</div>
	</main>
	
</body>
</html>