package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	
	private final ImageRepository imageRepository;
	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Transactional // (트랜잭션: 일의 최소 단위 여러 가지) INSERT 혹은 UPDATE가 있을 경우
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		UUID uuid = UUID.randomUUID(); // UUID : 네트워크 상에서 고유성이 보장되는 id를 만들기 위한 표준 규약
		String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename(); // 1.jpg
		System.out.println("이미지 파일 이름: " + imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		
		
		// 통신, I/O => 예외가 발생할 수 있다.
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// image 테이블에 저장
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser()); // DB에 저장
		imageRepository.save(image);
		
		// System.out.println(imageEntity); // user 무한 참조 조심!
		
	}
}
