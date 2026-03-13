package com.demo.dao;

import com.demo.models.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
	   private static final String SQL_SELECT_BASE =
	           "SELECT customerNumber, customerName, contactLastName, contactFirstName, " +
	           "phone, addressLine1, addressLine2, city, state, postalCode, country, " +
	           "salesRepEmployeeNumber, creditLimit " +
	           "FROM customers ";
	   // Base count (no WHERE)
	   private static final String SQL_COUNT_BASE =
	           "SELECT COUNT(*) FROM customers ";
	   // Global search WHERE clause
	   // - Works for most cases
	   // - Includes customerNumber exact match when q is numeric (via customerNumber = ?)
	   private static final String SQL_WHERE_GLOBAL =
	           "WHERE ( " +
	           "  customerName LIKE ? OR " +
	           "  contactFirstName LIKE ? OR " +
	           "  contactLastName LIKE ? OR " +
	           "  phone LIKE ? OR " +
	           "  city LIKE ? OR " +
	           "  state LIKE ? OR " +
	           "  postalCode LIKE ? OR " +
	           "  country LIKE ? OR " +
	           "  CAST(customerNumber AS CHAR) LIKE ? " +   // allows partial match on number
	           ") ";
	   private static final String SQL_ORDER_PAGING =
	           "ORDER BY customerNumber DESC LIMIT ? OFFSET ?";
	   private static boolean hasText(String s) {
	       return s != null && !s.trim().isEmpty();
	   }
	   public int countAll(String q) {
	       boolean searching = hasText(q);
	       String sql = searching ? (SQL_COUNT_BASE + SQL_WHERE_GLOBAL) : SQL_COUNT_BASE;
	       try (Connection con = DbUtil.getConnection();
	            PreparedStatement ps = con.prepareStatement(sql)) {
	           if (searching) {
	               setGlobalSearchParams(ps, q);
	           }
	           try (ResultSet rs = ps.executeQuery()) {
	               return rs.next() ? rs.getInt(1) : 0;
	           }
	       } catch (Exception e) {
	           throw new RuntimeException("Error counting customers", e);
	       }
	   }
	   public List<Customer> findPage(String q, int page, int pageSize) {
	       int offset = (page - 1) * pageSize;
	       boolean searching = hasText(q);
	       String sql = searching
	               ? (SQL_SELECT_BASE + SQL_WHERE_GLOBAL + SQL_ORDER_PAGING)
	               : (SQL_SELECT_BASE + SQL_ORDER_PAGING);
	       List<Customer> list = new ArrayList<>();
	       try (Connection con = DbUtil.getConnection();
	            PreparedStatement ps = con.prepareStatement(sql)) {
	           int idx = 1;
	           if (searching) {
	               idx = setGlobalSearchParams(ps, q); // returns next index
	           }
	           // paging params at the end
	           ps.setInt(idx++, pageSize);
	           ps.setInt(idx, offset);
	           try (ResultSet rs = ps.executeQuery()) {
	               while (rs.next()) {
	                   Customer c = new Customer();
	                   c.setCustomerNumber(rs.getInt("customerNumber"));
	                   c.setCustomerName(rs.getString("customerName"));
	                   c.setContactLastName(rs.getString("contactLastName"));
	                   c.setContactFirstName(rs.getString("contactFirstName"));
	                   c.setPhone(rs.getString("phone"));
	                   c.setAddressLine1(rs.getString("addressLine1"));
	                   c.setAddressLine2(rs.getString("addressLine2"));
	                   c.setCity(rs.getString("city"));
	                   c.setState(rs.getString("state"));
	                   c.setPostalCode(rs.getString("postalCode"));
	                   c.setCountry(rs.getString("country"));
	                   c.setSalesRepEmployeeNumber(rs.getInt("salesRepEmployeeNumber"));
	                   c.setCreditLimit(rs.getDouble("creditLimit"));
	                   list.add(c);
	               }
	           }
	       } catch (Exception e) {
	           throw new RuntimeException("Error fetching customers page", e);
	       }
	       return list;
	   }
	   /**
	    * Sets the 9 LIKE parameters used by SQL_WHERE_GLOBAL.
	    * Returns the next parameter index after the last one set.
	    */
	   private int setGlobalSearchParams(PreparedStatement ps, String q) throws Exception {
	       String term = q.trim();
	       String like = "%" + term + "%";
	       int idx = 1;
	       ps.setString(idx++, like); // customerName
	       ps.setString(idx++, like); // contactFirstName
	       ps.setString(idx++, like); // contactLastName
	       ps.setString(idx++, like); // phone
	       ps.setString(idx++, like); // city
	       ps.setString(idx++, like); // state
	       ps.setString(idx++, like); // postalCode
	       ps.setString(idx++, like); // country
	       ps.setString(idx++, like); // CAST(customerNumber AS CHAR)
	       return idx;
	   }
	}
