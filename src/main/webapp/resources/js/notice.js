/**
 * 
 * @author	김태현
 * @since	2022.07.21
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.07.21	-	담당자 : 김태현
 * 								내	용 : js 제작
 */
$(document).ready(function(){
	$('tr').not('tr[id="main"]').click(function(){
		$('#no').val($(this).find('td[class="no"]').html());
		$('#title').val($(this).find('td[class="title"]').html());
		$('#body').val($(this).find('input[name="body"]').val());
		$('#ischeck').val($(this).find('input[name="check"]').val());
		$('form').attr('action', 'noticeDetail.nbs');
		$('form').submit();
	});
});

function back(){
	$(location).attr('href', '/www/account/notice.nbs');
}