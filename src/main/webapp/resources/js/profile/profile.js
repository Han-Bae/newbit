$(document).ready(function(){
	
	$('.goTOStoreBtn').click(function(){
		const appId = $(this).parent('div').parent('div.card').attr('id');
		$(location).attr('href', 'http://localhost/www/store/app/?game=' + appId);
	});
	
	$('.refundBtn').click(function(){
		const appId = $(this).parent('div').parent('div.card').attr('id');
		$.ajax({
			url: '/www/payment/refund.nbs',
			type: 'post',
			dataType: 'json',
			data: {
				game_id: appId
			},
			success: function(data){
				if(data.icon == 'success'){
					swal(data.title, data.msg, data.icon);
					alert($(this).parent('div').parent('div.card'));
					$(this).parent('div').parent('div.card').remove();
				}else{
					swal(data.title, data.msg, data.icon);					
				}
			},
			error: function(request,status,error){
				alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
			}
		})
	})
});