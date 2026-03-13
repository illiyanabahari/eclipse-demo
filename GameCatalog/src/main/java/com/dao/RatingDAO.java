package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RatingDAO {
	    public void rate(int gameId, int userId, int rating) {
	        try {
	            Connection con = DBConnection.getConnection();
	            PreparedStatement ps =
	                con.prepareStatement(
	                    "INSERT INTO RATINGS VALUES(?,?,?) ON DUPLICATE KEY UPDATE RATING=?");
	            ps.setInt(1, gameId);
	            ps.setInt(2, userId);
	            ps.setInt(3, rating);
	            ps.setInt(4, rating);
	            ps.executeUpdate();
	        } catch (Exception e) { e.printStackTrace(); }
	    }
	}


