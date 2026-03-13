package com.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil 
{
    private static final String URL =
            "jdbc:mysql://localhost:3306/classicmodels?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    static 
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } 
        catch (Exception e) 
        {
            throw new RuntimeException("MySQL driver not found", e);
        }
    }
    public static Connection getConnection() throws Exception 
    {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
