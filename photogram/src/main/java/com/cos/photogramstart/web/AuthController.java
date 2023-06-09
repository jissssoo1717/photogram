package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
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
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { // key=value (x-www-form-urlencoded)
		// @Controller로 등록해놔도 @ResponseBody이면 파일이 아닌 데이터 리턴함
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				
			}
			throw new CustomValidationException("유효성 검사 실패", errorMap);
		}
		else {
			log.info(signupDto.toString());
			// User <- SignupDto에 있는 데이터 넣기
		
			User user = signupDto.toEntity();
			log.info(user.toString());
			
			User userEntity = authService.회원가입(user);
			System.out.println(userEntity);
			
			return "auth/signin";
		}
	}
	
}
