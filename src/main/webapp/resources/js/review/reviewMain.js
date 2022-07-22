$(document).ready(function(){
	
	// 리뷰, 스샷, 방송 탭 전환용
	$('#review').show();
	$('#screenShot').hide();
	$('#live').hide();
	
	// 리뷰저장버튼을 누르면 작성한 리뷰글을 저장하게 하는 기능
	$('#rbtn').click(function(){
		
		if($("#body").val() == ""){ // id에 있는 val값이 비어있으면 안내창 띄워라
	        alert("리뷰를 입력하세요");
	        $("#body").focus(); // 그리고 id에 포커스 해라
	        return false;	// return false를 해서 넘어가지 못하게 함
	      }
		
		// reviewWrite.nbs 로 이동할 때 전체 내용을 가지고 서밋해라
		$('#frm').attr('action', '/www/review/reviewWrite.nbs');
		$('#frm').submit();
	});
	
	// 태그 name의 game을 클릭했을 시 
	$('input[name="game"]').click(function(){
		
		// 상단 버튼이 클릭되면 game_no의 val값을 value 값으로 사용해라 
		$('#game_no').val($(this).attr('value'));
		
		// gameReviewList.nbs 로 이동 시 현재 정보를 가지고 가서 서밋해라.
		$('#frm').attr('action', '/www/review/gameReviewList.nbs');
		$('#frm').submit();
	});


	
	// 평가게시글을 누르면 상세페이지 띄우기
	$('.card').click(function(){
		var ano = $(this).attr('data-value1');
		var rno = $(this).attr('data-value2');
		$(location).attr('href', '/www/review/reviewDetailPage.nbs?ano='+ano+'&rno='+rno);
		if(!ano) return;
	});
	
	
	// 리뷰평가 상세에서 평가한 내용 유용한지 또 평가하는거
	$('#ynSaveBtn').click(function(){
		
		$('#frm').attr('action', '/www/review/reviewYN.nbs');
		$('#frm').submit();
	});	
	
	
	$('#btnDelete').click(function(){
		$('#frm').attr('action', '/www/review/delReview.nbs');
		$('#frm').submit();
	});

	
	$('#reviewBtn').click(function(){
		$('#review').show();
		$('#screenShot').hide();
		$('#live').hide();
	});
	
	$('#ssBtn').click(function(){
		$('#review').hide();
		$('#screenShot').show();
		$('#live').hide();
	});
	
	$('#liveBtn').click(function(){
		$('#review').hide();
		$('#screenShot').hide();
		$('#live').show();
	});
	
	
	//파일첨부
	$('#filebox').on('change', '.upfile', function(evt){
		var str = $(this).val();
		var index = $(this).index();
		var tmp = $('.upfile');
		var max = tmp.length;
		
		if(!str){
			$(this).remove();
			$('.picbox').eq(index).remove();
			return;
		}
		var path = URL.createObjectURL(evt.target.files[0]);
		var el = $('.upfile');
		if((index + 1) != el.length){
			alert("어디1");
			$('.infoAvtBox').eq(index).attr('src', path);
		}
		
		if(index == max - 1){
			alert("어디2");
			$('#preview').append('<div class="inblock pdAll10 picbox w3-card"><div class="w3-col w3-border" style="width: 100%; height: 100%; overflow: hidden;">' +
							'<img src="' + path + '" class="infoAvtBox">' + 
						'</div></div>');
		}
		$('#previewbox').css('display', 'block');
	});
	
	//스크린샷 업로드
	$('#ssUploadBtn').click(function(){
		$('#frm').attr('action', '/www/sshot/uploadScreenShot.nbs');
		$('#frm').submit();
	});


	

});



