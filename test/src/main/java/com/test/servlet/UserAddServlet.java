package com.test.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@WebServlet("/users/add")
public class UserAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Show the form JSP
        req.getRequestDispatcher("/WEB-INF/view/addUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");

        // Basic validation
        if (email == null || email.trim().isEmpty() ||
            fullname == null || fullname.trim().isEmpty()) {

            req.setAttribute("msg", "❌ Please fill in email and full name.");
            req.getRequestDispatcher("/WEB-INF/views/addUser.jsp").forward(req, resp);
            return;
        }

        email = email.trim();
        fullname = fullname.trim();

        try {
            Firestore db = FirestoreClient.getFirestore();

            Map<String, Object> data = new HashMap<>();
            data.put("email", email);
            data.put("fullname", fullname);

            // Option A (recommended): use auto-generated document ID
            DocumentReference docRef = db.collection("users").document(); // auto ID
            ApiFuture<WriteResult> result = docRef.set(data);

            // Wait for completion (simple for training/demo)
            result.get();

            req.setAttribute("msg", "✅ Saved to Firestore! Doc ID: " + docRef.getId());
            req.getRequestDispatcher("/WEB-INF/view/addUser.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "❌ Error saving to Firestore: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/addUser.jsp").forward(req, resp);
        }
    }
}
