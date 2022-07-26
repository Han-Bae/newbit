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
			<a href="/www/account/notice.nbs" id="noticeBtn"><i class="tim-icons icon-alert-circle-exc"></i></a>
		</div>
		
	</header>
	
	<!-- steam 웹사이트 참고 -->
	<main>
		<div class="store-top">
			<button type="button" class="btn btn-warning animation-on-hover">Favorites</button>
			<button type="button" id="allGameBtn" class="btn btn-primary animation-on-hover">All Games</button>
			<button type="button" id="storeCategoryBtn" class="btn btn-info animation-on-hover">Category</button>
						
			<div class="selectCategoryTab">
				<a href="/www/store/categories.nbs?tag=action">
					<ion-icon name="eyedrop-outline"></ion-icon>
					<span>Action</span>
				</a>
				<a href="/www/store/categories.nbs?tag=rpg">
					<ion-icon name="color-wand-outline"></ion-icon>
					<span>RPG</span>
				</a>
				<a href="/www/store/categories.nbs?tag=strategy">
					<ion-icon name="bulb-outline"></ion-icon>
					<span>Strategy</span>
				</a>
				<a href="/www/store/categories.nbs?tag=adventure">
					<ion-icon name="rocket-outline"></ion-icon>
					<span>Adventure</span>
				</a>
				<a href="/www/store/categories.nbs?tag=simulation">
					<ion-icon name="build-outline"></ion-icon>
					<span>Simulation</span>
				</a>
				<a href="/www/store/categories.nbs?tag=sports">
					<ion-icon name="football-outline"></ion-icon>
					<span>Sports</span>
				</a>
			</div>
			
			<form method="GET" action="" class="store-search">
				<input type="text" id="storeSearch" class="form-control" placeholder="Search">
				<button class="btn btn-primary animation-on-hover"><i class="tim-icons icon-zoom-split"></i></button>
			</form>
		</div>
		
		<hr>
		
		<div class="store-games">
			<div class="store-games__tab">
				<button type="button" class="btn" id="TopSellersBtn">최고 인기</button>
				<button type="button" class="btn" id="newTopSellerBtn">신규 인기</button>
				<button type="button" class="btn" id="specialsSaleBtn">현재 할인</button>
			</div>
			
			
			<div class="store-games-main">
				<div class="games-sort">
					<label for="gameSort">정렬기준</label>
					<select name="gamesSort" class="form-control store-select" id="gameSort">
<c:choose>
	<c:when test="${empty sortSelect}">
						<option value="" selected>연관성</option>
						<option value="Released_DESC">출시일</option>
						<option value="Name_ASC">이름</option>
						<option value="Price_ASC">낮은 가격</option>
						<option value="Price_DESC">높은 가격</option>
						<option value="Reviews_DESC">사용자 평가</option>
	</c:when>
	<c:when test="${sortSelect eq 'Released_DESC'}">
						<option value="">연관성</option>
						<option value="Released_DESC" selected>출시일</option>
						<option value="Name_ASC">이름</option>
						<option value="Price_ASC">낮은 가격</option>
						<option value="Price_DESC">높은 가격</option>
						<option value="Reviews_DESC">사용자 평가</option>
	</c:when>
	<c:when test="${sortSelect eq 'Name_ASC'}">
						<option value="">연관성</option>
						<option value="Released_DESC">출시일</option>
						<option value="Name_ASC" selected>이름</option>
						<option value="Price_ASC">낮은 가격</option>
						<option value="Price_DESC">높은 가격</option>
						<option value="Reviews_DESC">사용자 평가</option>
	</c:when>
	<c:when test="${sortSelect eq 'Price_ASC'}">
						<option value="">연관성</option>
						<option value="Released_DESC">출시일</option>
						<option value="Name_ASC">이름</option>
						<option value="Price_ASC" selected>낮은 가격</option>
						<option value="Price_DESC">높은 가격</option>
						<option value="Reviews_DESC">사용자 평가</option>
	</c:when>
	<c:when test="${sortSelect eq 'Price_DESC'}">
						<option value="">연관성</option>
						<option value="Released_DESC">출시일</option>
						<option value="Name_ASC">이름</option>
						<option value="Price_ASC">낮은 가격</option>
						<option value="Price_DESC" selected>높은 가격</option>
						<option value="Reviews_DESC">사용자 평가</option>
	</c:when>
	<c:when test="${sortSelect eq 'Reviews_DESC'}">
						<option value="">연관성</option>
						<option value="Released_DESC">출시일</option>
						<option value="Name_ASC">이름</option>
						<option value="Price_ASC">낮은 가격</option>
						<option value="Price_DESC">높은 가격</option>
						<option value="Reviews_DESC" selected>사용자 평가</option>
	</c:when>
</c:choose>
					</select>
				</div>
				
				<div class="store-games--games">
				
					<div class="games">
					
<c:forEach var="data" items="${LIST}">
						<div class="card game" id="${data.appId}">
							<img class="card-img-left" src="${data.img}">
							<div>
								<h4>${data.title}</h4>
								<div class="game-title-info">
									<span class="whenGame">${data.released}</span>
	<c:if test="${data.reviewSummary eq 'positive'}">
									<i class="tim-icons icon-minimal-up positive"></i>
	</c:if>
	<c:if test="${data.reviewSummary eq 'mixed'}">
									<i class="tim-icons icon-simple-delete mixed"></i>
	</c:if>
	<c:if test="${data.reviewSummary eq 'negative'}">
									<i class="tim-icons icon-minimal-down negative"></i>
	</c:if>
								</div>
								
								<div class="gameDiscount">
	<c:if test="${not empty data.discount}">
									<span class="howSale">${data.discount}</span>
	</c:if>
									<div class="gamePrice">
	<c:if test="${empty data.discountPrice}">
										<span class="nowPrice">${data.price}</span>
	</c:if>
	<c:if test="${not empty data.discountPrice}">
										<span class="originalPrice"><del>${data.price}</del></span>
										<span class="nowPrice">${data.discountPrice}</span>
	</c:if>
									</div>
								</div>
							</div>
						</div>
</c:forEach>
						
					</div>
					
					<div class="games-filter">
					
						<div class="card card-nav-tabs text-center priceFilter">
							<div class="card-header">가격으로 검색하기</div>
							<div class="card-body">
<c:if test="${not empty maxPrice}">
								<input id="range" type="range" step="5000" min="0" max="65000" value="${maxPrice}">
	<c:choose>
		<c:when test="${maxPrice eq '65000'}">
								<span id="rangeValue" class="games-filter--range">모든 가격</span>
		</c:when>
		<c:when test="${maxPrice eq '60000'}">
								<span id="rangeValue" class="games-filter--range">₩ 60,000 이하</span>
		</c:when>
		<c:when test="${maxPrice eq '55000'}">
								<span id="rangeValue" class="games-filter--range">₩ 55,000 이하</span>
		</c:when>
		<c:when test="${maxPrice eq '50000'}">
								<span id="rangeValue" class="games-filter--range">₩ 50,000 이하</span>
		</c:when>
		<c:when test="${maxPrice eq '45000'}">
								<span id="rangeValue" class="games-filter--range">₩ 45,000 이하</span>
		</c:when>
		<c:when test="${maxPrice eq '40000'}">
								<span id="rangeValue" class="games-filter--range">₩ 40,000 이하</span>
		</c:when>
		<c:when test="${maxPrice eq '35000'}">
								<span id="rangeValue" class="games-filter--range">₩ 35,000 이하</span>
		</c:when>
		<c:when test="${maxPrice eq '30000'}">
								<span id="rangeValue" class="games-filter--range">₩ 30,000 이하</span>
		</c:when>
		<c:when test="${maxPrice eq '25000'}">
								<span id="rangeValue" class="games-filter--range">₩ 25,000 이하</span>
		</c:when>
		<c:when test="${maxPrice eq '20000'}">
								<span id="rangeValue" class="games-filter--range">₩ 20,000 이하</span>
		</c:when>
		<c:when test="${maxPrice eq '15000'}">
								<span id="rangeValue" class="games-filter--range">₩ 15,000 이하</span>
		</c:when>
		<c:when test="${maxPrice eq '10000'}">
								<span id="rangeValue" class="games-filter--range">₩ 10,000 이하</span>
		</c:when>
		<c:when test="${maxPrice eq '5000'}">
								<span id="rangeValue" class="games-filter--range">₩ 5,000 이하</span>
		</c:when>
		<c:when test="${maxPrice eq '0'}">
								<span id="rangeValue" class="games-filter--range">무료</span>
		</c:when>
	</c:choose>
</c:if>
<c:if test="${empty maxPrice}">
								<input id="range" type="range" step="5000" min="0" max="65000" value="65000">
								<span id="rangeValue" class="games-filter--range">모든 가격</span>
</c:if>
								<div class="boundary"></div>
								
								<div class="form-check">
									<label class="form-check-label">
<c:if test="${empty specials}">
										<input id="specialsSale" type="checkbox" class="form-check-input" value="1">
</c:if>
<c:if test="${not empty specials}">
										<input id="specialsSale" type="checkbox" class="form-check-input" value="1" checked>
</c:if>
										특별 할인
										<span class="form-check-sign">
											<span class="check"></span>
										</span>
									</label>
								</div>
								
							</div>
						</div>
						
					</div>
					
				</div>
			</div>
		</div>
	</main>
	
	<input type="hidden" id="sort" name="sort" value="${sortSelect}">
	
	<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>