package com.petshop.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public UserEntity insertUser(UserDTO userDto) {
		UserEntity userEntity = UserEntity
				.builder()
				.user_addr_1(userDto.getUser_addr_1())
				.user_addr_2(userDto.getUser_addr_2())
				.user_del_date(userDto.getUser_del_date())
				.user_del_yn(userDto.getUser_del_yn())
				.user_email(userDto.getUser_email())
				.user_nickname(userDto.getUser_nickname())
				.user_name(userDto.getUser_name())
				.user_id(userDto.getUser_id())
				.user_pet_type(userDto.getUser_pet_type())
				.user_pw(userDto.getUser_pw())
				.user_reg_date(userDto.getUser_reg_date())
				.user_tel(userDto.getUser_tel())
				.build();
		
		return userRepository.save(userEntity);
	}
	
	//카카오 로그인 API
	@ResponseBody
	public UserEntity kakaoLoginAPI(@RequestBody String code, UserDTO userDto,HttpSession session) {
		 //POST방식으로 Key = value 데이터를 요청 (카카오쪽으로)
		 //<a></a> 태그를 이용하여 전달하는 방식은 무조건 - GET방식이다.
		 //http요청을 편하게 할 수있다  과거에서는 HttpsURLConnection url = new
		 //HttpsURLConnection(){}
		 //Retrofit2 OkHttp RestTemplate <== Http요청 라이브러리
		RestTemplate rt = new RestTemplate();
		
		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders(); // 헤더에 내가 요청한 데이터가 key : value 형태라는 것을 알려주는것
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "4cf0f2697c4d4da3d92fd94cda7f307c");
		params.add("redirect_uri", "http:localhost:8080/user/kakaoLogin");
		params.add("code", code);
		
		// HttpHeader 와 Httpbody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenReq = new HttpEntity<>(params, headers);

		// Http 요청하기 - Post방식으로 & response 변수의 응답받음.
		ResponseEntity<String> response = rt.exchange("https:kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenReq, String.class);
		// 토큰발급요청주소 ,요청메서드, HttpBody + HttpHeader값 데이터, 타입(응답 - String으로 응답받음)

		// Gson, Json Simple, ObjectMapper
		ObjectMapper objMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		
		try {
			oauthToken = objMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		RestTemplate rt2 = new RestTemplate();
		
		// HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// 1.HttpHeader 와 Httpbody를 하나의 오브젝트에 담기
		// 2.Http 요청하기 - Post방식으로 & response 변수의 응답받음.
		// 3.Gson, Json Simple, ObjectMapper
		HttpEntity<MultiValueMap<String, String>> kakaoProfileReq2 = new HttpEntity<>(headers2);
		ResponseEntity<String> response2 = rt2.exchange(
				"https:kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileReq2,
				String.class
				);	
		
		
		ObjectMapper objMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//UUID 중복되지않는 특정 값을 만들어내는 알고리즘
		//UUID garbagePW = UUID.randomUUID();

		System.out.println("1. 카카오 인증완료 코드값 : " + code);
		System.out.println("2. 카카오엑세스토큰 : " + oauthToken.getAccess_token());
		System.out.println("3. 응답받은 바디값 : " + response.getBody());
		System.out.println("4. JAVA Obj로 변경할 Json 데이터" + response2.getBody());
		
		System.out.println("카카오아이디(번호)  " + kakaoProfile.getId());
		System.out.println("카카오이메일 : " + kakaoProfile.getKakao_account().getEmail());
		System.out.println("카카오닉네임 : " + kakaoProfile.getProperties().getNickname());
		
		//User Obj
		System.out.println("DB서버 유저네임(닉네임+아이디번호) : "
				+ kakaoProfile.getProperties().getNickname() + "_" + kakaoProfile.getId());
		System.out.println("DB서버 이메일 : " + kakaoProfile.getKakao_account().getEmail());
		//System.out.println("DB서버 패스워드 : " + garbagePW);
		
		
		UserEntity userEntity = UserEntity
				.builder()
				.user_nickname( kakaoProfile.getProperties().getNickname() + "_" + kakaoProfile.getId())
				.user_pw("1234")
				.user_email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
		
		
		//로그인 후 세션에 아이디를 넣어서 가지고 오려면???
		// 로그인 후 팝업창 띄우기
		//userDto.setUser_nickname((String) session.getAttribute("user_nickname"));
		//session.setAttribute("user_nickname", userDto.getUser_nickname());
		
		System.out.println("잉???" + userEntity.getOauth());
		
		return userRepository.save(userEntity);
	}
	
	/**
	 * db insert = dto -> entity -> db
	 * db select = db -> entity -> dto -> controller -> 화면
	 */
	
	public UserDTO findByUserId(String user_nickname) {
		UserEntity list = userRepository.selectUser(user_nickname);
		if(list != null ) {
			UserDTO userDto = UserDTO
				.builder()
				.user_id(list.getUser_id())
				.user_nickname(list.getUser_nickname())
				.user_pw(list.getUser_pw())
				.build();
			return userDto;
		}
		return new UserDTO();
	}
	
}
