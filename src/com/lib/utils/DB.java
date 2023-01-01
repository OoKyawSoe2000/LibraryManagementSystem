package com.lib.utils;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DB {
	public static Connection getConnection(){
		Connection con=null;
		try{
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","admin");
		}catch(Exception e){
			 if(e instanceof LibCustomException) {
				 JOptionPane.showMessageDialog(null, e.getMessage());
			 }else {
				 JOptionPane.showMessageDialog(null,"Internal Error : " +  e.getMessage());
			 }
			}
		return con;
	}

}
