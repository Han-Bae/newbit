$(document).ready(function(){
	$('.card').each(function(index, item){
				if($(this).find('span[class="salePrice"]') == null ||
					$(this).find('span[class="salePrice"]').text() == ''){			
					$(this).find('span[class="originalPrice"]').html(comma($(this).find('span[class="originalPrice"]').text()));
				} else{
					$(this).find('span[class="originalPrice"]').html('<del>'+comma($(this).find('span[class="originalPrice"]').text())+'</del>');
					$(this).find('span[class="salePrice"]').text(comma($(this).find('span[class="salePrice"]').text()));
				}
			});

	// 클릭한 게임 가격 계산
	$('input[name="ckid"]').change(function(){
		var priceList = 0;
		$('input[name="ckid"]:checked').each(function(){
			var basket_game = $(this).parent().parent().parent();
			if(basket_game.find('span[class="salePrice"]') == null ||
			basket_game.find('span[class="salePrice"]').text() == ''){
				// 할인이 아닐 때
				priceList += getNumber(basket_game.find('span[class="originalPrice"]').text());
			} else{
				priceList += getNumber(basket_game.find('span[class="salePrice"]').text());
			}
		});
		if(priceList == 0){
			$('#total').text('₩ 0');						
		}else{
			$('#total').text('₩ '+comma(priceList));			
		}
	});
	
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

function comma(str) {
	str = String(str);
	return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

function getNumber(str) {
	var len      = str.length;
	var sReturn  = "";

	for (var i=2; i<len; i++){
		if ( (str.charAt(i) >= "0") && (str.charAt(i) <= "9") ){
			sReturn += str.charAt(i);
		}
	}
	return Number(sReturn);
}

function selfPay() {
	gaList();
	if($('input[name="gameIdList"]').val().length >= 1){
		$('#frm').attr('action', '/www/payment/myselfPayInfo.nbs');
		$('#frm').submit();
	}else{
		swal('진행할 수 없습니다','선물을 보낼 친구를 1명 이상 지정해주세요!', 'error');
		return;
	}
}

function presentPay() {
	gaList();
	if($('input[name="gameIdList"]').val().length >= 1){
		$('#frm').attr('action', '/www/payment/payForm.nbs');
		$('#frm').submit();
	}else{
		swal('진행할 수 없습니다','결제할 게임을 1개 이상 지정해주세요!', 'error');
		return;
	}		
}

function gaList(){
	var gList = new Array();
	$('input[name="ckid"]:checked').each(function(){
		gList.push($(this).parent().parent().parent().find($('.card')).attr('id'));
	});
	$('input[name="gameIdList"]').val(gList);
}

function self_next(){
	var gameList = new Array();
	$('.card').each(function(index, item){
		gameList.push($(this).find('h4').html());
	});
	$('#gameList').val(gameList)
	payment();
}
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
					$(location).attr('href', '/www/store/');
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
	}


	function paymentCheck(data){
		$.ajax({
			url: '/www/payment/selfCheckPay.nbs',
			method: 'POST',
			data : data,
			traditional : true	,
			success: function(result){
				swal(result.title, result.msg, result.icon)
				.then(function(){
					location.replace('/www/store/');
				})
			},
			error: function(){
				alert("결제 에러, 다시 진행해주세요");
				location.replace('/www/store/');
			}
	    });
    }
}