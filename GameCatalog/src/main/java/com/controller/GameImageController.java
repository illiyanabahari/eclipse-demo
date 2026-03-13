package com.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DBConnection;

@WebServlet("/gameImage")
public class GameImageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        
        String idStr = request.getParameter("id");
        if (idStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing game id");
            return;
        }

        int gameId;
        try {
            gameId = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid game id");
            return;
        }

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT PHOTOS FROM GAMES WHERE GAME_ID = ?")) {

            ps.setInt(1, gameId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Blob photoBlob = rs.getBlob("PHOTOS");

                if (photoBlob != null) {
                    response.setContentType("image/jpeg"); // adjust to your actual image type
                    
                    // Stream image bytes to client
                    try (InputStream is = photoBlob.getBinaryStream();
                         OutputStream os = response.getOutputStream()) {

                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = is.read(buffer)) != -1) {
                            os.write(buffer, 0, bytesRead);
                        }
                        os.flush();  // flush output stream
                    }
                } else {
                    // No photo data found, send 404 or default image maybe
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found");
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Game not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error");
        }
    }
}
