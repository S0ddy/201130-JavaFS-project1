package com.revature.services;

import com.revature.repos.LoginDAOImpl;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;
import com.revature.repos.loginDAO;

public class LoginService {
	
	private static loginDAO ld = new LoginDAOImpl();

	
	public boolean login(String username, String password) {
		return ld.login(username, password);
	}
}
