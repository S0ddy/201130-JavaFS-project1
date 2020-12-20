package com.revature.controller;

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

	public void reimbDeny(HttpServletRequest req, HttpServletResponse res) {

		if (req.getMethod().equals("GET")) {

			// Get manager id
			HttpSession ses = req.getSession();
			StringBuilder sbId = new StringBuilder();
			sbId.append(ses.getAttribute("userId"));
			String sId = new String(sbId);
			int managerId = 0;
			try {
				managerId = Integer.parseInt(sId);;
			} catch (NumberFormatException e) {
				res.setStatus(401);
			}

			// Get reimbursement id
			String URI = req.getRequestURI().replace("/project-1/manager/deny/", "");
			int reimbId = 0;
			try {
				reimbId = Integer.parseInt(URI);
			} catch (NumberFormatException e) {
				res.setStatus(401);
			}

			// Deny 'reimbursement record' in table
			if (rs.reimbDeny(reimbId, managerId)) {
				res.setStatus(200);
			} else {
				res.setStatus(401);
			}

		}

	}

}
