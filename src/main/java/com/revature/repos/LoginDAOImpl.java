package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class LoginDAOImpl implements loginDAO {

	@Override
	public boolean login(String username, String password) {
		// If this username exist inside DB, then check password.
		// If it's matched then return true, else false
		ResultSet rs = null;
		boolean res = false;
		
		String sql = "select * from ers_schema.ers_users where ers_users.ers_username = ? and ers_users.ers_password = ?;";
		
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			res = rs.next();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

}
