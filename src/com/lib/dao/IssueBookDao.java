package com.lib.dao;

import com.lib.service.LibraianService;

public class IssueBookDao {

	private LibraianService service;

	public IssueBookDao() {
		service = new LibraianService();
	}

	public boolean checkBook(String bookcallno) {
		return service.checkBook(bookcallno);
	}

	public int save(String bookcallno, int studentid, String studentname, String studentcontact) {
		return service.saveBookIssue(bookcallno, studentid, studentname, studentcontact);
	}

	public int updatebook(String bookcallno) {
		return service.updatebook(bookcallno);
	}
}
