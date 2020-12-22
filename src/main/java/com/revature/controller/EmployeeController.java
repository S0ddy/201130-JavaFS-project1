package com.revature.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimb;
import com.revature.services.ReimbService;

public class EmployeeController {

	private ObjectMapper om = new ObjectMapper();
	private ReimbService rs = new ReimbService();
	private static Logger log = LogManager.getRootLogger();


	public void createReimbursement(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		if (req.getMethod().equals("POST")) {

			HttpSession ses = req.getSession();
			BufferedReader reader = req.getReader();
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();

			// Get user id
			StringBuilder sbId = new StringBuilder();
			sbId.append(ses.getAttribute("userId"));
			String sId = new String(sbId);
			int id = Integer.parseInt(sId);

			// add information from Form
			while (line != null) {
				sb.append(line);
				line = reader.readLine();
			}
			String body = new String(sb);
			Reimb reimb = om.readValue(body, Reimb.class);

			// add addition information
			reimb.setAuthor(id);
			reimb.setSubmitted(null);
			reimb.setStatus(1);

			// Update reimbursement record in table
			if (rs.createReimb(reimb)) {
				res.setStatus(200);
				log.info("Reimbursement created.");

			} else {
				res.setStatus(401);
				res.getWriter().print("Something went wrong...");
				log.warn("failure to create reimbursement");
			}
		}

	}

	public void getHistory(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		if (req.getMethod().equals("GET")) {

			HttpSession ses = req.getSession();

			// Get user id
			StringBuilder sbId = new StringBuilder();
			sbId.append(ses.getAttribute("userId"));
			String sId = new String(sbId);
			int id = Integer.parseInt(sId);
			
			List<Reimb> list = rs.getReimbByEmployeeId(id);
			String json = om.writeValueAsString(list);
			res.getWriter().print(json);
			res.setStatus(200);
		}
	}

	public void getEmplPending(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException  {
		
		if (req.getMethod().equals("GET")) {

			HttpSession ses = req.getSession();

			// Get user id
			StringBuilder sbId = new StringBuilder();
			sbId.append(ses.getAttribute("userId"));
			String sId = new String(sbId);
			int id = Integer.parseInt(sId);
			
			List<Reimb> list = rs.getReimbByEmployeeId(id);
			List<Reimb> list2 = new ArrayList<>();
			
			for(Reimb r : list) {
				if(r.getStatus()==1)
					list2.add(r);
			}
			
			String json = om.writeValueAsString(list2);
			res.getWriter().print(json);
			res.setStatus(200);
		}
		
	}
}
