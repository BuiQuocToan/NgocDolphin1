package com.vti.backend.businesslayer;

import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.vti.backend.common.StringConfig;
import com.vti.backend.datalayer.IUserRepository;
import com.vti.backend.datalayer.UserRepository;
import com.vti.entity.Admin;
import com.vti.entity.Employee;
import com.vti.entity.User;

public class UserServicce implements IUserService {
	private IUserRepository iUserRepository;

	public UserServicce() throws IOException {
		iUserRepository = new UserRepository();
	}

	public List<Admin> getAdmin() throws ClassNotFoundException, SQLException {
		return iUserRepository.getAdmin();
	}

	public String login(String email, String password) {
		String name = iUserRepository.login(email, password);
		// check xem có kết quả trả về không
		return StringUtils.isEmpty(name) ? StringConfig.emptyString : name;
	}

	public void createAdmin(Admin admin) throws ClassNotFoundException, SQLException {
		iUserRepository.createAdmin(admin);

	}

	public void createEmployee(Employee employee) throws ClassNotFoundException, SQLException {
		iUserRepository.createEmployee(employee);

	}

	public List<User> getListUser() throws ClassNotFoundException, SQLException {
		return iUserRepository.getListUser();

	}

	public User getUserById(int userId) throws ClassNotFoundException, SQLException {
		return iUserRepository.getUserById(userId);
	}

	public boolean deleteUserById(int userId) throws ClassNotFoundException, SQLException {
		return iUserRepository.deleteUserById(userId);
	}
}
