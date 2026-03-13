package com.test.dao;

import java.io.FileInputStream;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@WebListener
public class FirebaseContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            ServletContext ctx = sce.getServletContext();

            // Path to your service account key inside WEB-INF
            String path = ctx.getRealPath("/WEB-INF/firebase/test-75b57-firebase-adminsdk-fbsvc-380451e9bc.json");

            // Avoid double init (important for redeploys)
            if (!FirebaseApp.getApps().isEmpty()) {
                System.out.println("Firebase already initialized.");
                return;
            }

            FileInputStream serviceAccount = new FileInputStream(path);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);

            System.out.println("✅ Firebase Initialized Successfully");

        } catch (Exception e) {
            System.out.println("❌ Firebase Init Failed");
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // optional: nothing needed
    }
}
