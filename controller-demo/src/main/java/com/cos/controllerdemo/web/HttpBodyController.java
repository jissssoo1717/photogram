package com.cos.controllerdemo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.controllerdemo.domain.User;

@RestController
public class HttpBodyController {
	
	
	
	private static final Logger log = LoggerFactory.getLogger(HttpBodyController.class);

	
	@PostMapping("/body1")
	public String xwwwFormUrlEncoded(String username) {
		
		log.info(username); // 이름 정확해야함!
		return "key=value was sended";
	}
	
	@PostMapping("/body2")
	public String textPlane(@RequestBody String data) { // 평문
		
		log.info(data);
		return "text/plain was sended";
	}
	
	@PostMapping("/body3")
	public String applicationJson(@RequestBody String data) {
		
		log.info(data);
		return "json was sended";
	}
	
	@PostMapping("/body4")
	public String applicationJsonToObject(@RequestBody User user) {
		
		log.info(user.getUsername());
		return "json was sended";
	}
	
}
