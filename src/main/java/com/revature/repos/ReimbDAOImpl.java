package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revature.models.Reimb;
import com.revature.util.ConnectionUtil;

public class ReimbDAOImpl implements ReimbDAO {

	@Override
	public int createReimb(Reimb r) {

		int reimbCreated = 0;
		String sql = "insert into ers_schema.ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) values (?,  CURRENT_DATE, null, ?, ?, null, 1, ?);";

		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setDouble(1, r.getAmount());
			ps.setString(2, r.getDescription());
			ps.setInt(3, r.getAuthor());
			ps.setInt(4, r.getType());

			reimbCreated = ps.executeUpdate();

		} catch (SQLException exc) {
			exc.printStackTrace();
		}

		return reimbCreated;
	}

	@Override
	public List<Reimb> getReimbByEmployeeId(int emplId) {
		
		List<Reimb> reimb = new ArrayList<>();
		String sql = "select * from ers_schema.ers_reimbursement where reimb_author = ?;";

		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, emplId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int reimbId = rs.getInt("reimb_id");
				double amount = rs.getDouble("reimb_amount");
				Date submitted = rs.getDate("reimb_submitted");
				Date resolved = rs.getDate("reimb_resolved");
				String description = rs.getString("reimb_description");
				int author = rs.getInt("reimb_author");
				int resolver = rs.getInt("reimb_resolver");
				int status = rs.getInt("reimb_status_id");
				int type = rs.getInt("reimb_type_id");
				reimb.add(new Reimb(reimbId, amount, submitted, resolved, description, author, resolver, status, type));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reimb;
	}

	@Override
	public List<Reimb> getAllReimb() {
		
		List<Reimb> reimb = new ArrayList<>();
		String sql = "select * from ers_schema.ers_reimbursement;";

		try (Connection c = ConnectionUtil.getConnection()) {
			
			Statement statement = c.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				int reimbId = rs.getInt("reimb_id");
				double amount = rs.getDouble("reimb_amount");
				Date submitted = rs.getDate("reimb_submitted");
				Date resolved = rs.getDate("reimb_resolved");
				String description = rs.getString("reimb_description");
				int author = rs.getInt("reimb_author");
				int resolver = rs.getInt("reimb_resolver");
				int status = rs.getInt("reimb_status_id");
				int type = rs.getInt("reimb_type_id");
				reimb.add(new Reimb(reimbId, amount, submitted, resolved, description, author, resolver, status, type));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reimb;
	}

	@Override
	public Reimb getReimbById(int id) {
		
		Reimb reimb = null;
		String sql = "select * from ers_schema.ers_reimbursement where reimb_id = ?;";

		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				double amount = rs.getDouble("reimb_amount");
				Date submitted = rs.getDate("reimb_submitted");
				Date resolved = rs.getDate("reimb_resolved");
				String description = rs.getString("reimb_description");
				int author = rs.getInt("reimb_author");
				int resolver = rs.getInt("reimb_resolver");
				int status = rs.getInt("reimb_status_id");
				int type = rs.getInt("reimb_type_id");
				reimb = new Reimb(id, amount, submitted, resolved, description, author, resolver, status, type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reimb;
	}

	@Override
	public int reimbUpdate(Reimb reimb2) {
		int reimbUpdate = 0;
		String sql = "update ers_schema.ers_reimbursement set reimb_resolved = CURRENT_DATE, reimb_resolver = ?, reimb_status_id = ? where reimb_id = ?";

		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, reimb2.getResolver());
			ps.setInt(2, reimb2.getStatus());
			ps.setInt(3, reimb2.getId());

			reimbUpdate = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reimbUpdate;
	}

	@Override
	public List<Reimb> getReimbByStatus(int status) {
		List<Reimb> reimb = new ArrayList<>();
		String sql = "select * from ers_schema.ers_reimbursement where reimb_status_id = ?;";

		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, status);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int reimbId = rs.getInt("reimb_id");
				double amount = rs.getDouble("reimb_amount");
				Date submitted = rs.getDate("reimb_submitted");
				Date resolved = rs.getDate("reimb_resolved");
				String description = rs.getString("reimb_description");
				int author = rs.getInt("reimb_author");
				int resolver = rs.getInt("reimb_resolver");
				int type = rs.getInt("reimb_type_id");
				reimb.add(new Reimb(reimbId, amount, submitted, resolved, description, author, resolver, status, type));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reimb;
	}

}
