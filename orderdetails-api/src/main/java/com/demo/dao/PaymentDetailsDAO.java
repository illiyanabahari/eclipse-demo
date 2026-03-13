package com.demo.dao;

import com.demo.models.PaymentItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentDetailsDAO 
{

    public static List<PaymentItem> findByOrderNumber(int customerNumber) 
    {

        List<PaymentItem> list = new ArrayList<>();
        String sql = """
            SELECT customerNumber, checkNumber, paymentDate,amount
            FROM payments
            WHERE customerNumber = ?
        """;
        try (Connection con = DbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {

            ps.setInt(1, customerNumber);

            try (ResultSet rs = ps.executeQuery()) 
            {
                while (rs.next()) 
                {
                    list.add(new PaymentItem(
                            rs.getString("checkNumber"),
                            rs.getDate("paymentDate"),
                            rs.getDouble("amount")
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
