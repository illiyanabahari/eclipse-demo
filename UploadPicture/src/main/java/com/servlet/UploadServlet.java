package com.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    // Folder where uploaded files are saved
    private static final String UPLOAD_DIR =
        System.getProperty("user.home") + File.separator + "UploadPictureUploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Create folder if it does not exist
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) dir.mkdirs();

        // Get uploaded file
        Part part = request.getPart("file");
        String fileName = new File(part.getSubmittedFileName()).getName();

        // Save file on disk
        part.write(UPLOAD_DIR + File.separator + fileName);

        // Encode filename for URL
        String encoded = URLEncoder.encode(fileName, "UTF-8");

        // Redirect back to JSP with image name
        response.sendRedirect("upload.jsp?img=" + encoded);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // Handle <img src="upload?show=filename">
        String name = request.getParameter("show");
        if (name == null) return;

        File file = new File(UPLOAD_DIR, name);
        if (!file.exists()) return;

        // Set correct MIME type
        String mime = Files.probeContentType(file.toPath());
        if (mime == null) mime = "application/octet-stream";

        response.setContentType(mime);
        response.setContentLengthLong(file.length());

        // Stream file to browser
        Files.copy(file.toPath(), response.getOutputStream());
    }
}
