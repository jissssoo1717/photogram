package com.cos.photogramstart.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
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
		
		if(e.getErrorMap() == null) {
			return Script.back(e.getMessage());
		}
		else {
			return Script.back(e.getErrorMap().toString()); // JavaScript 응답
		}
	}
	
	@ExceptionHandler(CustomException.class)
	public String exception(CustomException e) {
		return Script.back(e.getMessage());
	}
	
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<?> validationApiException(CustomValidationApiException e) {

		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST); // Data 응답
	}
	
	@ExceptionHandler(CustomApiException.class)
	public ResponseEntity<?> apiException(CustomApiException e) {

		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST); // Data 응답
	}
}
