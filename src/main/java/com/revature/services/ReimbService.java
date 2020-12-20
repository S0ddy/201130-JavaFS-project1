package com.revature.services;

import java.util.List;

import com.revature.models.Reimb;
import com.revature.repos.LoginDAOImpl;
import com.revature.repos.ReimbDAO;
import com.revature.repos.ReimbDAOImpl;
import com.revature.repos.loginDAO;

public class ReimbService {

	private static ReimbDAO rd = new ReimbDAOImpl();

	public boolean createReimb(Reimb reimb) {
		int reimbCreated = rd.createReimb(reimb);
		if(reimbCreated != 0) 
			return true;
		return false;
	}

	public List<Reimb> getReimbByEmployeeId(int id) {
		return rd.getReimbByEmployeeId(id);
	}

	public List<Reimb> getAllReimb() {
		return rd.getAllReimb();
	}

	public boolean reimbDeny(int reimbId, int managerId) {
		int reimbDeny = rd.reimbDeny(reimbId, managerId);
		if(reimbDeny != 0) 
			return true;
		return false;
	}

	
}
