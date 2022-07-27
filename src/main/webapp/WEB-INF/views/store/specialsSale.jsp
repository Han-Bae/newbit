<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 

 * 
 * 	@author 전다빈
 * 	@version v.1.0
 * 
 * 			담당자 : 전다빈
 * 			


 -->

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
			<button type="button" id="favoritesBtn" class="btn btn-warning animation-on-hover">Favorites</button>
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
			
			<form id="searchForm" method="GET" action="/www/store/search/" class="store-search">
				<input type="text" id="storeInput" class="form-control" name="term" placeholder="Search">
				<button type="button" id="searchBtn" class="btn btn-primary animation-on-hover"><i class="tim-icons icon-zoom-split"></i></button>
			</form>
		</div>
		
		<hr>
		
		<div class="store-games">
			<div class="store-games__tab">
				<button type="button" class="btn" id="TopSellersBtn">최고 인기</button>
				<button type="button" class="btn" id="newTopSellerBtn">신규 인기</button>
				<button type="button" class="btn" id="specialsSaleBtn">현재 할인</button>
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
									<div class="gamePrice">
										<span class="originalPrice"><del>${medium.price}</del></span>
										<span class="nowPrice">${medium.discountPrice}</span>
									</div>
	</c:if>
								</div>
							</div>
						</div>
</c:forEach>
					</div>
				</div>
					
				<div class="today-sale">
<c:forEach var="dailydeal" items="${dailydeal}">
					<div id="${dailydeal.appId}" class="game card card-nav-tabs text-center">
						<img class="card-img-top" src="${dailydeal.img}">
						<div class="card-body">
							<div class="prices">
								<span class="discount">${dailydeal.discount}</span>
								<div>
									<span class="original-price"><del>${dailydeal.price}</del></span>
									<span class="now-price">${dailydeal.discountPrice}</span>
								</div>
							</div>
							
							<div class="whenSale">
								<span class="text">오늘의 할인!</span>
							</div>
						</div>
					</div>
</c:forEach>
					
				</div>
								
			</div>
		</div>
	</main>
	
	<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>