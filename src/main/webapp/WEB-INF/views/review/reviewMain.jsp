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
						<a href="/www/info/ProfileUpdate.nbs"><i class="tim-icons icon-single-02"></i></a>
			</c:if>
						<a href="/www/payment/basket.nbs"><i class="tim-icons icon-basket-simple"></i></a>
						<a href="#"><i class="tim-icons icon-shape-star"></i></a>
		</div>
		
		<a href="/www/account/notice.nbs" id="noticeBtn"><i class="tim-icons icon-alert-circle-exc"></i></a>
	</header>
	
<form method="POST" id="frm" name="frm" enctype="multipart/form-data">
<input type="hidden" name="dreviewNo" id="dreviewNo"/>
<c:if test="${not empty RLIST}">
	<input type="hidden" name="game_no" id="game_no" value="${RLIST[0].game_no}"/>
</c:if>
<c:if test="${empty RLIST}">
	<input type="hidden" name="game_no" id="game_no"/>
</c:if>



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
	
		<div class="">
			<button type="button" href="#review" id="reviewBtn" class="btn btn-warning animation-on-hover">평가</button>
			<button type="button" href="#screenShot" id="ssBtn" class="btn btn-primary animation-on-hover">스크린샷</button>
			<button type="button" href="#live" id="liveBtn" class="btn btn-info animation-on-hover">방송</button>
		</div>
		
<!-- 		<div>
			<ul class="nav justify-content-center">
				<li class="nav-item"><a class="nav-link active" href="#review" id="reviewBtn">평가</a></li>
				<li class="nav-item"><a class="nav-link" href="#screenShot" id="ssBtn">스크린샷</a></li>
				<li class="nav-item"><a class="nav-link" href="#live" id="liveBtn">방송</a></li>
			</ul>
		</div> -->
		
		<hr>
		
		<!-- 1. 리뷰 탭 -->
		<div id="review">
			<div class="store-games__tab">
				<button type="button" class="btn" id="">유용한 순으로</button>
				<button type="button" class="btn" id="">좋아요만 보기</button>
			</div>
			
			<div class="reviewWriteWrap">
				<c:if test="${not empty SID}">
					<div class="form-group">
							  <div class="card-body">
							    <div class="form-check form-check-radio">
							    	<h4>평가리뷰입력</h4>
							        <label class="form-check-label">
							        	<img src="/www/img/good.png">
							            <input class="form-check-input" type="radio" name="isgood" id="exampleRadios1" value="G" checked>
							            좋아요<span class="form-check-sign"></span>
							        </label>
							        <label class="form-check-label">
							        	<img src="/www/img/bad.png">
							            <input class="form-check-input" type="radio" name="isgood" id="exampleRadios2" value="B" >
							            싫어요<span class="form-check-sign"></span>
							        </label>
							    </div>
							  </div>
			        	<textarea class="form-control" id="body" name="body" rows="1"></textarea>
			        	<button class="btn btn-default animation-on-hover" id="rbtn">평가리뷰저장</button>
			      	</div>
				</c:if>
			</div>
			
			<!-- 평가리뷰 게시글 -->
			
			<div class="reviewBoxWrap">
				<c:forEach var="data" items="${RLIST}">
				
							<div class="card" style="width: auto;" id="reviewBoxDetail" name="reviewBoxDetail" data-value1="${data.account_no}" data-value2="${data.no}">
							  <div class="reviewBox1">
							  <div class="card-body">
							  <p>${data.account_no}</p>
							    <h4 class="card-title">39명이 이 평가가 유용하다고 함</h4>
							    <h6 class="card-subtitle mb-4 text-muted">
									<p>게시일시:${data.rdate}</p>
									<c:if test="${data.isgood eq 'G'}">
										<img src="/www/img/good.png">
									</c:if>
									<c:if test="${data.isgood eq 'B'}">
										<img src="/www/img/bad.png">
									</c:if>
								</h6>
							    <p class="card-text">${data.body}</p>
							  </div>
							  </div>
							</div>
				
					<div class="reviewBox" id="reviewBoxDetail" name="reviewBoxDetail" data-value1="${data.account_no}" data-value2="${data.no}">
						<div class="reviewBox1">
							<div>

							</div>
						</div>
					
						<div class="reviewBox2">
							
						</div>
					</div>
					
					
					<input type="hidden" name="no" id="no" value="${data.no}"/>
					<input type="hidden" name="game_NO" id="game_NO" value="${data.game_no}"/>
					<input type="hidden" name="account_no" id="account_no" value="${data.account_no}"/>
				</c:forEach>
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
				



	<!-- 3. 방송 탭 -->
	<div id="live">
		<div>여긴 방송페이지</div>
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