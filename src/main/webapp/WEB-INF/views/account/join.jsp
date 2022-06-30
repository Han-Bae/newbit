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
}
.form-signin {
      background-color: #ffffff;
      opacity: initial;
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
    <main class="form-signin" style="max-width:650px;">
		<form method="POST" action="/www/account/joinProc.nbs"
			  name="frm" id="frm" class="frm">
		<!-- 메인 로고 가운데 정렬 -->
			<img src="/www/img/logo.png" alt="" style="height:80px; width:auto; text-align: left;">
		<!-- 타이틀 -->
			<h2 style="margin-bottom: 10px;" class="text-primary margin-top content"><strong>회원가입</strong></h2>
				<!-- 회원 아이디, 아이디 체크박스 --> 
			<div class="form-floating" style="margin-bottom: 5px;">
			      <input type="text" name="id" class="form-control" id="id" title="숫자, 대소문자 4~10글자로 입력해주세요."
			      	pattern="^([A-Za-z0-9]){4,10}$" placeholder="아이디를 입력하세요" required autofocus>
			      <label for="id">ID</label>
			      <div class="ckdiv" style="text-align: right; margin-top: 1.5px;">
				      <h4 id="idmsg"></h4>
					  <button class="btn btn-primary ckbtn" type="button" id="idck">확인</button>
			      </div>
			</div>
 				
				<!-- 비밀번호 -->
			<div class="form-floating" style="margin-bottom: 5px;">
				<input type="password" name="pw" class="form-control" id="pw" title="숫자, 영어를 포함한 6~15자로 입력해주세요."
		      		pattern="^(?=.*[a-zA-Z])(?=.*[0-9]).{6,15}$" placeholder="비밀번호를 입력하세요" required>
		      	<label for="pw">Password</label>
		      	<div class="ckdiv" style="text-align: right; margin-top: 1.5px;">
					<h4 id="pwmsg"></h4>
				</div>
			</div>
				
			<!-- 비밀번호 체크  -->
			<div class="form-floating">
		      	<input type="password" name="repw" class="form-control" id="repw" placeholder="비밀번호를 다시 입력해주세요." required>
		      	<label for="repw">비밀번호 확인</label>
		      	<div class="ckdiv" style="text-align: right; margin-top: 1.5px;">
				    <h4 id="repwmsg"></h4>
				</div>
			</div>
			
			<!-- 계정 설정 -->
<!-- 	        <div class="wrapper">
				<div class="title">Select your type</div>
					<div class="box">
				    <input type="radio" name="type" id="utype">
				    <input type="radio" name="type" id="dtype">
			      	<label for="utype" class="option-1">
				        <div class="dot"></div>
				        <div class="text">Gamer</div>
			      	</label>
			      	<label for="dtype" class="option-2">
				        <div class="dot"></div>
				        <div class="text">Devloper</div>
				    </label>
				</div>
			</div> -->
			
			  <div class="wrapper">
    <div class="title">Select your option</div>
    <div class="box">
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
			
			
			<!-- 닉네임 -->
			<div class="form-floating" style="margin-bottom: 5px;">
			    <input type="text" name="nickname" class="form-control" id="nickname"
			      	placeholder="닉네임을 입력하세요" required>
			    <label for="nickname">NickName</label>
			    <div class="ckdiv"  style="text-align: right; margin-top: 1.5px;">
			    	<h4 id="nicknamemsg"></h4>
					<button class="ckbtn btn btn-primary" type="button" id="nicknameck">확인</button>
				</div>
			</div>     
				<!-- 이메일 -->
			<div class="form-floating" style="margin-bottom: 5px;">
			    <input type="text" name="mail" class="form-control" id="mail" title="ex)asdf@asd.qwe"
			      	pattern="^([a-zA-Z0-9]){4,10}@([a-zA-Z]){2,10}.([a-zA-Z]){2,3}$"
			      	placeholder="이메일을 입력하세요." required>
			    <label for="mail">Mail</label>
			    <div class="ckdiv"  style="text-align: right; margin-top: 1.5px;">
			      	<button class="ckbtn btn btn-primary" type="button" id="mailck">확인</button>
					<button type="button" class="ckbtn btn btn-primary" id="mbtn">인증</button>
			    </div>
				<span id="mailckmsg" class="form-text text-muted"></span>
			</div>
						 
					<!-- 인증 버튼 이벤트 -->
				

				<!-- 버튼 이벤트 -->
				<div class="content">
					<button style="margin-top:8px;" type="button" class="btn btn-primary btn-lg" id="jbtn">가입하기</button>
				</div>
			</form>			
	<!-- 가운데정렬 태그 끝 -->
	</main>
</body>
</html>