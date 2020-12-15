package com.revature.repos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	
	public List<User> getUsers();
	public List<User> getEmployees();
	public List<User> getManagers();
	public User getUserByLogin(String login);
	
}
