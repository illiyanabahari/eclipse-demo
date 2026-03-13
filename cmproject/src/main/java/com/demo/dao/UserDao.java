package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.demo.models.AppUser;

public class UserDao 
{

    private static final String SQL_FIND_BY_USERNAME =
            "SELECT userId, username, passwordHash, firstName, lastName, permission " +
            "FROM app_users WHERE username = ?";

    private static final String SQL_INSERT_USER =
            "INSERT INTO app_users (username, passwordHash, firstName, lastName, permission) " +
            "VALUES (?, ?, ?, ?, ?)";

    public AppUser findByUsername(String username) {
        try (Connection con = DbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_USERNAME)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                AppUser u = new AppUser();
                u.setUserId(rs.getInt("userId"));
                u.setUsername(rs.getString("username"));
                u.setPasswordHash(rs.getString("passwordHash"));
                u.setFirstName(rs.getString("firstName"));
                u.setLastName(rs.getString("lastName"));
                u.setPermission(rs.getString("permission"));
                return u;
            }

        } catch (Exception e) {
            throw new RuntimeException("Error loading user", e);
        }
    }

    public int createUser(AppUser user) {
        try (Connection con = DbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT_USER)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPermission());

            return ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error creating user", e);
        }
    }
}

