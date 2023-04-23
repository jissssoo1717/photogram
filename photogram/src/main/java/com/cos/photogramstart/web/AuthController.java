package com.cos.photogramstart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor // final 필드를 DI할 때 사용
@Controller // 1.IoC 등록 2. File 리턴하는 컨트롤러
public class AuthController {
	
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	private final AuthService authService;

	/* 의존성 주입
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	 */
	
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
		// User <- SignupDto에 있는 데이터 넣기
		
		User user = signupDto.toEntity();
		log.info(user.toString());
		
		User userEntity = authService.회원가입(user);
		System.out.println(userEntity);
		
		return "auth/signin";
	}
	
}
