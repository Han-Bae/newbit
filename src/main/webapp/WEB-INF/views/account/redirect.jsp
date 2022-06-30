<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
	<%--
 	로그인 관련 기능 알림창 리다이렉트용 jsp파일
 			@author 김태현
  			@since 2022.05.26
  			@version v.1.0
  				제작자 김태현   --%>
<head>
    <title>데이터확인</title>
	<link rel="stylesheet" type="text/css" href="/www/css/signin.css">
	<link rel="stylesheet" type="text/css" href="/www/css/bootstrap.css">
	<script type="text/javascript" src="/www/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="/www/js/bootstrap.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

	
<style>
	
	body{
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
	</style>
</head>
<script>
$(document).ready(function(){
	//	받아온 url값, 오류멘트, 상태 조회 및 전송
    swal('${title}','${msg}','${icon}')
	.then(function(){
		if('${empty stat}'){
			$(location).attr('href', '${url}');
		}
		// 인증된 이메일과 현재 상태가 일치하면
		if('${mailCheck}' == 'pass'){
			window.close();
		}
		$('#frm').submit();
	});
});
</script>
<body>
	<c:if test="${not empty stat}">
		<form method="post" action="${url}" id="frm">
			<input type="hidden" name="stat" value="${stat}">
		</form>
	</c:if>
</body>
</html>