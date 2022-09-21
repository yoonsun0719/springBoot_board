//$(document).ready(() => {
//		        $('.menu').children('.content').on('click', () => {
//            	let submenu = $(this).next('.content');
// 
// 				//접고 펼치기
//            	if( submenu.is(':visible') ){
//                	submenu.slideUp();
//            	}else{
//                	submenu.slideDown();
//            	}
//        	});
//    	});

$(()=> {
	$('#deleteBtn').on('click', () =>{
		const param = {}
		param.board_id = $('#board_id').val();
		console.log(param)
		
		
		if(confirm("삭제하시겠습니까?") == true) {
    		
			axios({
				method: 'post',
				url: '/board/boardDel',
				data:param,
				dataType : 'json'
			}).then(res => {
				console.log(param)			
				location.href = '/board/boardList'
			}).catch(err => {
				
			})
		}else{ return }
	})
	
	$('#editBtn').on('click', () => {
		const board_id=$('#board_id').val();
		location.href = `/board/boardRead?boardId=${board_id}&edit=1`
	})
	
});