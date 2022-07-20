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
		const appType = this.id.substring(0, this.id.indexOf("_"));
		if(appType == "App"){
			$(location).attr('href', '/www/store/app/?game=' + this.id);
		}
	});
	
	
	/* 앱 디테일 페이지 이벤트 */
	$('#addToBasketBtn').click(function(){
		const param = (new URL(document.location)).searchParams;
		const appId = param.get('game');
		$(location).attr('href', '/www/payment/addBasket.nbs/?game_id=' + appId);
	});
});