package com.cos.photogramstart.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

/* 시큐리티 설정 파일 => "/auth/signin"이 POST 타입으로 들어오면
 * 								요청이 들어오는지 계속 주시함
 * 						=> POST /auth/signin 요청 들어오면 http Body에 두 개의 값 들고 옴(username, password)
 * 							두 값을 낚아챔 => IoC 컨테이너에 UserDetailsService 있음
 * 							=> UserDetailsService를 통해 두 값으로 로그인 진행
 * 
 * 		PrincipalDetailsService IoC에 등록해야 함
 *     => 부모가 같은 UserDetailsService 없애고 PrincipalDetailsService 등록됨
 *     		따라서 PrincipalDetailsService의 loadUserByUsername이 실행됨!!
 * */

@RequiredArgsConstructor
@Service // IoC
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	// Return이 잘 되면 자동으로 UserDetails 타입을 세션을 만든다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// password는 Security가 처리
		
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity == null) {
			return null;
		}
		else {
			return new PrincipalDetails(userEntity);
		}
	}

}
