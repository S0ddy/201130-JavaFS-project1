package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeServlet extends HttpServlet {
	
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		res.setStatus(404); //We override it, if everything is okay
		final String URI = req.getRequestURI().replace("/project-1/employee/", "");
		
		switch(URI) { 
		case "check-session":
			if(req.getSession(false)!= null)
				res.setStatus(200);
			else 
				res.setStatus(403);
			break;
		}
		
	}
}
