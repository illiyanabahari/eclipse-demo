package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.GameDAO;

/**
 * Servlet implementation class GameController
 */
@WebServlet("/games")
public class GameController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setAttribute("games", new GameDAO().getAllGames());
        req.getRequestDispatcher("games.jsp").forward(req, res);
    }
}