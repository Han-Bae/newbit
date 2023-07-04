$(document).ready(function(){
	// 장바구니에 없는 찜목록 장바구니 추가 버튼 넣기
	$('.basket-game').each(function(index, item){
		if($(this).find('input[name="yesBasket"]').val() != 'yes'){
			$(this).find('div[class="btnDiv"]').append('<button type="button" class="btn btn-primary animation-on-hover basket_game">장바구니</button>');
		}
	});
	// 장바구니 추가
	$('.basket_game').click(function(){
		var sgame_id = $(this).parent().parent().find('div[class="card"]').attr('id');
		var sgame_type = $('#' + sgame_id + 'Type').val();
		var me = $(this);
		$.ajax({
			url: '/www/payment/addBasket.nbs',
			type: 'post',
			dataType: 'json',
			data: {
				game_id : sgame_id,
				game_type : sgame_type
			},
			success: function(data){
				if(data.result == 'OK'){
					// 찜목록 삭제처리 완료 시
					$(me).remove();
					
					if(data.together != 'yes'){
						swal('추가 완료', '장바구니에서 확인해주세요', 'success').then(function(){
							$(location).attr('href', '/www/payment/basket.nbs');
						});
					} else if(data.together == 'yes'){
						swal('추가 완료', '장바구니에서 본편과 같이 확인해주세요', 'success').then(function(){
							$(location).attr('href', '/www/payment/basket.nbs');
						});
					}
				}else{
					swal('추가 실패', '다시 시도해주세요', 'error');
				}
			}, error: function(){
				swal('통신 에러', '다시 시도해주세요', 'error');
			}
		});
	});
	
	// 찜목록에서 삭제
	$('.del_game').click(function(){
		var me = $(this).parent().parent();
		var sgame_id = $(me).find('div[class="card"]').attr('id');
		var surl = $(location).attr('href');
		$.ajax({
			url: '/www/payment/delPick.nbs',
			type: 'post',
			dataType: 'json',
			data: {
				game_id : sgame_id,
				url : surl
			},
			success: function(data){
				if(data.result == 'OK'){
					// 찜목록 삭제처리 완료 시
					$(me).remove();			
				}else{
					swal('삭제 실패', '다시 시도해주세요', 'error');
				}
			}, error: function(){
				swal('통신 에러', '다시 시도해주세요', 'error');
			}
		});
		
	});
});

function goBasket(){
	$(location).attr('href', '/www/payment/basket.nbs');
}