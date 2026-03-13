package com.demo.dao;

import com.demo.models.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAO 
{

    public static List<OrderItem> findByOrderNumber(int orderNumber) 
    {

        List<OrderItem> list = new ArrayList<>();
        String sql = """
            SELECT productCode, quantityOrdered, priceEach
            FROM orderdetails
            WHERE orderNumber = ?
        """;
        try (Connection con = DbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {

            ps.setInt(1, orderNumber);

            try (ResultSet rs = ps.executeQuery()) 
            {
                while (rs.next()) 
                {
                    list.add(new OrderItem(
                            rs.getString("productCode"),
                            rs.getInt("quantityOrdered"),
                            rs.getDouble("priceEach")
                    ));
                }
            }

        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);
        }
        return list;
    }
}
