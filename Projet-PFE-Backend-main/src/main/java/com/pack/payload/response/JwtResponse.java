package com.pack.payload.response;

import java.util.List;

public class JwtResponse {
	private String token;
	/* token par entete authorization  */
	private String type = "Bearer";
	private Long id;
	private String username;
	private String telephone;
	private String role;


	public JwtResponse(String accessToken, Long id, String username, String telephone, String role) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.telephone = telephone;
		this.role = role;
	
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	
	
}
