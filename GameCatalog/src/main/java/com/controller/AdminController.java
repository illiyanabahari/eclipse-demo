package com.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dao.GameDAO;
import com.model.Game;

/**
 * Servlet implementation class AdminController
 */
@MultipartConfig(maxFileSize = 16177215) // ~16MB
@WebServlet("/admin")
public class AdminController extends HttpServlet {
    // ...
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            Game g = new Game();
            g.setName(req.getParameter("name"));
            g.setPublisher(req.getParameter("publisher"));
            g.setCategory(req.getParameter("category"));
            g.setPlatform(req.getParameter("platform"));
            g.setDescription(req.getParameter("description"));

            // Handle DATE column
            String dateStr = req.getParameter("date"); // comes in yyyy-MM-dd format
            if (dateStr != null && !dateStr.isEmpty()) {
                Date releaseDate = Date.valueOf(dateStr); // java.sql.Date, directly parse yyyy-MM-dd
                g.setDate(releaseDate);
            }

            // Handle USER_RATING column
            String ratingStr = req.getParameter("userRating");
            if (ratingStr != null && !ratingStr.isEmpty()) {
                g.setUserRating(Integer.parseInt(ratingStr));
            }

            Part photoPart = req.getPart("photo"); // name="photo"
            if (photoPart != null && photoPart.getSize() > 0) {
                InputStream is = photoPart.getInputStream();
                byte[] photoBytes = is.readAllBytes(); // Java 9+
                g.setPhotos(photoBytes);;
            }

            GameDAO dao = new GameDAO();
            String idStr = req.getParameter("id");
            if (idStr == null || idStr.isEmpty()) {
                dao.addGame(g); // Add new game
            } else {
                g.setGameId(Integer.parseInt(idStr));
                dao.updateGame(g); // Update existing game
            }

            res.sendRedirect("adminDashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("adminDashboard.jsp?error=1");
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        GameDAO dao = new GameDAO();

        if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            Game g = dao.getGameById(id);
            req.setAttribute("game", g);
            req.getRequestDispatcher("editGame.jsp").forward(req, res); // just forward to JSP
            return;
        }

        if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            dao.deleteGame(id);
            res.sendRedirect("adminDashboard.jsp");
            return;
        }

        // Default redirect
        res.sendRedirect("adminDashboard.jsp");
    }
}