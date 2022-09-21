$(() => {
	$('#loginBtn').on('click', () => {
		
		const param = {}
		param.user_nickname = $('#user_nickname').val();
		param.user_pw = $('#user_pw').val(); 

		//alert(param.user_nickname);

		axios({
			method: 'post',
			url: '/user/login.do',
			data: param,
			dataType: 'json'
		}).then(res => {
			console.log(res);
		
			openPopup("안내",res.data.msg)
			if(res.data.success === "Y" ) {
				location.href='/';
			}
			
		}).catch(err => {
			//에러가 났을경우
		})
	})

})