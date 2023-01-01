package com.lib.dao;
import com.lib.service.LibraianService;

public class ReturnBookDao {
	private LibraianService service;
	
	public ReturnBookDao() {
		service = new LibraianService();
	}
	
	public  int delete(String bookcallno,int studentid){
		return service.delete(bookcallno, studentid);
	}
	
	
	public int updatebook(String bookcallno){
		return service.updatebook(bookcallno);
	}
}
