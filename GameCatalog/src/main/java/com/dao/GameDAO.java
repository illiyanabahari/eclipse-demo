package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.*;

import com.model.Game;

public class GameDAO {
	  // Get all games
    public List<Game> getAllGames() {
        List<Game> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM GAMES");
            while (rs.next()) {
                Game g = new Game();
                g.setGameId(rs.getInt("GAME_ID"));
                g.setName(rs.getString("NAME"));
                g.setPublisher(rs.getString("PUBLISHER"));
                g.setDate(rs.getDate("DATE"));
                g.setCategory(rs.getString("CATEGORY"));
                g.setDescription(rs.getString("DESCRIPTION"));
                g.setPhotos(rs.getBytes("PHOTOS"));
                g.setUserRating(rs.getInt("USER_RATING"));
                g.setPlatform(rs.getString("PLATFORM_AVAILABLE"));
                list.add(g);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // Get game by ID
    public Game getGameById(int id) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM GAMES WHERE GAME_ID=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Game g = new Game();
                g.setGameId(id);
                g.setName(rs.getString("NAME"));
                g.setPublisher(rs.getString("PUBLISHER"));
                g.setDate(rs.getDate("DATE"));
                g.setCategory(rs.getString("CATEGORY"));
                g.setDescription(rs.getString("DESCRIPTION"));
                g.setPhotos(rs.getBytes("PHOTOS"));
                g.setUserRating(rs.getInt("USER_RATING"));
                g.setPlatform(rs.getString("PLATFORM_AVAILABLE"));
                return g;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    // Add game
    public void addGame(Game g) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO GAMES(NAME,PUBLISHER,DATE,CATEGORY,DESCRIPTION,PHOTOS,USER_RATING,PLATFORM_AVAILABLE) VALUES(?,?,?,?,?,?,?,?)"
            );
            ps.setString(1, g.getName());
            ps.setString(2, g.getPublisher());
            ps.setDate(3, (Date) g.getDate());
            ps.setString(4, g.getCategory());
            ps.setString(5, g.getDescription());
            ps.setBytes(6, g.getPhotos());
            ps.setInt(7, g.getUserRating());
            ps.setString(8, g.getPlatform());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Update game
    public void updateGame(Game g) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "UPDATE GAMES SET NAME=?,PUBLISHER=?,DATE=?,CATEGORY=?,DESCRIPTION=?,PHOTOS=?,USER_RATING=?,PLATFORM_AVAILABLE=? WHERE GAME_ID=?"
            );
            ps.setString(1, g.getName());
            ps.setString(2, g.getPublisher());
            ps.setDate(3, (Date) g.getDate());
            ps.setString(4, g.getCategory());
            ps.setString(5, g.getDescription());
            ps.setBytes(6, g.getPhotos());
            ps.setInt(7, g.getUserRating());
            ps.setString(8, g.getPlatform());
            ps.setInt(9, g.getGameId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Delete game
    public void deleteGame(int id) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM GAMES WHERE GAME_ID=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
