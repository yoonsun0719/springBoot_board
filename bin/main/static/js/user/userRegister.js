$(() => {
	$('#regCommit').on('click', () => {
		const param = {}
		const nowDate = new Date()
		const year = nowDate.getFullYear()
		const mon = nowDate.getMonth() + 1
		const day = nowDate.getDate()
		const now = `${year}-${mon}-${day}`

		param.user_nickname = $('#user_nickname').val() //==> UserEntity 데이터
		param.user_pw = $('#user_pw').val()
		param.user_email = $('#user_email').val()
		param.user_name = $('#user_name').val()
		param.user_addr_1 = $('#user_addr_1').val()
		param.user_addr_2 = $('#user_addr_2').val()
		param.user_tel = $('#user_tel').val()
		param.user_pet_type = $('input[name="user_pet_type"]:checked').val()
		param.user_del_yn = 'N'
		param.user_reg_date = now

		console.log($('input[name="user_pet_type"]:checked').val())

		console.log(param)
		
		axios({
			method: 'post',
			url: '/user/reg.do',
			data: param,
			dataType: 'json'
		}).then(res => {
			//서버로 부터 응답받은 데이터가 res 입니다
			openPopup("성공","회원가입성공")
			location.href='user?pageName=Login';	
		}).catch(err => {
			//에러가 났을경우 
		})
	})
})