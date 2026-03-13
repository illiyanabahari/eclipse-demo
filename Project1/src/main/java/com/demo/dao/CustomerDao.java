package com.demo.dao;

import com.demo.models.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class CustomerDao {
   private static final String SQL_SELECT_ALL =
           "SELECT customer_id, first_name, last_name, email, phone, created_at " +
           "FROM customers ORDER BY customer_id DESC";
   public List<Customer> findAll() {
       List<Customer> list = new ArrayList<>();
       try (Connection con = DbUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = ps.executeQuery()) {
           while (rs.next()) {
               Customer c = new Customer();
               c.setCustomerId(rs.getInt("customer_id"));
               c.setFirstName(rs.getString("first_name"));
               c.setLastName(rs.getString("last_name"));
               c.setEmail(rs.getString("email"));
               c.setPhone(rs.getString("phone"));
               c.setCreatedAt(rs.getTimestamp("created_at"));
               list.add(c);
           }
       } catch (Exception e) {
           throw new RuntimeException("Error fetching customers", e);
       }
       return list;
   }
}
