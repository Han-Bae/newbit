$(document).ready(function(){
	// 결제 진행 상황 표시
	var stat = $("#stat").val();
	var no;
	switch(stat){
		case 'first':
			no = 0;
			break;
		case 'second':
			no = 1;
			break;
		case 'third':
			no = 2;
			break;
		case 'fourth':
			no = 3;
			break;
		}
	for(var i = 0; i < $('.store-top > h4').length; i++){
		if(i == no)	$('.store-top > h4:eq('+i+')').css("color", "white");
		else $('.store-top > h4:eq('+i+')').css("color", "grey");
	}
	
	/////////////////////// 친구설정 - 1단계
	// 검색창
	$('#find').on('keyup', function(){
		var value = $(this).val().toLowerCase();
		$('.card h4').filter(function(){
			$(this).parent().parent().toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});

	// 친구 하나 이상 선택해야 다음
	$('#cbtn').click(function(){
		var items = new Array();
		$('input[name="fid"]:checked').each(function(){
			items.push($(this).val());
		});
		if(items.length >= 1){
			$('#nameList').val(items);
			$('input[type="checkbox"]').prop('disabled');
			$('#frm').submit();
		}else{
			swal('진행할 수 없습니다','선물을 보낼 친구를 1명 이상 지정해주세요!', 'error');
			return;
		}

	});

	////////////////////////// Memo - 2단계
	// 친구 리스트 넣기
	$('input[name="name"]').each(function(){
		$('#friendList').append($(this).val()+', ');
	});
	var list = $('#friendList').html();
	$('#friendList').html(list.substring(0, list.length - 2));
});