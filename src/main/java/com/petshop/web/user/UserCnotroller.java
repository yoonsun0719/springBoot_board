package com.petshop.web.user;


import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "user")
public class UserCnotroller {
	
	@Autowired
	UserService userService;
	
	//index 화면
	@RequestMapping(value = "user{pageName}")
	public ModelAndView regHome(String pageName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/user" + pageName);
		return mv;
	}
	
	
	@RequestMapping(value = "reg.do", method = { RequestMethod.POST })
	public ModelAndView insertRegData(@RequestBody UserDTO userDTO, String user_nickname ) {
		UserDTO userInfo = userService.findByUserId(user_nickname);
	
		ModelAndView mv = new ModelAndView();

		System.out.println("유저아이디" + "" + userDTO.getUser_id());
		System.out.println("유저비밀번호" + "" + userDTO.getUser_pw());
		System.out.println("유저이메일" + "" + userDTO.getUser_email());
		System.out.println("유저이름" + "" + userDTO.getUser_name());
		System.out.println("유저주소1" + "" + userDTO.getUser_addr_1());
		System.out.println("유저주소2" + "" + userDTO.getUser_addr_2());
		System.out.println("유저전화번호" + "" + userDTO.getUser_tel());
		System.out.println("유저펫타입" + "" + userDTO.getUser_pet_type());
		System.out.println("유저삭제일" + "" + userDTO.getUser_del_yn());
		System.out.println("유저가입일" + "" + userDTO.getUser_reg_date());
		
		userService.insertUser(userDTO);
		
		System.out.println("쳬크" + "" + userDTO);
		
		mv.setViewName("/user/userLogin");
		return mv;
	}
	
	@RequestMapping(value = "login.do", method = { RequestMethod.POST })
	@ResponseBody
	public HashMap<String, Object> login(@RequestBody UserDTO param, Model model, HttpSession session) {
		HashMap<String, Object> resMap = new HashMap<String, Object>();		
		
		UserDTO userDTO = new UserDTO();
		UserDTO userInfo = userService.findByUserId(param.getUser_nickname());
		String userInfoUser_nickname = userInfo.getUser_nickname();
		String userInfoUser_pw = userInfo.getUser_pw();
		String paramUser_nickname = param.getUser_nickname();
		String paramUser_pw = param.getUser_pw();
		
		
		System.out.println(".............등록된아이디" + "  " + userInfoUser_nickname);
		System.out.println(".............입력아이디" + "  " + paramUser_nickname);
		System.out.println(".............등록된패스워드" + "  " + userInfoUser_pw);
		System.out.println(".............입력패스워드" + "  " + paramUser_pw);
		//System.out.println(".............33" + userInfoUser_id.equals(paramUser_id));
		
		if(userInfoUser_nickname == null) {
			resMap.put("msg", "아이디가 존재하지 않습니다.");
		 } else if(paramUser_pw.equals("")){
			 resMap.put("msg", "비밀번호를 입력하세요");
		 } else if(!userInfo.getUser_pw().equals(paramUser_pw)) {
			 resMap.put("msg", "비밀번호가 일치하지 않습니다.");
		 } else if(userInfo.getUser_nickname().equals(paramUser_nickname) && userInfo.getUser_pw().equals(paramUser_pw)){
			 resMap.put("msg", "로그인성공Controller");
			 resMap.put("userInfo", userInfo);
			 resMap.put("success", "Y");
			 // 세션
			 session.setAttribute("user_nickname", userInfo.getUser_nickname());
			 session.setAttribute("user_id", userInfo.getUser_id());
		 }
		return resMap;	
	}
	
	@RequestMapping(value = "logOut.do")
	public ModelAndView logOut(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		session.removeAttribute("user_nickname");
		mv.setViewName("redirect:/");
		return mv;
	}
}
