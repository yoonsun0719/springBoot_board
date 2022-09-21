$(() => {
	$('#searchType').on('change', () => {
		if($('#searchType').val() == "1") {
			$('#searchData').attr("name", "board_writer")
		}else if($('#searchType').val() == "2") {
			$('#searchData').attr( "name", "board_title")
		}else if($('#searchType').val() == "3") {
			$('#searchData').attr("name", "board_content")
		}
	})
	
		
	$('#searchBtn').on('click', ()=> {
		const param = {}
		param.type = $('#searchType').val();
		param.searchData = $('#searchData').val();
		
		axios({
			method: 'post',
			url: '/board/boardSearch',
			data:param,
			dataType : 'json'
		}).then(res=> { 
			$('#boardList').empty();
			for (let i = 0; i < res.data.length; i++) {
				let div = `<div class="leftSet flexSet listATag"> 
								<a href="/board/boardRead?edit=0&boardId=${res.data[i].boardId}">
									<div class="listTitle_2 ma-lr-px10"> ${res.data[i].boardTitle} </div>
								</a>
								<div class="boardListDate"> ${res.data[i].board_reg_date} </div>
								<div class="boardListDate"> ${res.data[i].board_see} </div>
							</div>
							<hr/>`
				console.log(div.length)
        		$('#boardList').append(div);
        }
		}).catch(err => {
		})
	})
})
