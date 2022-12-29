package com.lib.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.lib.utils.DB;
import com.lib.utils.LibCustomException;

public class AdminService {
	private final String SAVE_LIBRARIAN = """
			INSERT INTO librarian(name,password,email,address,city,contact) 
			values(?,?,?,?,?,?)
			""";
	
	private final String DELETE_LIBRARIAN = """
			DELETE FROM librarian WHERE id=?
			""";

	 public int save(String name, String password, String email, String address, String city, String contact) {
		 try (Connection con = DB.getConnection();
					PreparedStatement ps = con.prepareStatement(SAVE_LIBRARIAN)) {

				ps.setString(1, name);
				ps.setString(2, password);
				ps.setString(3, email);
				ps.setString(4, address);
				ps.setString(5, city);
				ps.setString(6, contact);
				return ps.executeUpdate();

			} catch (Exception e) {
				if(e instanceof LibCustomException) {
					 JOptionPane.showMessageDialog(null, e.getMessage());
				 }else {
					 JOptionPane.showMessageDialog(null,"Internal Error : " +  e.getMessage());
				 }
			}
		 return 0;
	 }
	 
	 public int delete(int id) {
			try(Connection con = DB.getConnection();
					PreparedStatement ps = con.prepareStatement(DELETE_LIBRARIAN);) {
				ps.setInt(1, id);
				return ps.executeUpdate();
			} catch (Exception e) {
				if(e instanceof LibCustomException) {
					 JOptionPane.showMessageDialog(null, e.getMessage());
				 }else {
					 JOptionPane.showMessageDialog(null,"Internal Error : " +  e.getMessage());
				 }
			}
			return 0;
	 }
	 
	 public boolean check(String name, String password) {
		 boolean status = false;
			try {
				Connection con = DB.getConnection();
				PreparedStatement ps = con.prepareStatement("select * from librarian where name=? and password=?");
				ps.setString(1, name);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				status = rs.next();
				con.close();
			} catch (Exception e) {
				if(e instanceof LibCustomException) {
					 JOptionPane.showMessageDialog(null, e.getMessage());
				 }else {
					 JOptionPane.showMessageDialog(null,"Internal Error : " +  e.getMessage());
				 }
			}
			return status;
	 }
}
