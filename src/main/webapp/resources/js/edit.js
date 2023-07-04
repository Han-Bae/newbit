$(document).ready(function(){
	
		   var contentFull = true;
		   /*select option 날짜 생성 함수*/
		   $(document).ready(function(){            
			
		    var now = new Date();
		    var year = now.getFullYear();
		    var mon = (now.getMonth() + 1) > 9 ? ''+(now.getMonth() + 1) : '0'+(now.getMonth() + 1); 
		    var day = (now.getDate()) > 9 ? ''+(now.getDate()) : '0'+(now.getDate());           
		
		    //년도 selectbox만들기
		    for(var i = 1900 ; i <= year ; i++) {
		        $('#year').append('<option value="' + i + '">' + i + '년</option>');    
		    }
		
		    // 월별 selectbox 만들기            
		    for(var i=1; i <= 12; i++) {
		        var mm = i > 9 ? i : "0"+i ;             
		        $('#month').append('<option value="' + mm + '">' + mm + '월</option>');    
		    }
		    
		    // 일별 selectbox 만들기
		    for(var i=1; i <= 31; i++) {
		        var dd = i > 9 ? i : "0"+i ;            
		        $('#day').append('<option value="' + dd + '">' + dd+ '일</option>');    
		    }
		});
		
		
		
		// 비밀번호 정규식
		$(function(){
				$('#pw-text1').css('display', 'none');
				$('#Password1').keyup(function(){
				var Password1 = $('#Password1').val();
				var passwordRule = new RegExp('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,15}$');
			
				 if(Password1.length < 4 || Password1.length > 10){
					$('#pw-text1').html('*** 6 ~ 15자리이내로 입력해주세요. ***').css('color', 'gray');
					$('#pw-text1').css('display', 'block');
					
				 }else if(Password1.search(/\s/) != -1){
					$('#pw-text1').html('*** 비밀번호를 공백 없이 입력해주세요. ***').css('color', 'gray');
					$('#pw-text1').css('display', 'block');
				 }else{
					$('#pw-text1').css('display', 'none');
				}
			});
		}); 
		
		
		
		// 비밀번호 확인 이벤트
		$(function(){
			$('#checkbox').css('display', 'none');
			$('#Password2').keyup(function(){
				var password1 = $('#Password1').val();
				var password2 = $('#Password2').val();
				
				if(password1 == password2){
					$('#checkbox').html('*** 비밀번호가 같습니다. ***').css('color', 'gray');
					$('#checkbox').css('display', 'block');
				} else {
					$('#checkbox').html('*** 비밀번호가 다릅니다. ***').css('color', 'gray');
					$('#checkbox').css('display', 'block');
				}
			});
		});
		
		
		
		// 이메일 정규식
		$(function(){
			$('#email_check').css('display', 'none');
				$('#mail').keyup(function() {
					var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
					
					if(!emailRule.test($("#mail").val())) {            
						 	$('#email_check').css('color', 'gray').text("형식에 맞춰 입력해주세요.");
							$('#email_check').css('display', 'block');
				 	} else {
						$('#email_check').css('display', 'none');
					}
			});
		});
		
		
		
		// 핸드폰번호 정규식
		$(function(){
			$('#tel_check').css('display', 'none');
				$('#tel').keyup(function() {
					var postNoCheck = RegExp(/[0-9]{3}-[0-9]{3}/);
					
					if (!postNoCheck.test($("input[id='tel']").val())) {         
						 	$('#tel_check').css('color', 'gray').text("010-xxxx-xxxx 형식에 맞춰 입력해주세요.");
							$('#tel_check').css('display', 'block');
				 	} else {
						$('#tel_check').css('display', 'none');
					}
			});
		});
		
		
		
		$(function(){
			// exit 버튼
			$(".exit-btn").click(function(){
				$(location).attr("href", "/www/");
			});
			
			$.each($('#aname').find('option'),function(i,data) {
				if($('#hiddenAreaName').val() == $(data).val()) {
					$(data).prop("selected", true);
				}
			});
		
			// 지역 선택 이벤트 처리
		   $('#aname').change(function(){
				var sname = $(this).val();
				getCitylist(sname); 
			});
			
			 // 도시 선택 이벤트 처리
		   $('#city').change(function(){
				var cityName = $(this).val();
				$("#hiddenAddrNo").val(cityName);
			});
			
			$('.watchBirth').change(function(){
				birthBinding();
			});
			
			//최초 로딩할때 시티 셋팅
			getCityView();
			
			//최초 로딩할때 생일 셋팅
			birthSet();
		});
		
		
		
		function birthBinding(){
			  var yyyy = $('#year').val();
		      var mm = $('#month').val();
		      var dd = $('#day').val();
		      $('#birth').val(""+yyyy + mm + dd);
		      var birth = $("#birth").val();
		}
		
		
		
		function birthSet(){
		    var birth = $("#birth").val();
		    var yyyy = birth.substring(0, 4);
		     $("#year  > option[value="+yyyy+"]").attr("selected", "true"); 
		    var mm = birth.substring(4, 6);
		    $("#month  > option[value="+mm+"]").attr("selected", "true"); 
		    var dd = birth.substring(6, 8);
		    $("#day  > option[value="+dd+"]").attr("selected", "true"); 
		}
		
		
		
		
		function getCitylist(sname){	
				$.ajax({
					url: '/viseo/member/cityList.blp',
					type: 'POST',
					dataType: 'json',
					async : false, //비동기처리
					contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					data: {
						areaname: sname
					},
					success(data){
						$('#city').html('');
						$('#city').append('<option> 도시 선택 </option>');
						for(i = 0 ; i < data.length ; i++ ){
							var stag = '<option value="'+ data[i].addr +'">' + data[i].city + '</option>';
							console.log(stag);
							$('#city').append(stag);
						}
		
						$('#city').css('display', 'inline');
						$('#citymsg').css('display', 'inline');
					}
				});
		}
		
		
		
		function getCityView(){
			var addrNo = $("#hiddenAddrNo").val();
			var sname = $("#hiddenAreaName").val();
		
			getCitylist(sname); 
			
			$.each($('#city').find('option'),function(i,data) {
				if(addrNo == $(data).val()) {
					$(data).prop("selected", true);
				}
			});
		}

});
