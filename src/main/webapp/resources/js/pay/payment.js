$(document).ready(function(){
	$('.icon-shape-star').click(function(){
		const data = {
			id : "dummy1",
			game_id : "sample_000001"
		}
		$.ajax({
			url: '/www/payment/refund.nbs',
			type: 'post',
			dataType: 'json',
			data: data,
			success: function(result){				
				swal(result.title, result.msg, result.icon);				
			},
			error: function(){
				console.log('통신에러');	
			}
			
		})
	});


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
		$('#howPay').attr('src','/www/img/pay/'+name+'pay.png');
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

function next4(){
	var gameList = new Array();
	$('.card').each(function(index, item){
		gameList.push($(this).find('h4').html());
	});
	$('#gameList').val(gameList);
	payment();
}
// 총합 구하기
function getTotal(){
	var priceList = 0;
	$('.card').each(function(index, item){
		if($(this).find('span[class="salePrice"]') == null ||
			$(this).find('span[class="salePrice"]').text() == ''){
			// 할인이 아닐 때
			priceList += Number($(this).find('span[class="originalPrice"]').text());
		} else{
			priceList += Number($(this).find('span[class="salePrice"]').text());
		}
	});
	return(priceList);
}
// 결제 테스트
function payment(){
	// 결제할 게임들 id 저장
	var gameIdList = new Array();
	var gamePriceList = new Array();
	$('.card').each(function(index, item){
		if($(this).find('span[class="salePrice"]') == null ||
			$(this).find('span[class="salePrice"]').text() == ''){
			// 할인이 아닐 때			
			gamePriceList.push(getNumber($(this).find('span[class="originalPrice"]').text()));
		} else{
		    gamePriceList.push(getNumber($(this).find('span[class="salePrice"]').text()));
		}
		gameIdList.push($(this).attr('id'));		
	});
	
	const data = {
		merchant_uid : $('#paySel').val() + new Date().getTime(),
		name : $('#gameList').val(),
		amount : $('#totalPrice').val(),
		buyer_email : $('#buyerEmail').val(), 
		buyer_name : $('#buyerNick').val(),
		buyer_tel : $('#buyerTel').val(),
		buyer_postcode : "123456",
		gameIdList : gameIdList,
		gamePriceList : gamePriceList
	}
	switch($('#paySel').val()){
		// KG이니시스 결제(일반 카드 결제)
		case "card":
			IMP.init('imp53015725');	// 가맹점 식별코드
			IMP.request_pay({	// 파라미터 넣기
				pg: "html5_inicis", //pg사명 or pg사명.CID (잘못 입력할 경우, 기본 PG사가 띄워짐)
				pay_method: "card", //지불 방법
				merchant_uid: data.merchant_uid, //가맹점 주문번호 (아임포트를 사용하는 가맹점에서 중복되지 않은 임의의 문자열을 입력)
				name: data.name, //결제창에 노출될 상품명
				amount: data.amount, //금액
				buyer_email : data.buyer_email, 
				buyer_name : data.buyer_name,
				buyer_tel : data.buyer_tel,
				buyer_postcode : data.buyer_postcode
		}, function (rsp) { // callback
			console.log(rsp);
			if (rsp.success) {
				alert("완료 -> imp_uid : "+rsp.imp_uid+" / merchant_uid(orderKey) : " +rsp.merchant_uid);
				swal("결제 완료!","결제가 완료되어 메인페이지로 이동됩니다.","success")
				.then(function(){
					$(location).attr('href', '/www/store/games.nbs');
					/* 	$('form').attr('action', '/www/store/games.nbs');
					$('form').submit(); */
					});
				} else {
					alert("실패 : 코드("+rsp.error_code+") / 메세지(" + rsp.error_msg + ")");
				}
		});
		break

		// 카카오 간편 결제
		case "kakao":
			IMP.init('imp53015725');	// 가맹점 식별코드
			IMP.request_pay({	// 파라미터 넣기
				pg: "kakaopay.TC0ONETIME", //pg사명 or pg사명.CID (잘못 입력할 경우, 기본 PG사가 띄워짐)
				pay_method: "card", //지불 방법
				merchant_uid: data.merchant_uid, //가맹점 주문번호 (아임포트를 사용하는 가맹점에서 중복되지 않은 임의의 문자열을 입력)
				name: data.name, //결제창에 노출될 상품명
				amount: data.amount, //금액
				buyer_email : data.buyer_email, 
				buyer_name : data.buyer_name,
				buyer_tel : data.buyer_tel,
		},  function(rsp) {
				if(rsp.success){
					// 결제 성공
					data.imp_uid = rsp.imp_uid;
					data.merchant_uid = rsp.merchant_uid;
					paymentCheck(data);
				} else {
					alert("결제에 실패하였습니다. 에러 내용: " +  rsp.error_msg);
				}
		});
		break

		// 토스 간편 결제
		case "toss":
			IMP.init('imp53015725');	// 가맹점 식별코드
			IMP.request_pay({
				pg : 'tosstest',
				pay_method : 'card',
				merchant_uid: "toss_test"+ new Date().getTime(), //상점에서 생성한 고유 주문번호
				name: $('#gameList').val(), //결제창에 노출될 상품명
				amount: $('#totalPrice').val(), //금액
				buyer_email : $('#buyerEmail').val(), 
				buyer_name : $('#buyerNick').val(),
				buyer_tel : $('#buyerTel').val(),
		}, function(rsp) { // callback 로직
				console.log(rsp);
			if (rsp.success) {
				alert("완료 -> imp_uid : "+rsp.imp_uid+" / merchant_uid(orderKey) : " +rsp.merchant_uid);
				swal("결제 완료!","결제가 완료되어 메인페이지로 이동됩니다.","success")
				.then(function(){
					$(location).attr('href', '/www/store/games.nbs');
					/* 	$('form').attr('action', '/www/store/games.nbs');
					$('form').submit(); */
					}
					)
				} else {
					alert("실패 : 코드("+rsp.error_code+") / 메세지(" + rsp.error_msg + ")");
				}
		});
		break
	}


	function paymentCheck(data){
		$.ajax({
			url: '/www/payment/checkPay.nbs',
			method: 'POST',
			data : data,
			traditional : true	,
			success: function(result){
				swal(result.title, result.msg, result.icon)
				.then(function(){
					location.replace('/www/store/games.nbs');
				})
			},
			error: function(){
				alert("결제 에러, 다시 진행해주세요");
				//location.replace('/www/store/games.nbs');
			}
	    });
    }
// 가격관리
	    function comma(str) {
	        str = String(str);
	        return '₩ '+ str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	    }

	    function getNumber(str) {
	        var len      = str.length;
	        var sReturn  = "";

	        for (var i=2; i<len; i++){
	            if ( (str.charAt(i) >= "0") && (str.charAt(i) <= "9") ){
	                sReturn += str.charAt(i);
	            }
	        }
	        return sReturn;
	    }
}