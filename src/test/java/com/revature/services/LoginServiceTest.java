package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LoginServiceTest {

	public static LoginService ls = new LoginService();
	
	@Test public void checkEncrypt1() throws Exception {
		assertEquals("4��m�7!���� bC��fs�(", LoginService.encrypt("testPassword1235^", "Alex"));
	}
	
	@Test public void checkEncrypt2() throws Exception {
		assertEquals("\f$�!��l�", LoginService.encrypt("1", "1"));
	}
	
	@Test public void checkEncrypt3() throws Exception {
		assertEquals("Q5��m�9", LoginService.encrypt("pass", "Kulikov"));
	}
	
	@Test public void checkDecrypt1() throws Exception {
		assertEquals("LV21bps", LoginService.decrypt("��4���", "Kulikov"));
	}
	
	@Test public void checkDecrypt2() throws Exception {
		assertEquals("gT6pAeH8oXia", LoginService.decrypt("�ep��� �I���6 �I", "Kulikov"));
	}
	
	@Test public void getLoginWithCorrectData1() {
		assertTrue(ls.login("employee", "Q5��m�9"));
	}
	
	@Test public void getLoginWithCorrectData2() throws Exception {
		assertTrue(ls.login("employee", LoginService.encrypt("pass", "Kulikov")));
	}
	
	@Test public void getLoginWithCorrectData3() throws Exception {
		assertTrue(ls.login("doakton1", LoginService.encrypt("LV21bps", "Kulikov")));
	}
	
	
}
