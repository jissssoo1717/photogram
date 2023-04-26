package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.auth.CMRespDto;

@RestController // Return data
@ControllerAdvice // 모든 Exception 낚아챔
public class ContollerExceptionHandler {

	// 모든 RuntimeException을 낚아챔
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		
		// CMRespDto, Script 비교
		// 1. 클라이언트에게 응답할 때에는 Script가 좋다. -> 브라우저가 응답받기 때문
		// 2. Ajax 통신 -> CMRespDto
		// 3. Android 통신 -> CMRespDto
		return Script.back(e.getErrorMap().toString());
	}
}
