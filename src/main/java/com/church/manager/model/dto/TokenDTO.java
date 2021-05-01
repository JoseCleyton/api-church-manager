package com.church.manager.model.dto;

public class TokenDTO {
	private String login;
	private String token;
	private boolean isAdmin;
	
	public TokenDTO(String login, String token, boolean isAdmin) {
		super();
		this.login = login;
		this.token = token;
		this.isAdmin = isAdmin;
	}
	
	public TokenDTO() {
		super();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
