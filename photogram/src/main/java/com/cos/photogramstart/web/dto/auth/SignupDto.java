package com.cos.photogramstart.web.dto.auth;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data // Getter, Setter
public class SignupDto {
	// Request dto
	
	/*
	 * DTO(Data Transfer Object)
	 *  => 계층 간 데이터 교환을 하기 위해 사용하는 객체
	 *  	  로직을 가지지 X, 순수한 데이터 객체(getter&setter만 가진 클래스)
	 *  	  해당 DTO를 받은 서버가 DAO를 이용하여 데이터베이스로 데이터 집어넣음
	 */
	
	
	// Front-end 뿐만 아니라 Back-end 에서도 막아야함 
	@Size(min = 2, max = 20)
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String email;
	@NotBlank
	private String name;
	
	public User toEntity() {
		
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
		
	}
}
