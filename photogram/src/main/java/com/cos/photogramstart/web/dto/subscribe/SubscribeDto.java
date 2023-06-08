package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {
	// 구독 정보 DTO
	
	private int userId; // 다른 유저에 대한 Id
	private String username;
	private String profileImageUrl;
	private Integer subscribeState;
	private Integer equalUserState;
}
