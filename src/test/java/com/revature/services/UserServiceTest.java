package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.revature.models.User;

public class UserServiceTest {

	public static UserService us = new UserService();
	
	@Test public void testAllUsers() {
		assertNotNull(us.getAllUsers());
	}
	
	@Test public void testGetUser1() {
		assertNotNull(us.getUserById(5));
	}
	
	@Test public void testGetUser2() throws Exception {
		User user = new User(6, "wpaolacci5", LoginService.encrypt("gdUOj7coH", "Kulikov"), "Ware", "Paolacci", "wpaolacci5@ebay.co.uk", "Manager");
		assertEquals(user, us.getUserById(6));
	}
	
	@Test public void testGetEmployees() throws Exception {
		assertNotNull(us.getEmployees());
	}
	
	@Test public void testGetManagers() throws Exception {
		assertNotNull(us.getManagers());
	}
	
}
