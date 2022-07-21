$(document).ready(function() {
	/* store 공통 */
	$('#storeCategoryBtn').click(function(){
		$(location).attr('href', '/www/store/categories.nbs');
	});
	$('#allGameBtn').click(function(){
		$(location).attr('href', '/www/store/games.nbs');
	});
	
	$('#TopSellersBtn').click(function(){
		$(location).attr('href', '/www/store/games.nbs');
	});
	
	
	/* 인기 페이지 이벤트 */
	$('#gameSort').change(function(){
		$(location).attr('href', '/www/store/games.nbs/?sortSelect=' + this.value);
	});
	
	$('.game').click(function(){
		const appId = this.id;
		const appType = appId.substring(0, appId.indexOf("_"));
		if(appType == "App"){
			$('#goAppDetail').attr('action', '/www/store/app/?game=' + appId);
			
			const reviewIcon = document.querySelector('#' + appId + ' i');
			if(reviewIcon != null) {
				const reviewClass = $(reviewIcon).attr('class');
				if(reviewClass.indexOf('positive')){
					$('#score').val('positive');
				} else if(reviewClass.indexOf('mixed')){
					$('#score').val('mixed');
				} else if(reviewClass.indexOf('negative')){
					$('#score').val('negative');
				}
			}
			$('#goAppDetail').submit();
		}
	});
	
	
	/* 앱 디테일 페이지 이벤트 */
	$('#addToBasketBtn').click(function(){
		const data = {
			game_id : $('#gameId').val()
		}
		$.ajax({
			url: '/www/payment/addBasket.nbs',
			type: 'post',
			dataType: 'json',
			data: data,
			success: function(data){
				if(data.result == 'OK') {
					$(location).attr('href', '/www/payment/basket.nbs');
				}else if(data.result == 'RETRY'){
					swal('추가 불가', '이미 장바구니에 등록된 상품입니다.', 'error');
				}
			},
			error: function(){
				$('input[name="vw"]').val($(location).attr('href'));
				$('form').attr('action', '/www/account/login.nbs');
				$('form').submit();
			}
		});
	});
});