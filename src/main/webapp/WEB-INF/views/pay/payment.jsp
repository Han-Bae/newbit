<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<!-- 
		결제 첫 페이지
		
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
	</header>
	<!-- 여기까지 -->
	<main>
		<div class="store-top pay-top">
<c:if test="${not empty stat}">
			<input type="hidden" id=stat value="${stat}">
</c:if>
			<h4>선물 보내기</h4><h5>▶</h5>
			<h4>메모작성</h4><h5>▶</h5>
			<h4>결제 정보</h4><h5>▶</h5>
			<h4>확인 및 구매</h4>
		</div>
		<hr class="payStat">
		<div class="payMain">
			<!-- 친구 검색 -->
			<div class="row">
				<div style="padding-top: 20px;"><h2 style="margin-left: 20px; margin-bottom: 10px;">친구 선택</h2></div>
				<div class="findF">
					<form method="post" name="search">
						<table class="pull-right">
							<tr>
								<!-- 카테고리<td><select class="form-control" name="searchField">
										<option value="0">선택</option>
										<option value="bbsTitle">게임명</option>
										<option value="userID">개발사</option>
								</select></td> -->
								<td><input type="text" class="form-control" id="find"
									placeholder="검색어 입력" name="searchText" maxlength="100"></td>
								<!-- 검색 버튼 <td><button type="submit" class="btn btn-success animation-on-hover">검색</button></td> -->
							</tr>
		
						</table>
					</form>
				</div>
				<!-- 친구 정보 -->
			<div class="friend-main">
				<form method="POST" action="/www/payment/payFormMemo.nbs"
			  			name="frm" id="frm" class="frm">
			  	<input type="hidden" id="nameList" name="nameList">
			  	<!-- 친구 내용 -->
			<c:forEach var="friend" items="${nameList}">
				<div class="card">
					<div>    
						<input type="checkbox" id="${friend}" name="fid" value="${friend}"/>
	    				<label for="${friend}">
							<span></span>
	    				</label>
					</div>
    				<label for="${friend}" class="notLabel">
						<img class="card-img-left" src="/www/img/logo.png" width="120px" height="50px">
	    				<h4>${friend}</h4>
    				</label>
				</div>
			</c:forEach>			
				</form>	
			</div>
		</div>
			<div class="conti">
				<button type="button" id="cbtn" class="btn btn-success animation-on-hover">Continue</button>
			</div>
		</div>
	</main>
</body>
</html>