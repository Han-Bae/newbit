$(document).ready(function(){
		// 사용자 타입 설정
	$('input[type="radio"]').change(function(){
		if($(this).attr('id') == 'option-1'){
			$(this).attr('value','g');
		}else{
			$(this).attr('value','d');
		}
		console.log($(this).val());
	});
});