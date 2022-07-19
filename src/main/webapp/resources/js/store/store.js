$(document).ready(function() {
	/* store 공통 */
	$('#storeCategoryBtn').click(function(){
		$(location).attr('href', '/www/store/categories.nbs');
	});
	$('#allGameBtn').click(function(){
		$(location).attr('href', '/www/store/games.nbs');
	});
	
	$('#TopSellersBtn').click(function(){
		$(location).attr('href', '/www/store/');
	});
	
	
	/* 인기,메인 이벤트 */
	$('#gameSort').change(function(){
		$(location).attr('href', '/www/store/games.nbs/?sortSelect=' + this.value);
	});
	
	$('.game').click(function(){
		$(location).attr('href', '/www/store/app/?game=' + this.id);
	});
});