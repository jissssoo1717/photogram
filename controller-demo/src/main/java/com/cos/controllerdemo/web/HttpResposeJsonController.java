package com.cos.controllerdemo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.controllerdemo.domain.User;


// Chrome 확장 프로그램의 json viewer를 통해 브라우저에 출력
@RestController
public class HttpResposeJsonController {

	@GetMapping("/resp/json")
	public String respJson() {
		
		return "{\"username\":\"cos\"}";
	}
	
	
	// 직접 데이터를 만들어서 응답함
	@GetMapping("/resp/json/object")
	public String respJsonObject() {
		
		User user = new User();
		user.setUsername("Gildong Hong");
		
		String data = "{\"username\":\""+user.getUsername()+"\"}";
		return data;
	}
	
	
	// user 오브젝트를 그대로 리턴함
	@GetMapping("/resp/json/javaobject")
	public User respJsonJavaObject() {
		
		User user = new User();
		user.setUsername("Gildong Hong");
		
		return user; 
		// 1. MessageConverter가 자동으로 JavaObject를 Json(구:xml)으로 변경해서 통신을 통해 응답을 해준다.
		// 2. @RestController일 때만 MessageConverter가 작동한다.
	}
	
}
