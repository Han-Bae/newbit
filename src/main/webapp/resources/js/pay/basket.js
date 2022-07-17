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
		$('#total').text(comma(priceList));
	});
});

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
	return Number(sReturn);
}

function del_game() {
	$(this).parent().remove();
}

function selfPay() {
	gaList();
	$('#frm').attr('action', '/www/payment/myselfPayInfo.nbs');
}

function presentPay() {
	gaList();
	$('#frm').attr('action', '/www/payment/payForm.nbs');
}

function gaList(){
	var gList = new Array();
	$('input[name="ckid"]:checked').each(function(){
		gList.push($(this).parent().parent().parent().find('div[class="card"]').attr('id'));
	});
	$('#gameList').val(gList);
}