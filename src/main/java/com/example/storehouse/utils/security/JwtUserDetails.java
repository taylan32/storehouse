package com.example.storehouse.utils.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.storehouse.models.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtUserDetails implements UserDetails {

	public Long id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtUserDetails(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static JwtUserDetails create(User user, Collection<SimpleGrantedAuthority> authorities) {
		/*
		 * List<GrantedAuthority> authoritiesList = new ArrayList<>();
		 * authoritiesList.add(new SimpleGrantedAuthority("admin")); return new
		 * JwtUserDetails(user.getId(), user.getEmail(), user.getPassword(),
		 * authoritiesList);
		 */
		return new JwtUserDetails(user.getId(), user.getEmail(), user.getPassword(), authorities);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
}
