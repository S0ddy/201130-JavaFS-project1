package com.revature.services;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LoginServiceTest {

	public static LoginService ls = new LoginService();
	
	@Test public void getLoginWithCorrectData1() {
		assertTrue(ls.login("employee", "pass"));
	}
	
	
}
