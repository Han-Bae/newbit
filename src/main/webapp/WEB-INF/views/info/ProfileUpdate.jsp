<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 설정</title>
<link rel="stylesheet" type="text/css" href="/www/css/theme/black-dashboard.css">
<link rel="stylesheet" type="text/css" href="/www/css/theme/nucleo-icons.css">
<link rel="stylesheet" type="text/css" href="/www/css/components/header.css">
	
<script src="https://kit.fontawesome.com/e0f46f82c6.js"></script>
<script type="text/javascript" src="/www/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/www/js/components/header.js"></script>
<script type="text/javascript" src="/www/js/edit.js"></script>
	
<style type="text/css">
</style>

<script type="text/javascript">
</script>

</head>

<body>
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
	
<!-- 여기까지 헤더부분 -->


<!--    <div class="menu">
      <i class="fa-solid fa-bars menu-btn menu-btn--white"></i>
      <div class="menu-bar bg-primary">
         <ul>
			<li class="profileBtn"><i class="fa-solid fa-user-astronaut"></i>Profile</li>
			<li class="settingBtn"><i class="fa-solid fa-gear"></i>Settings</li>
			<li class="logoutBtn"><i class="fa-solid fa-arrow-right-from-bracket"></i>Log out</li>
         </ul>
      </div>
   </div> -->

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="profile">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link" href="/www/info/ProfileUpdate.nbs">프로필 수정</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/www/info/InfoUpdate.nbs">회원정보 수정</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/www/info/withdrawal.nbs">회원탈퇴</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
<br>

<div class="row input-center" style="width: 100%">
       <div style="width: 50%; float:none; margin:0 auto" >
	<h5 class="text-primary"><b>Profile Edit</b></h5>
	<hr>
	
	<form action="/viseo/member/info/ProfileUpdateProc.blp" method="post" name="frm" id="frm">
		<input type="hidden" id="tname" value="${DATA.nickname}">
		
	 	<div class="form-group">
		      <label for="nickname" class="form-label mt-4 text-primary"><b>닉네임</b></label>
		      <input type="text" class="form-control col-8" id="nickname" name="nickname" value="${DATA.nickname}">
		 </div>
		  <div class="form-group">
		      <label for="formFile" class="form-label mt-4 text-primary"><b>profile image select</b></label><br>
			  <img id="preview" />
		      <input class="form-control col-8" type="file" id="formFile" onchange="readURL(this);"> 
	    </div>
	
	    
	    <button type="submit" class="btn btn-primary">EDIT</button>
	    <button type="button" class="btn btn-primary exit-btn">EXIT</button>
	</form>
	</div>
</div>
</body>
</html>