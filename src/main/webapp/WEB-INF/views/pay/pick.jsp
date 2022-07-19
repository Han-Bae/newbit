<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<!-- 
		찜목록
		
		제작자 : 김태현
		since : 2022.07.19
		version : v.1.0
	-->
<html>
<head>
<meta charset="UTF-8">
<title>Newbit 찜 목록</title>
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
	<script type="text/javascript" src="/www/js/pay/pick.js"></script>
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
			<a href="/www/payment/pick.nbs"><i class="tim-icons icon-shape-star"></i></a>
		</div>
	</header>
	<!-- 여기까지 -->
	<main>
		<div class="store-top pay-top">
<!-- 전송할 데이터 -->
<form method="POST" action="/www/payment/payFormCheck.nbs"
		name="frm_info" id="frm_info" class="frm">
	<input type="hidden" id="totalPrice" name="totalPrice">
</form>
<!-- waring버튼 id, 라벨 input for/name 고치기 -->
		</div>
		<hr class="payStat">
		<div class="payMain" style="flex-direction: column; align-items: center;">
			<div class="row basket_row">
				<div class="store-games" style="display:contents">
					<div class="store-games-main">
						<div class="store-games--games">
							<div class="games">
				<c:forEach var="pick" items="${gameIdList}">
								<!-- 개별 게임 나중에 label for, input name ${game_id} -->
								<div class="basket-game">
									<div class="labelDiv">
										<div class="card" id="${pick}">
											<img class="card-img-left" src="/www/img/logo.png" width="120px" height="50px">
											<div class="card-width">
												<h4 style="width: 160px;">Game_${pick}</h4>
												<div class="game-title-info" style="width: 100px">
													<span class="whenGame">123</span>
													<i class="tim-icons icon-minimal-up positive"></i>
												</div>
												<div class="gameDiscount">
													<span class="howSale">-49%</span>
													<div class="gamePrice">
														<span class="originalPrice"><del>999990</del></span>
														<span class="salePrice">2000</span>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="btnDiv">
						<c:forEach var="basket" items="${basketList}">
							<c:if test="${basket == pick}">
										<input type="hidden" name="yesBasket" value="yes">
							</c:if>
						</c:forEach>
										<button type="button" class="btn btn-warning animation-on-hover del_game">삭제</button>
									</div>
								</div>
								<!-- 개별게임 끝 -->
				</c:forEach>													
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row basket_row">
				<div class="gamePay">
					<button type="button" id="goBasket" onclick='goBasket()' class="btn btn-success animation-on-hover">장바구니 가기</button>
				</div>
			</div>
		</div>
	</main>
</body>
</html>