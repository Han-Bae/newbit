$(document).ready(function() {
	/* store 공통 */
	$('#favoritesBtn').click(function(){
		$(location).attr('href', '/www/store/favorites.nbs');
	});
	$('#allGameBtn').click(function(){
		$(location).attr('href', '/www/store/');
	});
	$('#storeCategoryBtn').mouseenter(function(){
		$('.selectCategoryTab').css('display', 'flex');
	});
	$('.selectCategoryTab').mouseleave(function(){
		$('.selectCategoryTab').css('display', 'none');
	});
	$('#searchBtn').click(function(){
		$('#searchForm').submit();
	});
	
	$('#TopSellersBtn').click(function(){
		$(location).attr('href', '/www/store/topSeller.nbs');
	});
	$('#newTopSellerBtn').click(function(){
		$(location).attr('href', '/www/store/newTopSeller.nbs');
	});
	$('#specialsSaleBtn').click(function(){
		$(location).attr('href', '/www/store/specialsSale.nbs');
	});
	
	$('.game').click(function(){
		const appId = this.id;
		const appType = appId.substring(0, appId.indexOf('_'));
		
		if(appType == 'App'){
			$(location).attr('href', '/www/store/app/?game=' + appId);
		}
	});
	
	
	/* 최고 인기 페이지 이벤트 */
	$('#range').on('input', function(){
		switch(this.value) {
		case '65000':
			$('#rangeValue').text('모든 가격');
			break;
		case '60000':
			$('#rangeValue').text('₩ 60,000 이하');
			break;
		case '55000':
			$('#rangeValue').text('₩ 55,000 이하');
			break;
		case '50000':
			$('#rangeValue').text('₩ 50,000 이하');
			break;
		case '45000':
			$('#rangeValue').text('₩ 45,000 이하');
			break;
		case '40000':
			$('#rangeValue').text('₩ 40,000 이하');
			break;
		case '35000':
			$('#rangeValue').text('₩ 35,000 이하');
			break;
		case '30000':
			$('#rangeValue').text('₩ 30,000 이하');
			break;
		case '25000':
			$('#rangeValue').text('₩ 25,000 이하');
			break;
		case '20000':
			$('#rangeValue').text('₩ 20,000 이하');
			break;
		case '15000':
			$('#rangeValue').text('₩ 15,000 이하');
			break;
		case '10000':
			$('#rangeValue').text('₩ 10,000 이하');
			break;
		case '5000':
			$('#rangeValue').text('₩ 5,000 이하');
			break;
		case '0':
			$('#rangeValue').text('무료');
			break;
		}
	});
	
	$('#gameSort, #range, #specialsSale').on('change', function(){
		const sortSelect = $('#gameSort option:selected').val();
		const paramSort = $('#sort').val();
		const sort = sortSelect == paramSort ? paramSort : sortSelect;
		const priceRange = $('#range').val();
		const specialsSale = $('#specialsSale').val();
		
		if($('#specialsSale').is(':checked')){
			if(sort == '' && priceRange == '65000'){
				$(location).attr('href', '/www/store/topSeller.nbs/?specials=' + specialsSale);
			} else if(sort == ''){
				$(location).attr('href', '/www/store/topSeller.nbs/?maxPrice=' + priceRange + '&specials=' + specialsSale);
			} else if(priceRange == '65000'){
				$(location).attr('href', '/www/store/topSeller.nbs/?sortSelect=' + sort + '&specials=' + specialsSale);
			} else {
				$(location).attr('href', '/www/store/topSeller.nbs/?sortSelect=' + sort + '&maxPrice=' + priceRange + '&specials=' + specialsSale);
			}
		} else {
			if(sort == '' && priceRange == '65000'){
				$(location).attr('href', '/www/store/topSeller.nbs');
			} else if(sort == ''){
				$(location).attr('href', '/www/store/topSeller.nbs/?maxPrice=' + priceRange);
			} else if(priceRange == '65000'){
				$(location).attr('href', '/www/store/topSeller.nbs/?sortSelect=' + sort);
			} else {
				$(location).attr('href', '/www/store/topSeller.nbs/?sortSelect=' + sort + '&maxPrice=' + priceRange);
			}
		}
		
	});
	
	
	/* 카테고리 페이지 이벤트 */
	$('.tabs').click(function(){
		const tab = $(this).attr('id');
		$(location).attr('href', '/www/store/categories.nbs?tag=' + $('#categoryTag').val() + '&tab=' + tab);
	});
	
	
	/* 앱 디테일 페이지 이벤트 */
	const detailLoadAfter = function(){
		if($('.game-media-scroll > div').first().hasClass('movie')){
			$('video.mainMedia').css('display', 'block');
			$('.game-media-scroll > div').first().find('.now-show').css('visibility', 'visible');
			
			const movieSrc = $('.game-media-scroll > div').first().find('img').attr('class');
			$('video.mainMedia').find('source').attr('src', movieSrc);
			$('video.mainMedia').get(0).load();
			$('video.mainMedia').get(0).play();
		} else if($('.game-media-scroll > div').first().hasClass('screenshot')){
			$('video.mainMedia').css('display', 'none');
			$('.game-media-scroll > div').first().find('.now-show').css('visibility', 'visible');
					
			const screenshotSrc = $('.game-media-scroll > div').first().find('img').attr('class');
			$('img.mainMedia').css('display', 'block');
			$('img.mainMedia').attr('src', screenshotSrc);
		}
	}
	detailLoadAfter();
	
	$('#addToBasketBtn').click(function(){
		const data = {
			game_id : $('#gameId').val(),
			game_type : $('#gameType').val()
		}
		$.ajax({
			url: '/www/payment/addBasket.nbs',
			type: 'post',
			dataType: 'json',
			data: data,
			success: function(data){
				if(data.result == 'OK') {
					$('#together').val(data.together);
					$('#fullgameAndDlcForm').submit();

				}else if(data.result == 'RETRY'){
					swal('추가 불가', '이미 장바구니에 등록된 상품입니다.', 'error');
				}
			},
			error: function(){
				$('input[name="vw"]').val($(location).attr('href'));
				$('#basketErrorForm').attr('action', '/www/account/login.nbs');
				$('#basketErrorForm').submit();
			}
		});
	});
	
	$('#goToBasketBtn').click(function(){
		$(location).attr('href', '/www/payment/basket.nbs');
	});
	
	$('#addToPickBtn').click(function(){
		const data = {
				game_id : $('#gameId').val()
		}
		$.ajax({
			url: '/www/payment/addPick.nbs',
			type: 'post',
			dataType: 'json',
			data: data,
			success: function(data){
				if(data.result == 'OK') {
					$(location).attr('href', '/www/payment/pick.nbs');
				}else if(data.result == 'RETRY'){
					swal('추가 불가', '이미 찜목록에 등록된 상품입니다.', 'error');
				}
			},
			error: function(){
				$('input[name="vw"]').val($(location).attr('href'));
				$('#basketErrorForm').attr('action', '/www/account/login.nbs');
				$('#basketErrorForm').submit();
			}
		});		
	});
	
	$('.media').click(function(){
		$('video.mainMedia').get(0).pause();
		$('.game-media-scroll .now-show').css('visibility', 'hidden');
		$('img.mainMedia').attr('src', '');
		$('video.mainMedia').find('source').attr('src', '');
		$('.mainMedia').css('display', 'none');
		
		$(this).find('.now-show').css('visibility', 'visible');
		
		if($(this).hasClass('movie')){
			$('video.mainMedia').css('display', 'block');
			$('video.mainMedia').find('source').attr('src', $(this).find('img').attr('class'));
			$('video.mainMedia').get(0).load();
			$('video.mainMedia').get(0).play();
		} else if($(this).hasClass('screenshot')){
			$('img.mainMedia').css('display', 'block');
			$('img.mainMedia').attr('src', $(this).find('img').attr('class'));
		}
	});
	
});