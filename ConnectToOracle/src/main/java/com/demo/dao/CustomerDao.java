package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    public static class Customer {
        private long customerId;
        private String firstName;
        private String lastName;
        private String email;

        public Customer(long customerId, String firstName, String lastName, String email) {
            this.customerId = customerId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        public long getCustomerId() { return customerId; }
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getEmail() { return email; }
    }

    public List<Customer> findAll() {
        String sql = "SELECT customer_id, first_name, last_name, email FROM customers ORDER BY customer_id";
        List<Customer> list = new ArrayList<>();

        try (Connection con = DbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Customer(
                        rs.getLong("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email")
                ));
            }

        } catch (Exception ex) {
            throw new RuntimeException("DB error: " + ex.getMessage(), ex);
        }

        return list;
    }
}
