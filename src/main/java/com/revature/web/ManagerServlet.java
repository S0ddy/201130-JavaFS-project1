package com.revature.web;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controller.LoginController;
import com.revature.controller.ManagerController;


public class ManagerServlet extends HttpServlet {
	ManagerController mc = new ManagerController();
	LoginController lc = new LoginController();

	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		res.setStatus(404); //We override it, if everything is okay
		final String URI = req.getRequestURI().replace("/project-1/manager/", "");

		switch(URI) { 
		case "check-session":
			if(req.getSession(false).getAttribute("userRole").equals("Manager"))
				res.setStatus(200);
			else 
				res.setStatus(403);
			break;
		case "all-reimb":
			mc.getAllReimb(req, res);
			break;
		case "logout":
			lc.logout(req, res);
			break;
		}
		
	}
	
	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		res.setStatus(404); //We override it, if everything is okay
		final String URI = req.getRequestURI().replace("/project-1/manager/", "");

		switch(URI) { 
		case "change-status":
			mc.changeStatus(req, res);
			break;
		case "reimb-by-status":
			mc.getReimbByStatus(req, res);
			break;
		}
		
	}
}
