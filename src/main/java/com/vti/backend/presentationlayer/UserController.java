package com.vti.backend.presentationlayer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.vti.backend.businesslayer.IUserService;
import com.vti.backend.businesslayer.UserServicce;
import com.vti.entity.Admin;
import com.vti.entity.Employee;
import com.vti.entity.User;

public class UserController {
	private IUserService iUserServicce;

	public UserController() throws IOException {
		iUserServicce = new UserServicce();
	}

	public List<User> getListUser() throws ClassNotFoundException, SQLException {
		return iUserServicce.getListUser();
	}

	public List<Admin> getAdmin() throws ClassNotFoundException, SQLException {
		return iUserServicce.getAdmin();
	}

	public User getUserById(int userId) throws ClassNotFoundException, SQLException {
		return iUserServicce.getUserById(userId);
	}
	
	public boolean deleteUserById(int userId) throws ClassNotFoundException, SQLException {
		return iUserServicce.deleteUserById(userId);
	}
	public String login(String email, String password) {
		return iUserServicce.login(email, password);
	}

	public void createAdmin(Admin admin) throws ClassNotFoundException, SQLException {
		iUserServicce.createAdmin(admin);
	}

	public void createEmployee(Employee employee) throws ClassNotFoundException, SQLException {
		iUserServicce.createEmployee(employee);
	}
}