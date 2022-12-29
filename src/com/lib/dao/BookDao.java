package com.lib.dao;

import com.lib.service.LibraianService;

public class BookDao {
	private LibraianService service;
	
	public BookDao() {
		service = new LibraianService();
	}
	
	
	public int save(String callno, String name, String author, String publisher, int quantity) {
		return service.save(callno, name, author, publisher, quantity);
	}
}
