package com.revature.services;

import java.util.List;

import com.revature.models.User;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;

public class UserService {
	
	private static UserDAO ud = new UserDAOImpl();

	public List<User> getAllUsers() {
		return ud.getUsers();
	}
	
	public List<User> getEmployees() {
		return ud.getEmployees();
	}
	
	public List<User> getManagers() {
		return ud.getManagers();
	}


}
