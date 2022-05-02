package com.vti.backend.datalayer;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.vti.backend.common.StringConfig;
import com.vti.entity.Admin;
import com.vti.entity.Employee;
import com.vti.entity.Role;
import com.vti.entity.User;
import com.vti.utils.properties.JdbcUtils;

public class UserRepository implements IUserRepository {
	private JdbcUtils jdbcUtils;

	public UserRepository() throws IOException {
		jdbcUtils = new JdbcUtils();
	}

	// In ra Admin
	public List<Admin> getAdmin() throws ClassNotFoundException, SQLException {
		List<Admin> admins = new ArrayList<Admin>();

		try {
			Statement statement = jdbcUtils.Connect().createStatement();
			String sql = "SELECT ID, FullName, Email, `Password`, ExpInYear, `Role` FROM testjava.user "
					+ "WHERE `Role` = 'Admin'";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int ID = resultSet.getInt("ID");
				String fullName = resultSet.getString("FullName");
				String Email = resultSet.getString("Email");
				String Password = resultSet.getString("Password");
				int ExpInYear = resultSet.getInt("ExpInYear");
				Admin admin = new Admin(new User(fullName, Email, Password, StringConfig.admin), ExpInYear);
				admins.add(admin);
			}
			return admins;
		} finally {
			jdbcUtils.disconnect();
		}
	}

	// In ra User
	public List<User> getListUser() throws ClassNotFoundException, SQLException {
		List<User> users = new ArrayList<User>();
		try {
			Statement statement = jdbcUtils.Connect().createStatement();
			String sql = "SELECT ID, FullName, Email FROM testjava.user";

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int ID = resultSet.getInt("ID");
				String fullName = resultSet.getString("FullName");
				String Email = resultSet.getString("Email");

				User user = new User(ID, fullName, Email);
				users.add(user);
			}
			return users;
		} finally {
			jdbcUtils.disconnect();
		}
	}

	// Tìm User theo Id
	public User getUserById(int userId) throws ClassNotFoundException, SQLException {
		try {
			String sql = "SELECT * FROM testjava.user WHERE ID = ?";
			PreparedStatement statement = jdbcUtils.Connect().prepareStatement(sql);
			statement.setInt(1, userId);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int ID = resultSet.getInt("ID");
				String FullName = resultSet.getString("fullName");
				String Email = resultSet.getString("Email");
				String Password = resultSet.getString("Password");
				int expInyear = resultSet.getInt("ExpInYear");
				String ProSkill = resultSet.getString("ProSkill");
				String role = resultSet.getString("Role");
				if (role.equals("Admin")) {
					User admin = new Admin(new User(ID, FullName, Email, Password), expInyear);
					return admin;
				} else {
					User employee = new Employee(ID, FullName, Email, Password, ProSkill);
					return employee;
				}
			} else {
				return null;
			}
		} finally {
			jdbcUtils.disconnect();
		}
	}

	// Xóa User theo id
	public boolean deleteUserById(int userId) throws ClassNotFoundException, SQLException {
		try {
			String sql = "DELETE FROM testjava.user WHERE ID = ?";
			PreparedStatement statement = jdbcUtils.Connect().prepareStatement(sql);
			statement.setInt(1, userId);

			int affect_records = statement.executeUpdate();

			if (affect_records > 0) {
				return true;
			} else {
				return false;
			}
		} finally {
			jdbcUtils.disconnect();
		}

	}

	// Dang nhap
	public String login(String email, String password) {
		try {
			String sql = "SELECT * FROM testjava.user WHERE Email = ? AND `Password` = ?";
			PreparedStatement statement = jdbcUtils.Connect().prepareStatement(sql);

			statement.setString(1, email);
			statement.setString(2, password);

			ResultSet resultSet = statement.executeQuery();
			jdbcUtils.disconnect();
			return resultSet.next() ? resultSet.getString("FullName") : StringUtils.EMPTY;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Email hoặc mật khẩu sai!");
			return StringConfig.emptyString;
		}
	}

	// Tao Admin
	public void createAdmin(Admin admin) throws ClassNotFoundException, SQLException {
		try {
			String sql = "INSERT INTO `User` (Email	,FullName,`Password`,ExpInYear, `Role`) VALUES (?, ?, '123456', ? ,'Admin')";
			PreparedStatement statement = jdbcUtils.Connect().prepareStatement(sql);

			statement.setString(1, admin.getEmail());
			statement.setString(2, admin.getFullName());
			statement.setInt(3, admin.getExpInyear());

			statement.executeUpdate();

			System.out.println("Đã thêm Admin");
		} finally {
			jdbcUtils.disconnect();
		}
	}

	public void createEmployee(Employee employee) throws ClassNotFoundException, SQLException {
		try {
			String sql = "INSERT INTO `User` (Email	,FullName,`Password`,ProSkill, `Role`) VALUES (?, ?, '123456', ?,'Employee')";
			PreparedStatement statement = jdbcUtils.Connect().prepareStatement(sql);

			statement.setString(1, employee.getEmail());
			statement.setString(2, employee.getFullName());
			statement.setString(3, employee.getProSkill());

			statement.executeUpdate();

			System.out.println("Đã thêm Employee");
		} finally {
			jdbcUtils.disconnect();
		}
	}

}