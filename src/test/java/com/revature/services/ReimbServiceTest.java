package com.revature.services;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ReimbServiceTest {

	public static ReimbService rs = new ReimbService();
	
	@Test public void getAllReimbTest() {
		assertNotNull(rs.getAllReimb());
	}
	
}
