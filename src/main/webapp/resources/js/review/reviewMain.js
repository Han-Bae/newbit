$(document).ready(function(){
	$('#review').show();
	$('#screenShot').hide();
	$('#live').hide();
	
	$('#rbtn').click(function(){
		if($("#review_body").val() == ""){ // id에 있는 val값이 비어있으면 안내창 띄워라
	        alert("리뷰를 입력하세요");
	        $("#review_body").focus(); // 그리고 id에 포커스 해라
	        return false;	// return false를 해서 넘어가지 못하게 함
	      }
		
		
		$('#frm').attr('action', '/www/review/reviewWrite.nbs');
		$('#frm').submit();
	});

	$('input[name="game"]').click(function(){
		
		$('#game_no').val($(this).attr('value'));
		
		$('#frm').attr('action', '/www/review/gameReviewList.nbs');
		$('#frm').submit();
	});


	
	// 평가게시글을 누르면 상세페이지 띄우기
	$('.reviewBox').click(function(){
		var ano = $(this).attr('data-value1');
		var rno = $(this).attr('data-value2');

		alert("ano============="+ano);
		alert("rno============="+rno);
		
		if(!ano) return;
		
		$.ajax({
			url: '/www/review/reviewDetail.nbs',
			type: 'post',
			dataType: 'json',
			// 아래 데이터는 컨트롤러에 보내지는 값을 키:값 의 형태로 보내진다
			data: {
				accountNo: ano,
				dreviewNo: rno
			},
			success:function(returnData, status){
				//alert(returnData.result);
				
				/*alert(returnData.NO);
				alert(returnData.RDATE);
				alert(returnData.ISGOOD);
				alert(returnData.BODY);
				alert(returnData.REVIEW_NO);*/
/*				alert("평가작성한ID==="+returnData.NO);
				alert("로그인한고객NO==="+returnData.SNO);*/
				/*alert("유용yes==="+returnData.GOODYN);
				alert("유용no==="+returnData.BADYN);*/
				
				if(returnData.result == 'OK'){
					$('#reviewDetail').modal(); //모달창 띄우기
					$('#accountImg').show();
					
					$('#dAccount').html(returnData.ACCOUNTNO);
					$('#dRdate').html(returnData.RDATE);
					$('#dBody').html(returnData.BODY);
					$('#dreviewNo').val(returnData.NO);
					
					$('#reviewYnGood').html(returnData.GOODYN);
					$('#reviewYnBad').html(returnData.BADYN);
					
					if(returnData.ISGOOD == "G"){
						$('#goodImg').show();
						$('#badImg').hide();
					}else{
						$('#badImg').show();
						$('#goodImg').hide();
					}
					
					//평가글 작성ACCOUNT_NO와 로그인한 고객NO가 같은경우 수정/삭제버튼 표현
					if(returnData.NO == returnData.SNO){
						$('#btnModify').show();
						$('#btnDelete').show();
					}else{
						$('#btnModify').hide();
						$('#btnDelete').hide();
					}
					
					if(returnData.divReview == 'OK'){
						$('#divReview').show();
					}else{
						$('#divReview').hide();
					}
					
				}
			},
			error: function(){
				alert('### 통신에러 ###');
			}
		});
		
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



