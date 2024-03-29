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
	<script type="text/javascript" src="/www/js/bootstrap.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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
			<a href="/www/payment/payForm.nbs"><i class="tim-icons icon-basket-simple"></i></a>
			<a href="/www/payment/pick.nbs"><i class="tim-icons icon-shape-star"></i></a>
			<a href="/www/account/notice.nbs" id="noticeBtn"><i class="tim-icons icon-alert-circle-exc"></i></a>
		</div>
		
	</header>
	
	<!-- steam 웹사이트 참고 -->
	<main class="app-detail-main">
<c:if test="${not empty sVO}">
		<div class="app-detail-header">
			<h2>${sVO.title}</h2>
			<button type="button" class="btn btn-success animation-on-hover">커뮤니티 허브</button>
		</div>
		
		<div class="app-detail-body">
			<div class="game-media">
				<div>
					<video class="mainMedia" onloadstart="this.volume=0.5" controls>
						<source src="" type="video/mp4">
					</video>
					<img class="mainMedia" src="">
				</div>
				<div class="game-media-scroll">
	<c:forEach var="mv" items="${sVO.movie}">
					<div class="media movie">
						<img src="${mv.key}" class="${mv.value}">
						<span class="movie-play-btn">
							<i class="tim-icons icon-triangle-right-17"></i>
						</span>
						<span class="now-show" style="visibility:hidden;">
							<i class="tim-icons icon-minimal-up"></i>
						</span>
					</div>
	</c:forEach>
	<c:forEach var="sc" items="${sVO.screenshot}">
					<div class="media screenshot">
						<img src="${sc.key}" class="${sc.value} ">
						<span class="now-show" style="visibility:hidden;">
							<i class="tim-icons icon-minimal-up"></i>
						</span>
					</div>
	</c:forEach>
	
				</div>
			</div>
			
			<div class="game-info">
				<img class="thumbnail" src="${sVO.img}">
	<c:if test="${sVO.type eq 'dlc'}">
				<div class="fullgame">
					<p>플레이하려면 Steam 버전인 <a class="title-anchor" href="/www/store/app/?game=App_${sVO.fullgameId}">${sVO.fullgameTitle}</a> 기본 게임이 필요합니다.</p>
					<a class="img-anchor" href="/www/store/app/?game=App_${sVO.fullgameId}"><img src="${fullgameImg}"></a>
				</div>
	</c:if>
	<c:if test="${sVO.type eq 'game'}">
				<p class="shortDescription">${sVO.shortDescription}</p>
	</c:if>
				
				<div class="game-ect">
					<div class=" margin-bottom">
						<span class="label">모든 평가</span>
						<div class="content">
	<c:if test="${not empty sVO.reviewSummary}">
		<c:if test="${sVO.reviewSummary eq 'positive'}">
							<span class="positive">긍정적</span>
		</c:if>
		<c:if test="${sVO.reviewSummary eq 'mixed'}">
							<span class="mixed">복합적</span>
		</c:if>
		<c:if test="${sVO.reviewSummary eq 'negative'}">
							<span class="negative">부정적</span>
		</c:if>
	</c:if>
	<c:if test="${empty sVO.reviewSummary}">
							<span>사용자 평가 없음</span>
	</c:if>
						</div>
					</div>
					
					<div class=" margin-bottom">
						<span class="label">출시일:</span>
						<div class="content">
							<span class="released">${sVO.released}</span>
						</div>
					</div>
					
					<div>
						<span class="label">개발자:</span>
						<div class="content">
	<c:forEach var="dev" items="${sVO.developers}">
							<span class="company">${dev}</span>
	</c:forEach>
						</div>
					</div>
					<div class=" margin-bottom">
						<span class="label">배급사:</span>
						<div class="content">
	<c:forEach var="pub" items="${sVO.publishers}">
							<span class="company">${pub}</span>
	</c:forEach>
						</div>
					</div>
				</div>
				
				<div class="game-tag">
					<span>이 제품의 인기 태그:</span>
					<div>
	<c:forEach var="tag" items="${sVO.tags}">
						<button type="button">${tag}</button>
	</c:forEach>
						<button type="button">+</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="game-description">
			${sVO.detailedDescription}
		</div>
		
		<div class="game">
			<div class="card">
	<c:if test="${empty sVO.discount}">
				<h4>무료 게임</h4>
	</c:if>
	<c:if test="${not empty sVO.discount}">
				<h4>${sVO.packageTitle}</h4>
				<div>
		<c:if test="${sVO.discount ne '0'}">
					<span class="salePercent">-${sVO.discount}%</span>
					<div>
						<span class="oriPrice"><del>${sVO.price}</del></span>
						<span class="price">${sVO.discountPrice}</span>
					</div>
		</c:if>
		<c:if test="${sVO.discount eq '0'}">
					<span class="price">${sVO.price}</span>
		</c:if>
	
		<c:if test="${empty SID}">
					<button type="button" id="addToBasketBtn" class="btn btn-primary animation-on-hover basket">장바구니에 추가</button>
		</c:if>
		<c:if test="${not empty SID}">
			<c:if test="${haveGame eq 0}">
				<c:if test="${sVO.basketCount eq 0}">
					<button type="button" id="addToBasketBtn" class="btn btn-primary animation-on-hover basket">장바구니에 추가</button>
				</c:if>
				<c:if test="${sVO.basketCount eq 1}">
					<button type="button" id="goToBasketBtn" class="btn btn-primary animation-on-hover basket">장바구니로 가기</button>
				</c:if>
			</c:if>
			<c:if test="${haveGame eq 1}">
					<button type="button" class="btn btn-primary animation-on-hover basket" disabled>장바구니에 추가</button>
			</c:if>
		</c:if>
				</div>
	</c:if>
			</div>
	<c:if test="${empty SID}">
			<button type="button" id="addToPickBtn" class="btn btn-primary btn-round btn-icon animation-on-hover"><i class="tim-icons icon-shape-star"></i></button>
	</c:if>
	<c:if test="${not empty SID}">
		<c:if test="${haveGame eq 0}">
			<c:if test="${sVO.pickCount eq 0}">
			<button type="button" id="addToPickBtn" class="btn btn-primary btn-round btn-icon animation-on-hover"><i class="tim-icons icon-shape-star"></i></button>
			</c:if>
			<c:if test="${sVO.pickCount eq 1}">
			<button type="button" class="btn btn-primary btn-round btn-icon animation-on-hover" disabled><i class="tim-icons icon-shape-star"></i></button>
			</c:if>
		</c:if>
		<c:if test="${haveGame eq 1}">
			<button type="button" class="btn btn-primary btn-round btn-icon animation-on-hover" disabled><i class="tim-icons icon-shape-star"></i></button>
		</c:if>
	</c:if>
		</div>
</c:if>
	</main>
	
	<input type="hidden" id="gameId" name="game_id" value="${sVO.appId}">
	<input type="hidden" id="gameType" name="game_type" value="${sVO.type}">
<!-- 
<c:if test="${sVO.type eq 'dlc'}">
		<input type="hidden" id="type" name="type" value="${sVO.type}">
		<input type="hidden" id="fullgameId" name="fullgameId" value="${sVO.fullgameId}">
</c:if>		
 -->
	<form id="fullgameAndDlcForm" action="/www/payment/basket.nbs" method="post">
		<input type="hidden" id="together" name="together" >
	</form>	
	<form id="basketErrorForm" method="post">
		<input type="hidden" name="vw" >
	</form>	
</body>
</html>