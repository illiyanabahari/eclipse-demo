package com.model;

import java.sql.*;

public class Game {
	  private int gameId;
	    private String name;
	    private String publisher;
	    private Date date;
	    private String category;
	    private String description;
	    private byte[] photos;  // For LONGBLOB
	    private int userRating;
	    private String platform;

	    // Getters & Setters
	    public int getGameId() { return gameId; }
	    public void setGameId(int gameId) { this.gameId = gameId; }

	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }

	    public String getPublisher() { return publisher; }
	    public void setPublisher(String publisher) { this.publisher = publisher; }

	    public Date getDate() { return date; }
	    public void setDate(Date date) { this.date = date; }

	    public String getCategory() { return category; }
	    public void setCategory(String category) { this.category = category; }

	    public String getDescription() { return description; }
	    public void setDescription(String description) { this.description = description; }

	    public byte[] getPhotos() { return photos; }
	    public void setPhotos(byte[] photos) { this.photos = photos; }

	    public int getUserRating() { return userRating; }
	    public void setUserRating(int userRating) { this.userRating = userRating; }

	    public String getPlatform() { return platform; }
	    public void setPlatform(String platform) { this.platform = platform; }
}
