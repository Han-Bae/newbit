$(document).ready(function(){
		// 사용자 타입 설정
	$('input[type="radio"]').change(function(){
		if($(this).attr('id') == 'option-1'){
			$('#istype').attr('value','U');
		}else{
			$('#istype').attr('value','D');
		}
	});

	// 포커스시 흰색으로 바뀌는 것 변경
	$('input[class="form-control"]').focus(function(){
		$(this).css("color","#fff");
		$(this).css("background-color","#212529");
	});
	$('input[class="form-control"]').blur(function(){
		$(this).css("color","");
		$(this).css("background-color","");
	});

	// 중복 검사
	$('.ckbtn').click(function(){
		var ckID = $(this).attr('id');
		var svar, url, title1, title2, body2, msg, id;
		// 아이디 중복확인
		if(ckID == 'idck'){
			url = '/www/account/idCk.nbs';
			title1 = '사용 가능한 아이디';
			title2 ='사용 중인 아이디';
			body2 = '다른 아이디를 시도해주세요.';
			msg = '#idmsg';
			id = '#id';
		}
		// 닉네임 중복확인
		else if(ckID == 'nicknameck'){
			url = '/www/account/nicknameCk.nbs';
			title1 = '사용 가능한 닉네임';
			title2 ='사용 중인 닉네임';
			body2 = '다른 닉네임을 시도해주세요.';
			msg = '#nicknamemsg';
			id = '#nickname';			
		}
		// 이메일 중복확인
		else if(ckID == 'mailck'){
			url = '/www/account/emailCk.nbs';
			title1 = '사용 가능한 이메일';
			title2 ='사용 중인 메일';
			body2 = '다른 이메일을 시도해주세요.';
			msg = '#emailmsg';
			id = '#email';
		}else{
			// 이메일 인증은 패스
			return;
		}
		svar = $(id).val();
		var body1= '남은 절차를 진행해주세요';
		
		// 빈칸 검사
		if(svar == ''){
			swal('내용을 입력해주세요', '', 'error');
			return;
		}
		// 유효성 검사
		if($(id).parent().parent().attr('class').indexOf('invalid') != -1){
			swal('형식에 맞춰 입력해주세요', '', 'error');
			return;				
		}
		
		$.ajax({
		url: url,
		type: 'post',
		dataType: 'json',
		data: {
			var: svar
		},
		success: function(data){
			var result = data.result;
			// 뷰에 보여주고
			if(result == 'OK'){
				// 입력한 아이디가 사용가능한 경우
				swal(title1,body1,'success')
				.then(function(){
						$(msg).css('color', 'blue');
						$(msg).html('확인되었습니다.');
					});
				} else {
					// 입력한 아이디가 사용불가능한 경우
					swal(title2, body2,'error')
					.then(function(){
						$(msg).css('color', 'red');
						$(msg).html('중복되었습니다.');
				});
				}   
			},
			error: function(){
				swal('통신 에러!','다시 진행해주세요.','error');
			}
		});
	});

	// 유효성 검사 이펙트
	$('input[type="text"],input[type="password"]').keyup(function(){
		const inCss = $(this).parent().parent();
		var inVal = $(this).val();
		var pattern = $(this).attr('pattern');

		// 비밀번호 확인의 경우
		if($(this).attr('name') == 'pw'){
			let pwCss = $('#repw').parent().parent();
			var pw = $('#pw').val();
			var ckpw = $('#repw').val();
			
			if(pw!=ckpw){
				pwCss.addClass('invalid');
				pwCss.removeClass('valid');
			}else{
				pwCss.addClass('valid');
				pwCss.removeClass('invalid');
			}
		}
		if($(this).attr('name') =='repw'){
			var pw = $('#pw').val();
			var ckpw = $('#repw').val();
			
			if(pw!=ckpw){
				inCss.addClass('invalid');
				inCss.removeClass('valid');
			}else{
				inCss.addClass('valid');
				inCss.removeClass('invalid');
			}
		}
		// 일반 유효성 검사
		if($(this).attr('name') != 'repw'){
			$('#'+$(this).attr('id')+'msg').html('');
			if(inVal.match(pattern)){
				inCss.addClass('valid');
				inCss.removeClass('invalid');
			}else{
				inCss.addClass('invalid');
				inCss.removeClass('valid');				
			}
		}
		
		// 값이 비어있으면 invalid
		if(inVal == ''){
			inCss.addClass('invalid');
			inCss.removeClass('valid');
		}
	});
	
	$('#mbtn').click(function(){
		if($('#emailmsg').html() == '확인되었습니다.' && $('#mailCss').attr('class') == 'valid'){
			var smail = $('#email').val();
			$.ajax({
			url: '/www/account/certifiMail.nbs',
			type: 'post',
			dataType: 'json',
			data: {
				email: smail
			},
			success: function(data){
				var result = data.result;
				if(result == 'OK'){
					// 메일 전송
					swal('메일 전송 완료','해당 메일에서 확인해주세요.','success')
					.then(function(){
							return;
						});
					} else {
						swal('메일 전송 실패', '다시 시도해주세요.','error')
						.then(function(){
							return;
					});
					}   
				},
				error: function(){
					swal('통신 에러!','다시 진행해주세요.','error');
				}
			});
		}else{
			swal('이메일 인증 불가', '이메일 형식과 Check를 확인해주세요', 'error');
			return;
		}
	});

	// 회원가입 버튼 클릭
	$('#jbtn').click(function(){
		// 유효성 통과 못한 부분으로 이동
		var arrValid = $('input').not('input[type="radio"],input[type="hidden"]').get();
		for(var i = 0; i<arrValid.length; i++){
			const valCss = $(arrValid[i]).parent().parent();
			if(valCss.attr('id') == 'mailCss'){
				if(valCss.attr('class') == null){
					$(arrValid[i]).focus();
					return;
				}
			}else{
				if( (valCss.attr('class') == 'form-Css')
					|| (valCss.attr('class').indexOf('invalid') != -1)){
					$(arrValid[i]).focus();
					return;
				}
			}
		}
		if($('div[class*="invalid"]').attr('class') == "invalid"){
			$('#email').focus();
			return;
		}

		// id, 닉네임, 이메일 유효성 + 중복 검사
		if($('#idmsg').html() != '확인되었습니다.'
		|| $('#nicknamemsg').html() != '확인되었습니다.'
		|| $('#emailmsg').html() != '확인되었습니다.'){
			return;
		}
		
		// 타입을 고르지 않았다면
		if($('#type').val() == ''){
			return;
		}

		// 메일 인증 했는지 확인
		var smail = $('#email').val();
		$.ajax({
			url: '/www/account/checkOkMail.nbs',
			type: 'post',
			dataType: 'json',
			data: {
				email: smail
			},
			success: function(data){
				var result = data.result;
				if(result == 'OK'){
					$('#repw, input[type="radio"]').prop('disabled');
					$('#frm').submit();
				}else{
					swal('오류 발생!','다시 진행해주세요.','error');
				}
			},
			error: function(){
					swal('통신 에러!','다시 진행해주세요.','error');
				}
		});
	});

});
