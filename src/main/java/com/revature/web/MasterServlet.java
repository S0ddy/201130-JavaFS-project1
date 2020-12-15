package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controller.LoginController;

public class MasterServlet extends HttpServlet {

	private LoginController lc = new LoginController();

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("application/json");

		res.setStatus(404); // We override it, if everything is okay

		final String URI = req.getRequestURI().replace("/project-1/", "");

		if (URI.equals("login"))
			lc.login(req, res);

	}
}
