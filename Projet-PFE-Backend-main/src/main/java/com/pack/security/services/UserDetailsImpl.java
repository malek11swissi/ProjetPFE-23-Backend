package com.pack.security.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pack.models.User;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String telephone;

	@JsonIgnore
	private String password;

	private boolean active ;
      /*Collection autorisé role de user */
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String username, String telephone, String password,boolean active,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.telephone = telephone;
		this.password = password;
		this.active = active;
		// Autorisation par son role 
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user) {

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
		

		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(), 
				user.getTelephone(),
				user.getPassword(), 
				user.isActive(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	public String getTelephone() {
		return telephone;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return active;
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
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
