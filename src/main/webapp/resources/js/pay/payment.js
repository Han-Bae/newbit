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
	var list = '';
	$('input[name="name"]').each(function(){
		list +=  $(this).val()+', ';
	});
	$('#friendList').css('color', '#fff');
	$('#friendList').val(list.substring(0, list.length - 2));

	// 메세지 남은 글자수 알려주기
	$('#friendBody').on('keyup', function(){
		$('#restText').html(160 - $(this).val().length);
	});

});

function next2(){
	var nameList = new Array();
	$('input[name="name"]').each(function(index, item){
		nameList.push($(item).val());
	});
	$('input[name="nameList"]').val(nameList);
	$('input[name="presentTitle"]').val($('#friend-title').val());
	$('input[name="presentMsg"]').val($('textarea').val());

	if($('input[name="nameList"]').val() == ''){
		swal('선물을 보낼 친구 정보를 소실했습니다.','결제를 처음부터 진행해주세요.', 'error');
		$(location).attr('href', '/www/payment/payForm.nbs');
	}
	if($('input[name="presentTitle"]').val() == ''){
		swal('선물 제목이 없습니다.','내용을 입력해주세요.', 'error');
		return;
	}
	if($('input[name="presentMsg"]').val() == ''){
		swal('선물 메세지 내용이 없습니다.','내용을 입력해주세요.', 'error');
		return;
	}
	$('#frm_memo').submit();
}