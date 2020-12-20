package com.revature.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimb;
import com.revature.services.ReimbService;

public class ManagerController {

	private ObjectMapper om = new ObjectMapper();
	private ReimbService rs = new ReimbService();

	public void getAllReimb(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		if (req.getMethod().equals("GET")) {

//			HttpSession ses = req.getSession();

			List<Reimb> list = rs.getAllReimb();
			String json = om.writeValueAsString(list);
			res.getWriter().print(json);
			res.setStatus(200);
		}

	}

	public void changeStatus(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		if (req.getMethod().equals("POST")) {

			HttpSession ses = req.getSession();
			BufferedReader reader = req.getReader();
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();

			// Get user id
			StringBuilder sbId = new StringBuilder();
			sbId.append(ses.getAttribute("userId"));
			String sId = new String(sbId);
			int managerId = Integer.parseInt(sId);

			// add information from json
			while (line != null) {
				sb.append(line);
				line = reader.readLine();
			}

			// get Status and id
			String body = new String(sb);
			Reimb reimb = om.readValue(body, Reimb.class);

			// create new object with an identifier known to us
			Reimb reimb2 = rs.getReimbById(reimb.getId());
			reimb2.setResolver(managerId);
			reimb2.setStatus(reimb.getStatus());

			// Update reimbursement record in table
			if (rs.reimbUpdate(reimb2)) {
				res.setStatus(200);
			} else {
				res.setStatus(401);
				res.getWriter().print("Login Failed");
			}

		}

	}

	public void getReimbByStatus(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		if (req.getMethod().equals("POST")) {

			BufferedReader reader = req.getReader();
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();
			
			

			// add information from json
			while (line != null) {
				sb.append(line);
				line = reader.readLine();
			}

			// get Status
			String body = new String(sb);
			Reimb reimb = om.readValue(body, Reimb.class);

			
			List<Reimb> list = rs.getReimbByStatus(reimb.getStatus());
			String json = om.writeValueAsString(list);
			res.getWriter().print(json);
			res.setStatus(200);
		}

	}

}
