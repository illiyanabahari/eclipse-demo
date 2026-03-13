package com.demo.web;

import com.demo.dao.PaymentDetailsDAO;
import com.demo.models.PaymentItem;
import com.google.gson.Gson;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/paymentdetails")
public class PaymentDetailsApiServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
    {

        String orderParam = req.getParameter("customerNumber");
        if (orderParam == null || orderParam.isBlank()) 
        {
            resp.sendError(400, "customerNumber required. Example: ?customerNumber=121");
            return;
        }
        int customerNumber;
        try 
        {
            customerNumber = Integer.parseInt(orderParam);
        } 
        catch (NumberFormatException ex) 
        {
            resp.sendError(400, "customerNumber must be an integer.");
            return;
        }
        List<PaymentItem> items = PaymentDetailsDAO.findByOrderNumber(customerNumber);
        double totalValue = 0;
        for (PaymentItem item : items) 
        {
            totalValue += item.getAmount();
        }
        Map<String, Object> response = new HashMap<>();
        response.put("customerNumber", customerNumber);
        response.put("items", items);
        response.put("totalValue", totalValue);
        String json = gson.toJson(response);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}
