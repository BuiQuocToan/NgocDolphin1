package com.vti.backend.businesslayer;

import java.sql.SQLException;
import java.util.List;

import com.vti.entity.Admin;
import com.vti.entity.Employee;
import com.vti.entity.User;

public interface IUserService {
	public List<User> getListUser() throws ClassNotFoundException, SQLException;
	
	public List<Admin> getAdmin() throws ClassNotFoundException, SQLException;
	
	public User getUserById(int userId) throws ClassNotFoundException, SQLException;
	
	public boolean deleteUserById(int userId) throws ClassNotFoundException, SQLException;

	public  String login(String email, String password);
	
	public void createAdmin(Admin admin) throws ClassNotFoundException, SQLException ;
		
	public void createEmployee(Employee employee) throws ClassNotFoundException, SQLException;
}
