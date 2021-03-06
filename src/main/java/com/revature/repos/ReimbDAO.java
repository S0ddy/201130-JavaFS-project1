package com.revature.repos;

import java.util.List;

import com.revature.models.Reimb;

public interface ReimbDAO {

//	public Reimb getReimbById(int id);
//	public List<Reimb> getReimbs();
	public int createReimb(Reimb r);

	public List<Reimb> getReimbByEmployeeId(int id);

	public List<Reimb> getAllReimb();

	public Reimb getReimbById(int id);

	public int reimbUpdate(Reimb reimb2);

	public List<Reimb> getReimbByStatus(int status);
}
