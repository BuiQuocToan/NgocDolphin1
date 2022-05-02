package com.vti.entity;

import com.vti.backend.common.StringConfig;

public class Employee extends User {
	private String proSkill;

	public String getProSkill() {
		return proSkill;
	}

	public Employee(int id, String fullName, String email, String password, String proSkill) {
		super(id, fullName, email, password, StringConfig.employee);
		this.proSkill = proSkill;
	}

	public Employee(int id, String fullName, String email) {
		super(id, fullName, email);

	}

	public Employee(User userEmployeeUser, String proSkill2) {
		super(userEmployeeUser, StringConfig.employee);
		this.proSkill = proSkill2;
	}

}
