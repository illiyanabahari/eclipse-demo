package com.demo.web;

import com.demo.dao.OrderDetailsDAO;
import com.demo.models.OrderItem;
import com.google.gson.Gson;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/orderdetails")
public class OrderDetailsApiServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
    {

        String orderParam = req.getParameter("orderNumber");
        if (orderParam == null || orderParam.isBlank()) 
        {
            resp.sendError(400, "orderNumber required. Example: ?orderNumber=10100");
            return;
        }
        int orderNumber;
        try 
        {
            orderNumber = Integer.parseInt(orderParam);
        } 
        catch (NumberFormatException ex) 
        {
            resp.sendError(400, "orderNumber must be an integer.");
            return;
        }
        List<OrderItem> items = OrderDetailsDAO.findByOrderNumber(orderNumber);
        double totalValue = 0;
        for (OrderItem item : items) 
        {
            totalValue += item.getQuantityOrdered() * item.getPriceEach();
        }
        Map<String, Object> response = new HashMap<>();
        response.put("orderNumber", orderNumber);
        response.put("items", items);
        response.put("totalValue", totalValue);
        String json = gson.toJson(response);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}
