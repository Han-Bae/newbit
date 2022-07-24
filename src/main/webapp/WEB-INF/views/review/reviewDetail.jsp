<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>newbit_steam</title>
<link rel="icon" type="image/png" sizes="32x32" href="/www/img/favicon/favicon.ico">
<!-- CSS Files -->
<link rel="stylesheet" type="text/css" href="/www/css/theme/black-dashboard.css">
<link rel="stylesheet" type="text/css" href="/www/css/theme/nucleo-icons.css">
<link rel="stylesheet" type="text/css" href="/www/css/components/header.css">
<link rel="stylesheet" type="text/css" href="/www/css/store/store.css">
<script src="https://kit.fontawesome.com/e0f46f82c6.js"></script>
<script type="text/javascript" src="/www/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/www/js/components/header.js"></script>
<script type="text/javascript" src="/www/js/review/reviewDetail.js"></script>
<style>


</style>
</head>
<body class="store-main-body">
<input type="hidden" id="ano" value="${ANO}">
<input type="hidden" id="rno" value="${RNO}">


	<header>
		<div class="header-line"></div>
		<div class="header-logo">
			<img src="/www/img/logo.png" height="80px">
		</div>
		
		<ul class="main-nav">
			<li>
				<a class="nav-link active" href="/www/store/">
					<i class="tim-icons icon-cart main-nav-icon"></i>
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
			<a href="#"><i class="tim-icons icon-single-02"></i></a>
</c:if>
			<a href="/www/payment/basket.nbs"><i class="tim-icons icon-basket-simple"></i></a>
			<a href="#"><i class="tim-icons icon-shape-star"></i></a>
		</div>
	</header>
<form method="POST" id="frm" name="frm" enctype="multipart/form-data">
<input type="hidden" name="game_no" id="game_no" />
<input type="hidden" name="dreviewNo" id="dreviewNo"/>


<!-- 리뷰페이지 reviewMain -->

<div class="container">

	<section>
		<div style="margin-top: 20px;">
			<h3>최근 플레이(구매)한 게임의 포럼</h3>
		</div>
		<c:forEach var="data" items="${GLIST}">
		<input type="button" class="btn btn-primary btn-lg animation-on-hover" id="${data.appId}" name="game" value="${data.title}">
		</c:forEach>
	</section>
		
		<div>
			<ul class="nav justify-content-center">
				<li class="nav-item"><a class="nav-link active" href="#review" id="reviewBtn">평가</a></li>
				<li class="nav-item"><a class="nav-link" href="#screenShot" id="ssBtn">스크린샷</a></li>
				<li class="nav-item"><a class="nav-link" href="#live" id="liveBtn">방송</a></li>
			</ul>
		</div>
		
		
		<!-- 1. 리뷰 탭 -->
		<div id="review">
			
		<%-- 리뷰 상세내용 페이지  --%>
		        
		        <!-- 자기가 등록한 글을 클릭했을때만 노출 -->
		        <div class="card">
		        <div>
			        <div id="dAccount"></div>
			      <!--   <div id="btnModify" display='none'>수정</div> -->
			        <div id="btnDelete" display='none'>삭제</div>
		        </div>
		        
		        <div><span id="reviewYnGood"></span>명이 평가가 유용하다고 함</div>
		        <div><span id="reviewYnBad"></span>명이 평가가 유용하지 않다고 함</div>
		        
		        <div>
					<div>
						<img id="goodImg" src="/www/img/good.png" display='none'>
						<img id="badImg" src="/www/img/bad.png" display='none'>
					</div>
		        </div>
				<div>
					<div id="dRdate"></div>
				</div>
		        <div>
		        	<div id="dBody"></div>
		        </div>
		        
		        <!-- 이 평가에 대한 평가 -->
		        <c:if test="${not empty SID}">
		        <div id="divReview" display='none'>
		        	<div>이 평가가 유용한가요?</div>
						<div>
							<input type="radio" name="reviewYN" id="reviewY" value="Y"><label for="reviewY"> 네</label>
							<input type="radio" name="reviewYN" id="reviewN" value="N"><label for="reviewN"> 아니오</label>
							<input type="button" class="btn btn-info btn-sm" id="ynSaveBtn" value="저장">
							
						</div>
				</c:if>

		        </div>
		        </div>
		        <div>
					<input type="button" class="btn btn-deflaut animation-on-hover" id="confirm_btn" name="" value="확  인">
		        </div>
		    </div>
		    
		    
		    
		    
		    


	
<!--   Core JS Files   -->
<script src="/www/js/theme/core/jquery.min.js" type="text/javascript"></script>
<script src="/www/js/theme/core/popper.min.js" type="text/javascript"></script>
<script src="/www/js/theme/core/bootstrap.min.js" type="text/javascript"></script>
<script src="/www/js/theme/plugins/perfect-scrollbar.jquery.min.js"></script>
<!--  Google Maps Plugin    -->
<script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
<!-- Chartist JS -->
<script src="/www/js/theme/plugins/chartjs.min.js"></script>
<!--  Notifications Plugin    -->
<script src="/www/js/theme/plugins/bootstrap-notify.js"></script>
<!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
<script src="/www/js/theme/black-dashboard.js?v=1.0.0" type="text/javascript"></script>


</form>	

</body>
</html>