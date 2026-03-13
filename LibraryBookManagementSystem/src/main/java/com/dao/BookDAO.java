package com.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.*;

public class BookDAO {
	public boolean addBook(Book book) {
	    boolean status = false;
	    try {
	        Connection con = DBConnection.getConnection();
	        if (con == null) {
	            System.out.println("Connection is null! Cannot insert book.");
	            return false;
	        }

	        String sql = "INSERT INTO books(title, author, publisher, quantity) VALUES (?, ?, ?, ?)";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, book.getTitle());
	        ps.setString(2, book.getAuthor());
	        ps.setString(3, book.getPublisher());
	        ps.setInt(4, book.getQuantity());

	        int rows = ps.executeUpdate();
	        if (rows > 0) {
	            status = true;
	        }

	    } catch (Exception e) {
	        e.printStackTrace(); // <-- See the real exception
	    }
	    return status;
	}
	public List<Book> getAllBooks() {
	    List<Book> books = new ArrayList<>();
	    try {
	        Connection con = DBConnection.getConnection();
	        String sql = "SELECT * FROM books";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Book book = new Book();
	            book.setId(rs.getInt("id"));
	            book.setTitle(rs.getString("title"));
	            book.setAuthor(rs.getString("author"));
	            book.setPublisher(rs.getString("publisher"));
	            book.setQuantity(rs.getInt("quantity"));
	            books.add(book);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return books;
	}
	public boolean updateBook(Book book) {
	    boolean status = false;
	    try {
	        Connection con = DBConnection.getConnection();
	        String sql = "UPDATE books SET title=?, author=?, publisher=?, quantity=? WHERE id=?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, book.getTitle());
	        ps.setString(2, book.getAuthor());
	        ps.setString(3, book.getPublisher());
	        ps.setInt(4, book.getQuantity());
	        ps.setInt(5, book.getId());
	        int rows = ps.executeUpdate();
	        status = rows > 0;
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return status;
	}
	public Book getBookById(int id) {
		 Book book = null;
		    try {
		        Connection con = DBConnection.getConnection();
		        String sql = "SELECT * FROM books WHERE id = ?";
		        PreparedStatement ps = con.prepareStatement(sql);
		        ps.setInt(1, id);
		        ResultSet rs = ps.executeQuery();
		        if (rs.next()) {
		            book = new Book();
		            book.setId(rs.getInt("id"));
		            book.setTitle(rs.getString("title"));
		            book.setAuthor(rs.getString("author"));
		            book.setPublisher(rs.getString("publisher"));
		            book.setQuantity(rs.getInt("quantity"));
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return book;
	}
	public boolean deleteBook(int id) {
	    boolean status = false;
	    try {
	        Connection con = DBConnection.getConnection();
	        String sql = "DELETE FROM books WHERE id=?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, id);
	        int rows = ps.executeUpdate();
	        status = rows > 0;
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return status;
	}

	
}
