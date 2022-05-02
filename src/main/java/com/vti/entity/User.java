package com.vti.entity;

import com.vti.backend.common.StringConfig;

public class User {
	private int id;
	private String fullName;
	private String email;
	private String password;
	private Role role;

	public int getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Role getRole() {
		return role;
	}

	public User(int id, String fullName, String email, String password, String role) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.role = StringConfig.admin.equalsIgnoreCase(role) ? Role.ADMIN : Role.EMPLOYEE;
	}

	public User(int id, String fullName, String email) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
	}

	public User(User user, String role) {
		this.id = user.id;
		this.fullName = user.fullName;
		this.email = user.email;
		this.password = user.password;
		this.role = StringConfig.admin.equalsIgnoreCase(role) ? Role.ADMIN : Role.EMPLOYEE; //toán tử 3 ngôi ternary
	}

	public User(String fullName2, String email2, String password2, String role) {
		this.fullName = fullName2;
		this.email = email2;
		this.password =  password2;
		this.role = StringConfig.admin.equalsIgnoreCase(role) ? Role.ADMIN : Role.EMPLOYEE;
	}

	public User(int iD2, String fullName2, String email2, String password2) {
		this.id = iD2;
		this.fullName = fullName2;
		this.email = email2;
		this.password =  password2;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", email=" + email + "]";
	}
}
