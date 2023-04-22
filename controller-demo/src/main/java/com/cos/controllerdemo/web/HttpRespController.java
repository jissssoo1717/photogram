package com.cos.controllerdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // File을 리턴할 것임
public class HttpRespController {

	@GetMapping("/txt")
	public String txt() {
		
		return "a.txt"; // file 안의 내용을 브라우저가 읽어옴
							  // 프레임 워크 사용 -> 일반 정적 파일들은 resource/static 폴더가 디폴트 경로임
	}
	
	
	@GetMapping("/mus")
	public String mus() {
		
		return "b"; // mustache 템플릿 엔진 라이브러리 등록 완료
						 // => templates 폴더 안에 .mustache를 두면 
						 // 확장자 없이 파일명만 적어도 자동으로 찾아감.
	}
	
	
	@GetMapping("/jsp")
	public String jsp() {
		
		return "c"; // jsp 엔진 사용 => src/main/webapp 폴더가 디폴트 경로
						 // /WEB-INF/views/c.jsp (ViewResolver)
	}
}
