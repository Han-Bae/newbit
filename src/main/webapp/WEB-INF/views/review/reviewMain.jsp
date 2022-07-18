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
<script type="text/javascript" src="/www/js/review/reviewMain.js"></script>
<style>
/* 
* {
	padding: 0;
	margin: 0;
}

body {
	background-color: #1b2838;
	color: #c5c3c0;
}

.back-top0 {
	background: #0d131b;
}

.back-top {
	width: 940px;
	margin: 0px auto;
}

.container {
	max-width: 940px;
	margin: 0 auto;
	box-sizing: border-box;
	height: 100vh;
	display: flex;
	flex-direction: column;
	justify-content: normal;
	align-items: center;
}

header {
	align-items: center;
	width:940px;
	display: flex;
	justify-content: space-between;
	padding: 1.2rem 0;
}

header>p {
	font-size: 1.5rem;
	font-weight: 700;
}

header>ul {
	display: flex;
}

header>ul>li {
	list-style-type: none;
	margin-left: 1.5rem;
}

header>ul>li>a {
	text-decoration: none;
	font-size: 1.2rem;
	font-weight: 800;
	padding-bottom: 5px;
	color: #c5c3c0;
}

header>ul>li:first-child>a {
	border-bottom: 5px solid white;
}
header>ul>li>a:hover {
	border-bottom: 5px solid gray;
}

@media screen and (max-width:940px){
	header {
		flex-direction: column;
	}
}

section {
	text-align: left;
}

section>p {
	margin-bottom: 1rem;
}

section>p:first-child {
	font-size: 2rem;
	font-weight: 900;
}

section>p:last-of-type {
	font-size: 1.3rem;
	font-weight: 500;
}

section>a {
	display: inline-block;
	border: 1px solid black;
	width: 150px;
	font-size: 1.3rem;
	font-weight: 600;
	margin: auto;
	padding: 1rem;
	border-radius: 10px;
	text-decoration: none;
	background-color: white;
	color: black;
	text-align: center;
}

section2>p:first-child {
	margin-top: 50px;
	font-size: 2rem;
	font-weight: 900;
}

section2>p:last-of-type {
	font-size: 1.3rem;
	font-weight: 500;
}


footer {
	margin-top: 2rem;
	font-size: 1.1rem;
	color: gray;
}

footer>a {
	text-decoration: none;
	color: white;
}


.section_nav>ul {
	margin: 50px 0px;
	display: flex;
}

.section_nav>ul>li {
	list-style-type: none;
	margin-left: 1.5rem;
}

.section_nav>ul>li>a {
	text-decoration: none;
	font-size: 1.2rem;
	font-weight: 800;
	padding-bottom: 5px;
	color: #c5c3c0;
}

.section_nav>ul>li:first-child>a {
	border-bottom: 5px solid white;
}
.section_nav>ul>li>a:hover {
	border-bottom: 5px solid gray;
}

.sort>ul {
	display: flex;
	justify-content: flex-end;
	
}

.sort>ul>li {
	margin-left: 1.5rem;
	list-style-type: none;
	text-decoration: none;
	
	font-size: 1rem;
	font-weight: 800;
	padding-bottom: 5px;
	color: #c5c3c0;
}

.reviewBoxWrap {
	margin-top: 50px;
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	width: 940px;
	height: auto;
}

.reviewBox {
	margin: 5px 5px;
	width: 450px;
}

.reviewBox1 {
	margin-bottom: 2px;
	background-color: #0d131b;
	width: 450px;
}

.reviewBox2 {
	background-color: #0d131b;
	width: 450px;
}

.modal {
	top: 0;
	left: 0;
	z-index: 1050;
	display: none;
	width: 100%;
	height: 100%;
	overflow: hidden;
	outline: 0;
}

.modal-backdrop {
	top: 0;
	left: 0;
	z-index: 1040;
	width: auto;
	height: auto;
} */

</style>
</head>
<body class="store-main-body">


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


<!-- 리뷰페이지 main -->

<div class="back-top0" >
	<div class="back-top">
		<header>
			<p>Steam Game</p>
			<ul>
				<li><a href="#">상점</a></li>
				<li><a href="#">커뮤니티</a></li>
				<li><a href="#">정보</a></li>
				<li><a href="#">지원</a></li>
			</ul>
		</header>
	</div>
</div>
<div class="container">

	<section>
		<p>최근 플레이(구매)한 게임의 포럼</p>
		<p>Cover your page.Cover your page.Cover your page.Cover your page.Cover your page.Cover your page.Cover your page.Cover your page.Cover your page.Cover your page.Cover your page.Cover your page.Cover your page.Cover your page.Cover your page.Cover your page.</p>
		<input type="button" class="btn btn-primary btn-lg animation-on-hover" id="game1" name="game" value="1111111">
		<input type="button" class="btn btn-primary btn-lg animation-on-hover" id="game2" name="game" value="2222222">
	</section>
	
		
		<div class="section_nav" style="">
			<ul>
				<li><a href="#review" id="reviewBtn">평가</a></li>
				<li><a href="#screenShot" id="ssBtn">스크린샷</a></li>
				<li><a href="#live" id="liveBtn">방송</a></li>
			</ul>
		</div>
		
		
		<!-- 1. 리뷰 탭 -->
		<div id="review">
			<div class="sort">
				<ul>
					<li>표시중</li>
					<li><a href="#">가장인기있는항목</a></li>
					<li><a href="#">최신항목</a></li>
				</ul>
			</div>
			
			<div class="reviewWriteWrap">
			<%-- <c:if test="${}"> --%>
				<div class="form-group">
		        	<label for="exampleFormControlTextarea1">평가리뷰입력자리입니다</label>
		        	<textarea class="form-control" id="body" name="body" rows="3"></textarea>
		        	<button class="btn btn-success animation-on-hover" id="rbtn">평가리뷰저장</button>
		      	</div>
			<%-- </c:if> --%>
			</div>
			
			
			<div class="reviewBoxWrap">
				<c:forEach var="data" items="${LIST}">
				<!-- data-target="#fid" data-toggle="modal" -->
				
					<div class="reviewBox" id="reviewBoxDetail" name="reviewBoxDetail" data-value1="${data.account_no}" data-value2="${data.no}">
						<div class="reviewBox1">
							<div>
								<p>39명이 이 평가가 유용하다고 함</p>
								<c:if test="${data.isgood eq 'G'}">
									<img src="/www/img/good.png">
								</c:if>
								<c:if test="${data.isgood eq 'B'}">
									<img src="/www/img/bad.png">
								</c:if>
								<p>게시일시:${data.rdate}</p>
								<p>${data.body}</p>
							</div>
						</div>
					
						<div class="reviewBox2">
							<p>${data.account_no}</p>
						</div>
					</div>
					<input type="hidden" name="no" id="no" value="${data.no}"/>
					<input type="hidden" name="account_no" id="account_no" value="${data.account_no}"/>
				</c:forEach>
			</div>
			
	
	
		<%-- 리뷰 상세내용 모달창  --%>
		<div class="modal" id="reviewDetail">
		  <div class="" role="document">
		    <div style="">
		      <div style="background-color: #151e2a; display: flex; flex-direction: column; flex-direction: column;">
		        <!-- 자기가 등록한 글을 클릭했을때만 노출 -->
		        <div style="background-color: #2f343f; display: flex;">
			        <img id="accountImg" src="/www/img/logo.png" width="40px;" height="40px;" >
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
		        <div id="divReview" display='none'>
		        	<div>이 평가가 유용한가요?</div>
						<div>
							<input type="radio" name="reviewYN" id="reviewY" value="Y"><label for="reviewY"> 네</label>
							<input type="radio" name="reviewYN" id="reviewN" value="N"><label for="reviewN"> 아니오</label>
							<input type="button" id="ynSaveBtn" value="저장">
						</div>
		        </div>
		      </div>	      	
		    </div>
		  </div>
		</div>
	</div>
	

	
	
	
	
	<!-- 2. 스샷 탭 -->
<div id="screenShot">
	<div class="sort">
		<div>
			<div class="sort">
				<ul>
					<li>표시</li>
					<li><a href="#">인기순</a></li>
					<li><a href="#">검색</a></li>
					<li><a href="#">친구만 표시</a></li>
				</ul>
			</div>
			
			<div class="reviewWriteWrap">
				<div class="form-group">
		        	<label for="exampleFormControlTextarea1">스샷파일 업로드하는 곳 입니다.</label>
		        	<textarea class="form-control" id="" name="" rows="1"></textarea>
		        	<div class="w3-col w3-margin-bottom">
						<label class="w3-col s2">파일추가</label>
						<div class="w3-col m10" id="filebox">
							<input type="file" name="file" class="upfile">
						</div>
					</div>
					<div class="w3-col w3-margin-bottom" id="previewbox" style="display: none;">
						<label class="w3-col s2">미리보기</label>
						<div class="w3-col m10 w3-center" id="preview">
						</div>
					</div>
		        	
		      	</div>
			</div>
		       <button class="btn btn-success animation-on-hover" id="ssUploadBtn">업로드</button>
			
		</div>
		<div>
	
			<div class="reviewBoxWrap">
				<div class="reviewBox" id="" name="" data-value1="" data-value2="">
					<div class="reviewBox1">
						<div>
							<div>프로필</div>
							<div>
								<img src="<%=request.getContextPath()  %>/files/${r.reviewPhoto}" style="width: 500px; height: 300px;">
							</div>
							<div>추천n개</div>
							<div>비추n개</div>
							<div>댓글n개</div>
						</div>
					</div>
				
					<div class="reviewBox2">
						<div>작성자</div>
					</div>
				</div>
			</div>
			
<%-- 스샷 상세내용 모달창  --%>
		<div class="modal" id="">
		  <div class="" role="document">
		    <div style="">
		      <div style="background-color: #151e2a; display: flex; flex-direction: column; flex-direction: column;">
		        <!-- 자기가 등록한 글을 클릭했을때만 노출 -->
		        <div style="background-color: #2f343f; display: flex;">
			        <img id="accountImg" src="/www/img/logo.png" width="40px;" height="40px;" >
			        <div id=""></div>
			        <div id="" display='none'>삭제</div>
		        </div>
		        
		        <div><span id=""></span>명이 평가가 유용하다고 함</div>
		        <div><span id=""></span>명이 평가가 유용하지 않다고 함</div>
		        
		        <div>
					<div>
						<img id="goodImg" src="/www/img/good.png" display='none'>
						<img id="badImg" src="/www/img/bad.png" display='none'>
					</div>
		        </div>
				<div>
					<div id=""></div>
				</div>
		        <div>
		        	<div id=""></div>
		        </div>
		        
		        <!-- 이 평가에 대한 평가 -->
		        <div id="" display='none'>
		        	<div>이 평가가 유용한가요?</div>
						<div>
							<input type="radio" name="" id="" value="Y"><label for="reviewY"> 네</label>
							<input type="radio" name="" id="" value="N"><label for="reviewN"> 아니오</label>
							<input type="button" id="" value="저장">
						</div>
		        </div>
		      </div>	      	
		    </div>
		  </div>
		</div>
			
		</div>
	</div>
</div>







	<!-- 3. 방송 탭 -->
	<div id="live">
		<div>여긴 방송페이지</div>
	</div>
		
		
	<footer>Cover template for
		<a href="https://naver.com">Bootstrap</a>, by
		<a href="https://daum.net">@fighterkun</a>.
	</footer>
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