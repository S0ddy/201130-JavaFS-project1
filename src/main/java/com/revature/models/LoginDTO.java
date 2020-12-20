package com.revature.models;

public class LoginDTO {
	
	private String username;
	private String password;

	public LoginDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public LoginDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
}
