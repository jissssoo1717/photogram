package com.cos.photogramstart.web.dto.auth;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data // Getter, Setter
public class CMRespDto<T> {

	private int code; // 1(Success), -1(Failed)
	private String message;
	private T data;
}
