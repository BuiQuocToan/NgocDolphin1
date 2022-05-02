package com.vti.entity;

import com.vti.backend.common.StringConfig;

public class Admin extends User {
	private int expInyear;

	public int getExpInyear() {
		return expInyear;
	}

	public Admin(User user, int expInyear) {
		super(user, StringConfig.admin);
		this.expInyear = expInyear;
	}

	public Admin(int id, String fullName, String email) {
		super(id, fullName, email);
	}
}
