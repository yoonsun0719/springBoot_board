package com.petshop.web;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.petshop.web.user.UserDTO;
import com.petshop.web.user.UserService;


@Controller
public class MainController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	//카카오 로그인 API
	@RequestMapping("user/kakaoLogin")
	public String kakaoLogin(UserDTO userDto, String code, HttpSession session) { // data를 리턴해주는 컨트롤러 함수
		
		userService.kakaoLoginAPI(code, userDto,session);
		//String id = (String) session.getAttribute("user_nickname");
		//System.out.println("세션값이 로그인확인 : " + id);
		return "redirect:/";
	}
	

	@RequestMapping("/openPopup")
	public String openPopup(@RequestBody HashMap<String, Object> param, Model model) {
		model.addAttribute("title", param.get("title"));
		model.addAttribute("msg", param.get("msg"));
		return "common/popup/commonPopup";
	}
	
	@RequestMapping("/confirmPopup")
	public String openConfirmPopup(@RequestBody HashMap<String, Object> param, Model model) {
		model.addAttribute("title", param.get("title"));
		model.addAttribute("msg", param.get("msg"));
		return "common/popup/confirmPopup";
		
	}
}
