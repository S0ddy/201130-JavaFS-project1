package com.revature.repos;

import java.util.List;

import com.revature.models.Reimb;

public interface ReimbDAO {

//	public Reimb getReimbById(int id);
//	public List<Reimb> getReimbs();
	public int createReimb(Reimb r);

	public List<Reimb> getReimbByEmployeeId(int id);

	public List<Reimb> getAllReimb();

	public int reimbDeny(int reimbId, int managerId);
}
