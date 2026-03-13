package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDAO {
	public boolean addUser(User user) {
		boolean status=false;
		
		try {
			Connection con=DBConnection.getConnection();
			if(con==null) {
				System.out.println("Connection is null!");
				return false;
			}
			String sql="INSERT INTO user(name,email,password) VALUES (?,?,?)";
			
			PreparedStatement ps=con.prepareStatement(sql);
			
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			
			
			int rows=ps.executeUpdate();
			if(rows >0) {
				status=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
