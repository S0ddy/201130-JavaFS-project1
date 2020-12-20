package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDAOImpl implements UserDAO {

	@Override
	public List<User> getUsers() {

		List<User> users = new ArrayList<>();
		String sql = "SELECT ers_users_id, ers_users.ers_username, ers_users.ers_password, ers_users.ers_first_name, ers_users.ers_last_name, ers_users.user_email, ers_user_roles.user_role FROM ers_schema.ers_users INNER JOIN ers_schema.ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id";

		try (Connection c = ConnectionUtil.getConnection()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("ers_users_id");
				String userName = rs.getString("ers_username");
				String password = rs.getString("ers_password");
				String firstName = rs.getString("ers_first_name");
				String lastName = rs.getString("ers_last_name");
				String email = rs.getString("user_email");
				String userRole = rs.getString("user_role");
				users.add(new User(id, userName, password, firstName, lastName, email, userRole));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public List<User> getEmployees() {

		List<User> employees = new ArrayList<>();
		String sql = "SELECT ers_users_id, ers_users.ers_username, ers_users.ers_password, ers_users.ers_first_name, ers_users.ers_last_name, ers_users.user_email, ers_user_roles.user_role FROM ers_schema.ers_users INNER JOIN ers_schema.ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id where ers_users.user_role_id = 1";

		try (Connection c = ConnectionUtil.getConnection()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("ers_users_id");
				String userName = rs.getString("ers_username");
				String password = rs.getString("ers_password");
				String firstName = rs.getString("ers_first_name");
				String lastName = rs.getString("ers_last_name");
				String email = rs.getString("user_email");
				String userRole = rs.getString("user_role");
				employees.add(new User(id, userName, password, firstName, lastName, email, userRole));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employees;
	}

	@Override
	public List<User> getManagers() {
		
		List<User> managers = new ArrayList<>();
		String sql = "SELECT ers_users_id, ers_users.ers_username, ers_users.ers_password, ers_users.ers_first_name, ers_users.ers_last_name, ers_users.user_email, ers_user_roles.user_role FROM ers_schema.ers_users INNER JOIN ers_schema.ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id where ers_users.user_role_id = 2";

		try (Connection c = ConnectionUtil.getConnection()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("ers_users_id");
				String userName = rs.getString("ers_username");
				String password = rs.getString("ers_password");
				String firstName = rs.getString("ers_first_name");
				String lastName = rs.getString("ers_last_name");
				String email = rs.getString("user_email");
				String userRole = rs.getString("user_role");
				managers.add(new User(id, userName, password, firstName, lastName, email, userRole));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return managers;
	}

	@Override
	public User getUserById(int id) {
		String sql = "SELECT ers_users_id, ers_users.ers_username, ers_users.ers_password, ers_users.ers_first_name, ers_users.ers_last_name, ers_users.user_email, ers_user_roles.user_role FROM ers_schema.ers_users INNER JOIN ers_schema.ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id where ers_users.ers_users_id = ?";
		ResultSet rs = null;
		User user = null;
		
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				String userName = rs.getString("ers_username");
				String password = rs.getString("ers_password");
				String firstName = rs.getString("ers_first_name");
				String lastName = rs.getString("ers_last_name");
				String email = rs.getString("user_email");
				String userRole = rs.getString("user_role");
				user = new User(id, userName, password, firstName, lastName, email, userRole);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	

}
