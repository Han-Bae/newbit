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
			<a href="#"><i class="tim-icons icon-basket-simple"></i></a>
			<a href="#"><i class="tim-icons icon-shape-star"></i></a>
		</div>
	</header>
	
	<!-- steam 웹사이트 참고 -->
	<main>
		<div class="store-top">
			<button type="button" class="btn btn-warning animation-on-hover">Favorites</button>
			<button type="button" class="btn btn-primary animation-on-hover">All Games</button>
			<button type="button" id="storeCategoryBtn" class="btn btn-info animation-on-hover">Category</button>
			<form method="GET" action="#" class="store-search">
				<input type="text" class="form-control store-input" placeholder="Search">
				<button class="btn btn-primary animation-on-hover"><i class="tim-icons icon-zoom-split"></i></button>
			</form>
		</div>
		
		<hr>
		
		<div class="store-games">
			<div class="store-games__tab">
				<button type="button" class="btn">최고 인기</button>
				<button type="button" class="btn">신규 인기</button>
				<button type="button" class="btn">현재 할인</button>
			</div>
			
			
			<div class="store-games-main">
				<div class="games-sort">
					<label for="gameSort">정렬기준</label>
					<select name="gamesSort" class="form-control store-select" id="gameSort">
						<option value="">연관성</option>
						<option value="">출시일</option>
						<option value="">이름</option>
						<option value="">낮은 가격</option>
						<option value="">높은 가격</option>
						<option value="">사용자 평가</option>
					</select>
				</div>
				
				<div class="store-games--games">
				
					<div class="games">
					
						<div class="card">
							<img class="card-img-left" src="/www/img/logo.png" width="120px" height="50px">
							<div>
								<h4>Game Title</h4>
								<div class="game-title-info">
									<span class="whenGame">2022.07.04</span>
									<i class="tim-icons icon-minimal-up"></i>
									<span class="howSale">-49%</span>
								</div>
								<div class="gamePrice">
									<span class="originalPrice"><del>₩ 999,990</del></span>
									<span class="salePrice">₩ 1,990</span>
								</div>
							</div>
						</div>
						
						<div class="card">
							<img class="card-img-left" src="/www/img/logo.png" width="120px" height="50px">
							<div>
								<h4>Game Title</h4>
								<div class="game-title-info">
									<span class="whenGame">2022.07.04</span>
									<i class="tim-icons icon-minimal-up"></i>
									<span class="howSale">-49%</span>
								</div>
								<div class="gamePrice">
									<span class="originalPrice"><del>₩ 999,990</del></span>
									<span class="salePrice">₩ 1,990</span>
								</div>
							</div>
						</div>
						
						<div class="card">
							<img class="card-img-left" src="/www/img/logo.png" width="120px" height="50px">
							<div>
								<h4>Game Title</h4>
								<div class="game-title-info">
									<span class="whenGame">2022.07.04</span>
									<i class="tim-icons icon-minimal-up"></i>
									<span class="howSale">-49%</span>
								</div>
								<div class="gamePrice">
									<span class="originalPrice"><del>₩ 999,990</del></span>
									<span class="salePrice">₩ 1,990</span>
								</div>
							</div>
						</div>
						
					</div>
					
					<div class="games-filter">
					
						<div class="card card-nav-tabs text-center priceFilter">
							<div class="card-header chard-header-primary">가격으로 검색하기</div>
							<div class="card-body">
								<input type="range" step="1" min="0" max="13" value="13">
								<span class="games-filter--range">모든 가격</span>
								<div class="boundary"></div>
								
								
							</div>
						</div>
						
						<div class="card tagFilter">
							<div class="card-header chard-header-primary">태그로 검색하기</div>
							<div class="card-body">
								<div class="form-check">
									<label class="form-check-label">
										<input type="checkbox" class="form-check-input" value="">
										어드벤처
										<span class="form-check-sign">
											<span class="check"></span>
										</span>
									</label>
								</div>
								
								<div class="form-check">
									<label class="form-check-label">
										<input type="checkbox" class="form-check-input" value="">
										RPG
										<span class="form-check-sign">
											<span class="check"></span>
										</span>
									</label>
								</div>
								
								<div class="form-check">
									<label class="form-check-label">
										<input type="checkbox" class="form-check-input" value="">
										시뮬레이션
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
	
</body>
</html>