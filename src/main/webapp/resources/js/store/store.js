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
			const reviewClass = $(reviewIcon).attr('class');
			// const reviewScore = reviewClass.substring(reviewClass.lastindexOf(' ') + 1);
			if(reviewClass.indexOf('positive')){
				$('#score').val('positive');
			} else if(reviewClass.indexOf('mixed')){
				$('#score').val('mixed');
			} else if(reviewClass.indexOf('negative')){
				$('#score').val('negative');
			}
			const score = $('#score').val();
			console.log(score)
			$('#goAppDetail').submit();
		}
	});
	
	
	/* 앱 디테일 페이지 이벤트 */
	$('#addToBasketBtn').click(function(){
		$('#addToBasket').submit();
	});
});