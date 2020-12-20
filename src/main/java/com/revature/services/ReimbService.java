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

	public Reimb getReimbById(int id) {
		return rd.getReimbById(id);
	}

	public boolean reimbUpdate(Reimb reimb2) {
		int reimbUpdate = rd.reimbUpdate(reimb2);
		if (reimbUpdate != 0) {
			return true;
		} else {
			return false;
		}
	}

	public List<Reimb> getReimbByStatus(int status) {
		return rd.getReimbByStatus(status);
	}

	
}
