package com.lib.dao;

import com.lib.service.AdminService;

public class LibrarianDao {

	private AdminService service;

	public LibrarianDao() {
		service = new AdminService();
	}

	public int save(String name, String password, String email, String address, String city, String contact) {
		return service.save(name, password, email, address, city, contact);
	}

	public int delete(int id) {
		return service.delete(id);
	}

	public boolean validate(String name, String password) {
		return service.check(name, password);
	}

}
