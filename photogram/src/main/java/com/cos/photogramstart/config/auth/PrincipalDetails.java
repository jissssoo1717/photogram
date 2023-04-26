package com.cos.photogramstart.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;


@Data
public class PrincipalDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private User user;


	public PrincipalDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 권한 가져오는 함수(User의 Role) => 권한 한 개 이상일 수 있음
		Collection<GrantedAuthority> collector = new ArrayList<>();
		
		// 함수를 매개변수로 넘김 => 인터페이스 혹은 오브젝트 넘김 => 람다식으로 함수를 넘김
		collector.add(() -> {return user.getRole();});
		
		return collector;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정 만료
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		//계정 잠금
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호 변경
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 계정 활성화
		return true;
	}

}
