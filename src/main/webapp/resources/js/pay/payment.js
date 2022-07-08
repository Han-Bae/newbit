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

	/////////////////////////// INFO - 3단계
	$('#selPay').change(function(){
		var name = $('#selPay option:selected').val();
		$('#howPay').attr('src','/www/img/'+name+'pay.png');
		$('.payInfo').css('display', 'block');
	});

	$('#payBtn').click(function(){
		payment();
	});
});

// 3단계로 보내기
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
// 4단계로 보내기
function next3(){
	$('input[name="paySel"]').val($('#selPay option:selected').val());
	$('#frm_info').submit();
}

// 결제 테스트
function payment(data){
	IMP.init('imp53015725');	// 가맹점 식별코드
	IMP.request_pay({	// 파라미터 넣기
		pg: "kakaopay.TC0ONETIME", //pg사명 or pg사명.CID (잘못 입력할 경우, 기본 PG사가 띄워짐)
        pay_method: "card", //지불 방법
        merchant_uid: "kakao_test_id1", //가맹점 주문번호 (아임포트를 사용하는 가맹점에서 중복되지 않은 임의의 문자열을 입력)
        name: "game1", //결제창에 노출될 상품명
        amount: 13700, //금액
        buyer_email : "testiamport@naver.com", 
        buyer_name : "홍길동",
        buyer_tel : "01012341234"
    }, function (rsp) { // callback
        if (rsp.success) {
            alert("완료 -> imp_uid : "+rsp.imp_uid+" / merchant_uid(orderKey) : " +rsp.merchant_uid);
        } else {
            alert("실패 : 코드("+rsp.error_code+") / 메세지(" + rsp.error_msg + ")");
        }
    });
}