package com.demo.servlet;

import com.demo.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        StringBuilder jsonBuffer = new StringBuilder();
        String line;

        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
        }

        String requestBody = jsonBuffer.toString();

        String username = extractJsonValue(requestBody, "username");
        String password = extractJsonValue(requestBody, "password");

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            response.getWriter().write("""
                {
                  "success": false,
                  "message": "Username and password are required"
                }
            """);
            return;
        }

        String sql = "SELECT full_name FROM users WHERE username = ? AND password = ?";

        try (
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String fullName = resultSet.getString("full_name");

                    String jsonResponse = """
                        {
                          "success": true,
                          "fullName": "%s"
                        }
                    """.formatted(escapeJson(fullName));

                    response.getWriter().write(jsonResponse);
                } else {
                    response.getWriter().write("""
                        {
                          "success": false,
                          "message": "Invalid username or password"
                        }
                    """);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();

            String errorResponse = """
                {
                  "success": false,
                  "message": "Server error occurred"
                }
            """;

            response.getWriter().write(errorResponse);
        }
    }

    private String extractJsonValue(String json, String key) {
        String searchKey = "\"" + key + "\"";
        int keyIndex = json.indexOf(searchKey);

        if (keyIndex == -1) {
            return null;
        }

        int colonIndex = json.indexOf(":", keyIndex);
        int firstQuoteIndex = json.indexOf("\"", colonIndex + 1);
        int secondQuoteIndex = json.indexOf("\"", firstQuoteIndex + 1);

        if (colonIndex == -1 || firstQuoteIndex == -1 || secondQuoteIndex == -1) {
            return null;
        }

        return json.substring(firstQuoteIndex + 1, secondQuoteIndex);
    }

    private String escapeJson(String value) {
        return value.replace("\"", "\\\"");
    }
}

