$(() => {
	
	$('#boardInsertBtn').on('click', () => {
		//openPopup("등록성공", "게시글 등록을 완료했습니다")
		
		
		const param = {}
		param.board_title = $('#board_title').val()
		param.board_content = $('#board_content').val()
		
		 console.log(param);
		 
		 axios({
			method: 'post',
			url: '/board/boardInsert',
			data: param,
			dataType:'json'
		}).then(res => {
			//서버로 부터 응답받은 데이터가 res 입니다.
			openPopup("등록성공", "게시글 등록을 완료했습니다")
			$('#board_title').val('')
			$('#board_content').val('')
			location.href = '/board/boardList'
		}).catch(err => {
			
		})
		
	})
})
