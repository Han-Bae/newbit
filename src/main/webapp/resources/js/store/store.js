$(document).ready(function() {
	$('#storeCategoryBtn').click(function(){
		$(location).attr('href', '/www/store/categories.nbs');
	});
	
	$('#gameSort').change(function(){
		$(location).attr('href', '/www/store/games.nbs/?sortSelect=' + this.value);
	});
});