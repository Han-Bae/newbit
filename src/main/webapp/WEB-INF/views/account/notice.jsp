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
<title>알림 페이지</title>
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
	<script type="text/javascript" src="/www/js/notice.js"></script>
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
			<a href="/www/account/notice.nbs" id="noticeBtn"><i class="tim-icons icon-alert-circle-exc"></i></a>
		</div>
	</header>
	<!-- 여기까지 -->
	<main>	
		<div class="store-top pay-top">
		</div>
	<form method="post">
		<input type="hidden" name="no" id="no">
		<input type="hidden" name="title" id="title">
		<input type="hidden" name="body" id="body">
		<input type="hidden" name="ischeck" id="ischeck">
	</form>
		<hr class="payStat">
		<h1 style="text-align:center">알림창</h1>	
	    <table align = "center" style="color:white">
	    <tr id="main" align="center" >
			<th class="noticeNo">글번호</th>
			<th>제목</th>
	    </tr>
 <c:forEach var="notice" items="${NOTICELIST.nVOList}">
	 <c:if test="${notice.ischeck eq 'Y'}">
		<!-- 첫번째 줄 시작 -->
		<tr id="${notice.no}" style="color:#818182">
		    <td class="no">${notice.no}</td>
		    <td class="title">${notice.title}</td>
		    <input type="hidden" name="body" value="${notice.body}">
		    <input type="hidden" name="check" value="${notice.ischeck}">
		</tr>
	</c:if>
	 <c:if test="${notice.ischeck eq 'N'}">
		<tr id="${notice.no}">
		    <td class="no">${notice.no}</td>
		    <td class="title">${notice.title}</td>
		    <input type="hidden" name="body" value="${notice.body}">
		    <input type="hidden" name="check" value="${notice.ischeck}">
		</tr><!-- 첫번째 줄 끝 -->
	</c:if>
</c:forEach>
	    </table>
	</main>
</body>
</html>