<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
<title>NewBit Register</title>

	<link rel="stylesheet" type="text/css" href="/www/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/www/css/signin.css">
	<link rel="stylesheet" type="text/css" href="/www/css/radio.css">
	<script type="text/javascript" src="/www/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="/www/js/bootstrap.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script type="text/javascript" src="/www/js/join.js"></script>


<style type="text/css">
	.bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }
@media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
body	{
		overflow: auto;
		font-family: 'necleo';
		color: #fff;
		}
body::before {
	    position: fixed;
	    top: 0;
	    left: 0;
	    right: 0;
	    bottom: 0;
	    background-image: url(/www/img/back.jpg);
	    background-size: cover;
	    -webkit-filter: blur(3px); 
	    -moz-filter: blur(3px); 
	    -o-filter: blur(3px); 
	    -ms-filter: blur(3px); 
	    filter: blur(5px);
	    transform: scale(1.02);
	    z-index: -1; 
	    content: "";
		}
h4{
	font-size: 10pt;
	margin-top: 5px;
}
.form-signin {
    width:450px;
    max-width:650px;
    }
	 
.ckdiv{
		text-align:right;
		margin-top: 3px;
	}
.ckbtn{
	 	width:calc(1.5rem + 80px);
	 	font-size: 0.8rem;
	 	height: calc(2rem + 2px);
	 	line-height: 1.25;
	 }
	 
</style>
<script type="text/javascript">
</script>
</head>
<body class="text-center">
	<!-- 가운데 정렬 -->
    <main class="form-signin">
		<form method="POST" action="/www/account/joinProc.nbs"
			  name="frm" id="frm" class="frm">
		<!-- 메인 로고 가운데 정렬 -->
			<img src="/www/img/logo.png" alt="" style="height:80px; width:auto; text-align: left;">
		<!-- 타이틀 -->
			<h2 style="margin-bottom: 10px;" class="text-primary margin-top content"><strong>New Account</strong></h2>
				<!-- 회원 아이디, 아이디 체크박스 --> 
		<div class="form-Style">
		<div class="form-Css">
			<div class="form-floating">
			      <input type="text" name="id" class="form-control" id="id" title="숫자, 대소문자 4~10글자로 입력해주세요."
			      	style="margin-bottom: 10px;" pattern="^([A-Za-z0-9]){4,10}$" placeholder=" " required
			      	>
			      <label for="id">ID</label>
			      <span class="indicator idCk"></span>
			</div>
		</div>
		</div>
			      <div class="ckdiv" style="text-align: right; margin-top: 1.5px; padding-left: 15px;">
				      <h4 id="idmsg"></h4>
				      <a>
					  <button class="ckbtn" type="button" id="idck">Check</button>
				      </a>
			      </div>
 				
				<!-- 비밀번호 -->
		<div class="form-Style">
		<div class="form-Css">
			<div class="form-floating">
				<input type="password" name="pw" class="form-control" id="pw" title="숫자, 영어를 포함한 6~15자로 입력해주세요."
		      		pattern="^(?=.*[a-zA-Z])(?=.*[0-9]).{6,15}$" placeholder=" " required>
		      	<label for="pw">Password</label>
			    <span class="indicator"></span>
			</div>
		</div>
		</div>
			<!-- 비밀번호 체크  -->
		<div class="form-Style">		
		<div class="form-Css">
			<div class="form-floating">
		      	<input type="password" name="repw" class="form-control" id="repw" placeholder=" " required>
		      	<label for="repw">Password Check</label>
			    <span class="indicator"></span>
			</div>
		</div>
		</div>
				
			<!-- 닉네임 -->
		<div class="form-Style">
		<div class="form-Css">
			<div class="form-floating">
			    <input type="text" name="nickname" class="form-control"  id="nickname"
			      	placeholder=" " pattern="^([A-Za-z0-9]){1,15}$" required>
			    <label for="nickname">NickName</label>
			    <span class="indicator"></span>
			</div>     
		</div>
		</div>
			    <div class="ckdiv" id="niCk" style="text-align: right; padding-left: 15px;">
			 		<h4 id="nicknamemsg" style="text-algin: right;"></h4>
			    	<a>
					<button class="ckbtn" type="button" id="nicknameck">Check</button>
			    	</a>
				</div>
				<!-- 이메일 -->
			<div id="mailStyle">
				<div id="mailCss">
					<div class="inputBox">
						<input type="text" name="email" id="email" placeholder="Enter Email Address" required
							pattern="^[^ ]+@[^ ]+\.[a-z]{2,3}$">
						<span class="indicator"></span>
					</div>
				</div>					
			</div>
				 		<h4 id="emailmsg" style="text-align:right;"></h4>
				    <div class="ckdiv" id="maildiv" style="text-align: right; margin-top: 1.5px;">
				    	<a>
				      	<button class="ckbtn" type="button" id="mailck">Check</button>
				    	</a>
				    	<a>
						<button type="button" class="ckbtn" id="mbtn">Certification</button>
				    	</a>
				    </div>
					<span id="mailckmsg" class="form-text text-muted"></span>
						 

					<!-- 인증 버튼 이벤트 -->
				

			<!-- 계정타입 -->
			<div class="wrapCss">
			  <div class="wrapper">
			    <div class="title">Select Account Type</div>
				    <div class="box">
				      <input type="hidden" name="istype" id="istype">
				      <input type="radio" name="select" id="option-1">
				      <input type="radio" name="select" id="option-2">
				      <label for="option-1" class="option-1">
				        <div class="dot"></div>
				        <div class="text">Gamer</div>
				      </label>
				      <label for="option-2" class="option-2">
				        <div class="dot"></div>
				        <div class="text">Developer</div>
				      </label>
				    </div>
				</div>
			</div>			
			
				<!-- 버튼 이벤트 -->
				<div class="ckdiv content">
					<a>
					<button type="button" class="btn-lg" id="jbtn">Regist</button>
					</a>
				</div>
			</form>			
	<!-- 가운데정렬 태그 끝 -->
	</main>
</body>
</html>