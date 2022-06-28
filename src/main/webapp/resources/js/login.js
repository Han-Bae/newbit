/**
 * 
 * @author	김태현
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.24	-	담당자 : 김태현
 * 								내	용 : js 제작
 				2022.05.26 	-	담당자 : 김태현
 								내  용 : 아이디 저장 구현(쿠키 사용)
 										, status에 따른 모달 창 처리작업
 */
 
$(document).ready(function(){
	// 회원가입 이동
	$("#join").click(function(){
		$(location).attr('href','/viseo/member/joinForm.blp');
	});
	
	// 쿠키값이 있으면 받아와서 id값으로 설정하고 
	var userId = getCookie("cookieUserId"); 
    $("#id").val(userId); 
    if($("#id").val() != ""){	// 값이 채워졌으면 체크
        $("input[name='remember']").attr("checked", true);
    }
     
    $("#signIn").click(function(){ // Login Form을 Submit할 경우,
        if($("input[name='remember']").is(":checked")){ // ID 기억하기 체크시 쿠키에 저장
            var userId = $("#id").val();
            setCookie("cookieUserId", userId, 7); // 7일동안 쿠키 보관
        } else {
            deleteCookie("cookieUserId");
        }
    });             
    
	
	// 비밀번호 체크
	$('#rpw, #rpwck').keyup(function(){
		var pw = $('#rpw').val();
		var ckpw = $('#rpwck').val();
		
		if(pw!=ckpw){
			$('#rpwmsg').html('* 비밀번호가 다릅니다.').css('color', 'red');
			$('#rpwbtn').prop('disabled', true);
		}else{
			$('#rpwmsg').html('* 비밀번호가 동일합니다.').css('color', 'blue');
			$('#rpwbtn').prop('disabled', false);
		}
	});
	
	// 모달 나가면 폼 초기화
	$('.subbtn, .logoBtn, .btn-close').click(function(){
		$('#rpwmsg').html('');
		$('#rpwbtn').prop('disabled', false);
		$('form').each(function(){
			this.reset();
		})
	});
	
	// 실패시 상태에 따른 모달창 띄우기
	var status = $("#status").val();
	switch (status){
		case 'refindId':
			// 아이디찾기 인증 실패시
			$('#fid').modal();
			break;
			// 비밀번호찾기 인증 실패시
		case 'refindPw':
			$('#fpw').modal();
			break;	
			// 비밀번호찾기 인증 성공시
		case 'refindPw_next':
			$('#rpwmd').modal();
			break;
			// 비밀번호 재설정 오류시
		case 'rePwInput':
			$('#rpwmd').modal();
			break;
	};

});

// 쿠키 사용 함수

function setCookie(cookieName, value, exdays){
    var exdate = new Date();
    exdate.setDate(exdate.getDate()+exdays);
    var cookieValue = escape(value)+((exdays==null)? "": "; expires="+exdate.toGMTString());
    document.cookie = cookieName+"="+cookieValue;
}

function deleteCookie(cookieName){
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate()-1);
    document.cookie = cookieName+"= "+"; expires="+expireDate.toGMTString();
}
function getCookie(cookieName){
    cookieName = cookieName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cookieName);
    var cookieValue = '';
    if(start != -1){
        start += cookieName.length;
        var end = cookieData.indexOf(';', start);
        if(end == -1) end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue);
     
}