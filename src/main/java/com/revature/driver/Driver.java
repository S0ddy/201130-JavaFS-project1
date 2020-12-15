package com.revature.driver;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.services.UserService;
import com.revature.util.ConnectionUtil;

public class Driver {

	private static UserService us = new UserService();

	public static void main(String[] args) {

		// Connection
//		try {
//
//			Connection c = ConnectionUtil.getConnection();
//			String driverName = c.getMetaData().getDriverName();
//			System.out.println(driverName);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		**************** USERS ****************

		// get all users
//		System.out.println(us.getAllUsers());
		
		// get all employees
//		System.out.println(us.getEmployees());
		
		// get all managers
//		System.out.println(us.getManagers());
		


	}

}
