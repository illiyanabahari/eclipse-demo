package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.User;

public class UserDAO {
	public User login(String email, String password) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                con.prepareStatement("SELECT * FROM USERS WHERE EMAIL=? AND PASSWORD=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("USER_ID"));
                u.setName(rs.getString("NAME"));
                u.setEmail(email);
                u.setRole("customer");
                return u;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

	public void register(String name, String email, String password) {
	    try {
	        Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(
	            "INSERT INTO USERS(NAME,EMAIL,PASSWORD) VALUES(?,?,?)");
	        ps.setString(1, name);
	        ps.setString(2, email);
	        ps.setString(3, password);
	        ps.executeUpdate();
	        System.out.println("Inserted into DB: " + email);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
