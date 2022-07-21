$(document).ready(function(){
	
	$('.goTOStoreBtn').click(function(){
		const appId = $(this).parent('div').parent('div.card').attr('id');
		$(location).attr('href', 'http://localhost/www/store/app/?game=' + appId);
	});
});