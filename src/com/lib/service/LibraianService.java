package com.lib.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.lib.utils.DB;
import com.lib.utils.LibCustomException;

public class LibraianService {

	private final String SAVE_BOOK = """
			INSERT INTO books(callno,name,author,publisher,quantity)
			VALUES(?,?,?,?,?)
			""";

	private static final String SELECT_BOOK_BY_CALLNO = "SELECT * FROM books WHERE callno=?";

	private static final String SAVE_BOOK_ISSUE = """
			INSERT INTO issuebooks(bookcallno,studentid,studentname,studentcontact) VALUES(?,?,?,?)
			""";

	private static final String DELETE_BY_CALLNO_STUID = "DELETE FROM issuebooks WHERE bookcallno=? AND studentid=?";

	public int save(String callno, String name, String author, String publisher, int quantity) {

		try (Connection con = DB.getConnection(); PreparedStatement ps = con.prepareStatement(SAVE_BOOK)) {

			ps.setString(1, callno);
			ps.setString(2, name);
			ps.setString(3, author);
			ps.setString(4, publisher);
			ps.setInt(5, quantity);
			return ps.executeUpdate();
		} catch (Exception e) {
			if (e instanceof LibCustomException) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} else {
				JOptionPane.showMessageDialog(null, "Internal Error : " + e.getMessage());
			}
		}
		return 0;
	}

	public boolean checkBook(String bookcallno) {
		try (Connection con = DB.getConnection(); PreparedStatement ps = con.prepareStatement(SELECT_BOOK_BY_CALLNO)) {

			ps.setString(1, bookcallno);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			if (e instanceof LibCustomException) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} else {
				JOptionPane.showMessageDialog(null, "Internal Error : " + e.getMessage());
			}
		}
		return false;
	}

	public int saveBookIssue(String bookcallno, int studentid, String studentname, String studentcontact) {
		int status = 0;
		try {
			Connection con = DB.getConnection();

			status = updatebook(bookcallno);

			if (status > 0) {
				PreparedStatement ps = con.prepareStatement(SAVE_BOOK_ISSUE);
				ps.setString(1, bookcallno);
				ps.setInt(2, studentid);
				ps.setString(3, studentname);
				ps.setString(4, studentcontact);
				return ps.executeUpdate();
			}

			con.close();
		} catch (Exception e) {
			if (e instanceof LibCustomException) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} else {
				JOptionPane.showMessageDialog(null, "Internal Error : " + e.getMessage());
			}
		}
		return status;
	}

	public int updatebook(String bookcallno) {
		int status = 0;
		int quantity = 0, issued = 0;
		try {
			Connection con = DB.getConnection();

			PreparedStatement ps = con.prepareStatement("SELECT quantity,issued FROM books WHERE callno=?");
			ps.setString(1, bookcallno);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				quantity = rs.getInt("quantity");
				issued = rs.getInt("issued");
			}

			if (quantity > 0) {
				PreparedStatement ps2 = con.prepareStatement("UPDATE books SET quantity=?,issued=? WHERE callno=?");
				ps2.setInt(1, quantity - 1);
				ps2.setInt(2, issued + 1);
				ps2.setString(3, bookcallno);

				status = ps2.executeUpdate();
			}
			con.close();
		} catch (Exception e) {
			if (e instanceof LibCustomException) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} else {
				JOptionPane.showMessageDialog(null, "Internal Error : " + e.getMessage());
			}

		}
		return status;
	}

	public int delete(String bookcallno, int studentid) {
		int status = 0;
		try {
			Connection con = DB.getConnection();
			status = updatebook(bookcallno);

			if (status > 0) {
				PreparedStatement ps = con.prepareStatement(DELETE_BY_CALLNO_STUID);
				ps.setString(1, bookcallno);
				ps.setInt(2, studentid);
				status = ps.executeUpdate();
			}

			con.close();
		} catch (Exception e) {
			if (e instanceof LibCustomException) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} else {
				JOptionPane.showMessageDialog(null, "Internal Error : " + e.getMessage());
			}
		}
		return status;
	}

	


}
