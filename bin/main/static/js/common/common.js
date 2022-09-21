$(() => {

	$('.test').on('click', () => {
		openPopup('공통팝업', '확인');
	})
	
	$('.confirmTest').on('click', ()=>{
		confirmPopup("공통확인팝업창","확인팝업",function(res){
			if(res == true){
				openPopup("공통확인팝업창", "공통확인팝업오픈")
			}//if close
		})//confirmPopup close
	})	
})


const openPopup = (getTitle, getMsg) => {
	axios({
		method: 'post',
		url: '/openPopup',
		data: { title: getTitle, msg: getMsg },
		dataType: 'json'
	}).then(res => {
		$('body').append(res.data)
		//$('.popupArea').hide()
		$('.popupArea').fadeIn(500)
	})	
}  


const confirmPopup = (getTitle, getMsg, callback) => {

	axios({
		method: 'post',
		url: '/confirmPopup',
		data: { title: getTitle, msg: getMsg },
		dataType: 'json'
	}).then(res => {
		$('body').append(res.data)
		$('.popupArea').hide()
		$('.popupArea').fadeIn(500)

		$('.popupCheckBtn').on('click', () => {
			$('.popupBlurArea').remove()
			callback(true)
		})
	})
}
