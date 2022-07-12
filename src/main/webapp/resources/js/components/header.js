$(document).ready(function() {
	$('#storeCategoryBtn').click(function(){
		$(location).attr('href', '/www/store/categories.nbs');
	});
	$('.header-logo img').click(function(){
		$(location).attr('href', '/www/store/games.nbs');
	})
});
