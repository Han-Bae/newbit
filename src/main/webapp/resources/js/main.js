$(document).ready(function(){
	$('#msgClose').click(function(){
		$('#msgWin').css('display', 'none');
		
		$.ajax({
			url: '/www/mainMsgCheck.blp',
			type: 'post',
			dataType: 'json',
			success: function(data){
				if(data.result == 'OK'){
					// 처리에 성공한 경우
					$('#msgWin').remove();
				}
			},
			error: function(){
				alert('### 통신 에러 ###');
			}
		});
	});
	/* 파일게시판 클릭이벤트 */
	$('#fbtn').click(function(){
		
		
		$.ajax({
			url:'/www/kakao/pay.blp',
			dataType:'json',
			success:function(data){
				alert(data.tid);
				var box = data.next_redirect_pc_url;
				window.open(box);
			},
			error:function(error){
				alert(error);
			}
		});
	});

	// 로그인
	$('#lbtn').click(function(){
		$(location).attr('href', '/www/account/login.nbs');
	});

	// 로그아웃
	$('#obtn').click(function(){
		$(location).attr('href', '/www/account/logout.nbs');
	});
	
	
});