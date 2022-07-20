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
				<a class="nav-link active" href="/www/store/games.nbs">
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
			<a href="/www/payment/payForm.nbs"><i class="tim-icons icon-basket-simple"></i></a>
			<a href="/www/payment/pick.nbs"><i class="tim-icons icon-shape-star"></i></a>
		</div>
	</header>
	
	<!-- steam 웹사이트 참고 -->
	<main class="app-detail-main">
		<div class="app-detail-loot">
			모든 게임
			<i class="tim-icons icon-minimal-right"></i>
			액션 게임
			<i class="tim-icons icon-minimal-right"></i>
			Monster Hunter 프랜차이즈
			<i class="tim-icons icon-minimal-right"></i>
			MONSTER HUNTER RISE
			<i class="tim-icons icon-minimal-right"></i>
			DLC
			<i class="tim-icons icon-minimal-right"></i>
			MONSTER HUNTER RISE: SUNBREAK
		</div>
		
		<div class="app-detail-header">
			<h2>${param.game}</h2>
			<button type="button" class="btn btn-success animation-on-hover">커뮤니티 허브</button>
		</div>
		
		<div class="app-detail-body">
			<div class="game-media">
				<img id="mainMedia" src="/www/img/pay/naverpay.png">		
				<div class="game-media-scroll">
					<img src="https://cdn.akamai.steamstatic.com/steam/apps/1880360/header.jpg?t=1656626643">
					<img src="https://cdn.akamai.steamstatic.com/steam/apps/1880360/header.jpg?t=1656626643">
					<img src="https://cdn.akamai.steamstatic.com/steam/apps/1880360/header.jpg?t=1656626643">
					<img src="https://cdn.akamai.steamstatic.com/steam/apps/1880360/header.jpg?t=1656626643">
					<img src="https://cdn.akamai.steamstatic.com/steam/apps/1880360/header.jpg?t=1656626643">
					<img src="https://cdn.akamai.steamstatic.com/steam/apps/1880360/header.jpg?t=1656626643">
					<img src="https://cdn.akamai.steamstatic.com/steam/apps/1880360/header.jpg?t=1656626643">
					<img src="https://cdn.akamai.steamstatic.com/steam/apps/1880360/header.jpg?t=1656626643">
				</div>
			</div>
			
			<div class="game-info">
				<img class="thumbnail" src="https://cdn.akamai.steamstatic.com/steam/apps/1880360/header.jpg?t=1656626643">
				<div class="fullgame">
					<p>플레이하려면 Steam 버전인 <a class="title-anchor" href="">MONSTER HUNTER RISE</a> 기본 게임이 필요합니다.</p>
					<a class="img-anchor" href=""><img src="https://cdn.akamai.steamstatic.com/steam/apps/1446780/header.jpg?t=1656665891"></a>
				</div>
				
				<div class="game-ect">
					<div class=" margin-bottom">
						<span class="label">모든 평가</span>
						<div class="content">
							<span class="gb">복합적</span>
							<span>(2,197)</span>
						</div>
					</div>
					
					<div class=" margin-bottom">
						<span class="label">출시일:</span>
						<div class="content">
							<span class="released">2022년 6월 30일</span>
						</div>
					</div>
					
					<div>
						<span class="label">개발자:</span>
						<div class="content">
							<span class="company">CAPCOM Co., Ltd.</span>
						</div>
					</div>
					<div class=" margin-bottom">
						<span class="label">배급사:</span>
						<div class="content">
							<span class="company">CAPCOM Co., Ltd.</span>
						</div>
					</div>
				</div>
				
				<div class="game-tag">
					<span>이 제품의 인기 태그:</span>
					<div>
						<button type="button">액션</button>
						<button type="button">멀티플레이어</button>
						<button type="button">협동</button>
						<button type="button">캐릭터 커스터마이즈</button>
						<button type="button">+</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="game">
			<div class="card">
				<h4>MONSTER HUNTER RISE: SUNBREAK 구매</h4>
				<div>
					<span class="price">48,400 원</span>
					<span id="addToBasketBtn" class="add-to-basket">장바구니에 추가</span>
				</div>
			</div>
		</div>
	</main>
	
</body>
</html>