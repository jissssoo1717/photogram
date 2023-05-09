package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// JPA - Java Persistence API (자바로 데이터를 영구적으로 저장(DB)할 수 있는 API 제공)



@Builder
@AllArgsConstructor
@NoArgsConstructor // 빈 생성자
@Data
@Entity // DB에 테이블을 생성
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라감
	private int id;
	
	@Column(length = 20, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	private String website; // 웹 사이트
	private String bio; // 자기소개
	@Column(nullable = false)
	private String email;
	private String phone;
	private String gender;
	
	private String profileImageUrl; // 사진
	private String role; // 권한
	
	
	// 연관관계의 주인 아님 => 테이블에 Column 만들지 X
	// User SELECT할 때 해당 User id로 등록된 image들 다 가져옴
	// LAZY = User를 Select할 때 해당 User id로 등록된 image들을 무조건 가져오지 X => 대신 getImages() 함수의 image들이 호출될 때 가져오는것
	// Eager = User를 Select할 때 해당 User id로 등록된 image들을 전부 join해서 가져옴
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Image> images; // 양방향 매핑
	
	
	private LocalDateTime createDate;
	
	@PrePersist // DB에 INSERT 되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
