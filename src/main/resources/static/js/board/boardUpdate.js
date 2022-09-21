$(() => {

	$('#updateBtn').on('click', () => {
	
		const param = {}
		param.board_id = $('#board_id').val();
		param.board_title = $('#board_title').val();
		param.board_content = $('#board_content').val();
		console.log(param)

		axios({
			method: 'post',
			url: '/board/boardUpdate',
			data: param,
			dataType: 'json'
		}).then(res => {
			console.log(param)
			alert("수정완료");
			location.href = '/board/boardList'
		})

	})

})
