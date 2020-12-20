package com.revature.web;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controller.ManagerController;


public class ManagerServlet extends HttpServlet {
	ManagerController mc = new ManagerController();

	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		res.setStatus(404); //We override it, if everything is okay
		final String URI = req.getRequestURI().replace("/project-1/manager/", "");
		
		Pattern patternDeny = Pattern.compile("deny");
		Pattern patternApprove = Pattern.compile("approve");

	    Matcher matcherDeny = patternDeny.matcher(URI);
	    Matcher matcherApprove = patternApprove.matcher(URI);

		switch(URI) { 
		case "check-session":
			if(req.getSession(false)!= null)
				res.setStatus(200);
			else 
				res.setStatus(403);
			break;
		case "all-reimb":
			mc.getAllReimb(req, res);
			break;
		}
		
		if (matcherDeny.find())
			mc.reimbDeny(req, res);
//		else if(matcherApprove.find())
//			mc.approveReimb(req, res);
	}
}
