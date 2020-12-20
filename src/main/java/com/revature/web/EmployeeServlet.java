package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controller.EmployeeController;

public class EmployeeServlet extends HttpServlet {
	
	EmployeeController ec = new EmployeeController();

	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		res.setStatus(404); //We override it, if everything is okay
		final String URI = req.getRequestURI().replace("/project-1/employee/", "");
		
		switch(URI) { 
		case "check-session":
			if(req.getSession(false).getAttribute("userRole").equals("Employee"))
				res.setStatus(200);
			else 
				res.setStatus(403);
			break;
		case "history":
			ec.getHistory(req, res);
			break;
		case "pending":
			ec.getEmplPending(req, res);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		res.setStatus(404); //We override it, if everything is okay
		final String URI = req.getRequestURI().replace("/project-1/employee/", "");
		
		switch(URI) { 
		case "create-reimbursement":
			ec.createReimbursement(req, res);
			break;
		
		}
		
	}

}
