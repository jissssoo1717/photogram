package com.cos.photogramstart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.web.dto.auth.SignupDto;

@Controller // 1.IoC 등록 2. File 리턴하는 컨트롤러
public class AuthController {
	
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	

	@GetMapping("/auth/signin")
	public String signinForm() {
		
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		
		return "auth/signup";
	}

	
	// 회원가입 버튼 -> /auth/signup -> /auth/signin
	// 회원가입 버튼 X -> csrf 토큰 활성화 때문임
	@PostMapping("/auth/signup")
	public String signup(SignupDto signupDto) { // key=value (x-www-form-urlencoded)
		
		log.info(signupDto.toString());
		
		return "auth/signin";
	}
	
}
