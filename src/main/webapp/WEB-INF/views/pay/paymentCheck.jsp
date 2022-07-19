<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<!-- 
		메인
		
		제작자 : 김태현
		since : 2022.07.08
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
	<!-- iamport.payment.js -->
  	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
	<script type="text/javascript" src="/www/js/components/header.js"></script>
	<script type="text/javascript" src="/www/js/pay/payment.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('input[name="gameName"]').each(function(){
				$('.games').append(
						'<div class="card" id='+$(this).val()+'>'
						+	'<img class="card-img-left" src="/www/img/logo.png" width="65px" height="50px">'
						+		'<div class="card-width">'
						+			'<h4 style="width:40%;">'+$(this).val()+'</h4>'
						+				'<div class="game-title-info">'
						+					'<span class="howSale">-49%</span>'
						+				'</div>'
						+				'<div class="gamePrice">'
						+					'<span class="originalPrice"><del>999990</del></span>'
						+					'<span class="salePrice">2</span>'
						+				'</div>'
						+		'</div>'
						+'</div>');				
			})
			var priceList = 0;
			$('.card').each(function(index, item){
				if($(this).find('span[class="salePrice"]') == null ||
					$(this).find('span[class="salePrice"]').text() == ''){
					// 할인이 아닐 때
					priceList += Number($(this).find('span[class="originalPrice"]').text());
					
					$(this).find('span[class="originalPrice"]').html(comma($(this).find('span[class="originalPrice"]').text()));
				} else{
					priceList += Number($(this).find('span[class="salePrice"]').text());
					
					$(this).find('span[class="originalPrice"]').html('<del>'+comma($(this).find('span[class="originalPrice"]').text())+'</del>');
					$(this).find('span[class="salePrice"]').text(comma($(this).find('span[class="salePrice"]').text()));
				}
			});
			
			$('#subtotal').text(comma(priceList));
			$('#total').text(comma(getNumber($('#subtotal').text()) * $('input[name="name"]').length));
			$('#totalPrice').val(getNumber($('#total').text()));
		});

	    function comma(str) {
	        str = String(str);
	        return '₩ '+ str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	    }

	    function getNumber(str) {
	        var len      = str.length;
	        var sReturn  = "";

	        for (var i=2; i<len; i++){
	            if ( (str.charAt(i) >= "0") && (str.charAt(i) <= "9") ){
	                sReturn += str.charAt(i);
	            }
	        }
	        return sReturn;
	    }
	</script>
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
				<a class="nav-link active" href="#">
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
			<a href="/www/payment/pick.nbs"><i class="tim-icons icon-basket-simple"></i></a>
		</div>
	</header>
	<!-- 여기까지 -->
	<main>
		<div class="store-top pay-top">
<c:if test="${not empty stat}">
		<input type="hidden" id=stat value="${stat}"></c:if>
<form method="POST" action="/www/payment/payFormCheck.nbs"
		name="frm_info" id="frm_info" class="frm">
	<input type="hidden" id="totalPrice" name="totalPrice">
	<input type="hidden" id="gameList" name="gameList">
	<input type="hidden" id="buyerNick" name="buyerNick" value="${aVO.nickname}">
	<input type="hidden" id="buyerEmail" name="buyerEmail" value="${aVO.email}">
	<input type="hidden" id="buyerTel" name="buyerTel" value="${aVO.tel}">
</form>

<!-- 가격 계산용 보낼 친구 수 -->
<c:forEach var="name" items="${nameList}" varStatus="status">
    	<input type="hidden" name="name" value="${name}">
</c:forEach>
<c:forEach var="name" items="${gameIdList}" varStatus="status">
    	<input type="hidden" name="gameName" value="${name}">
</c:forEach>
			<h4>선물 보내기</h4><h5>▶</h5>
			<h4>메모작성</h4><h5>▶</h5>
			<h4>결제 정보</h4><h5>▶</h5>
			<h4>확인 및 구매</h4>
		</div>
		<hr class="payStat">
		<div class="payMain">
			<div class="rowDiv">
				<div class="row" style="width:100%;">
					<div class="store-games" style="display:contents">
						<div class="store-games-main">
							<div class="store-games--games">
								<div class="games">
									<!-- 개별 게임 -->				
								</div>
							</div>
						</div>
					</div>	
				</div>
				
					<div class="row" style="width:100%;">
						<div style="padding-top: 20px; margin-bottom:40px; justify-content: space-between; display:flex;">
							<h2 style="margin-left: 20px; margin-bottom: 10px;">소계:</h2>
							<h2 id="subtotal" style="margin-right: 20px; margin-bottom: 10px;"></h2>
					  	</div>
						<div style="padding-top: 20px; margin-bottom:40px; justify-content: space-between; display:flex;">
							<h2 style="margin-left: 20px; margin-bottom: 10px;">합계:</h2>
							<h2 id="total" style="margin-right: 20px; margin-bottom: 10px;"></h2>
					  	</div>
			</div>
		</div>
				<div class="conti">
					<button type="button" id="cbtn3" onclick='next4()' class="btn btn-success animation-on-hover">${paySel}pay로 결제하기</button>
					<input type="hidden" id="paySel" value="${paySel}">
				</div>
		</div>
	</main>
</body>
</html>