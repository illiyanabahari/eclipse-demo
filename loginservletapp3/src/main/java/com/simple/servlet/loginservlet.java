package com.simple.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loginservlet
 */
@WebServlet("/login")
public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get form parameters
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// Set response type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// Simple login logic
		if ("admin".equals(username) && "1234".equals(password)) {
			out.println("<h1>Login Successful! Welcome, " + username + "</h1>");
		} else {
			out.println("<h1>Login Failed! Invalid Username or Password.</h1>");
		}
	}
}
