package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.web.dto.auth.CMRespDto;

@RestController // Return data
@ControllerAdvice // 모든 Exception 낚아챔
public class ContollerExceptionHandler {

	// 모든 RuntimeException을 낚아챔
	@ExceptionHandler(CustomValidationException.class)
	public CMRespDto<?> validationException(CustomValidationException e) {
		return new CMRespDto<Map<String, String>>(-1, e.getMessage(), e.getErrorMap());
	}
}
