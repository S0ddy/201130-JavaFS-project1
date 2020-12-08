package com.revature.services;

import java.util.List;

import com.revature.daos.UserDao;
import com.revature.daos.UserPostgres;
import com.revature.models.User;

public class UserService {
	
	private static UserDao ud = new UserPostgres();

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
