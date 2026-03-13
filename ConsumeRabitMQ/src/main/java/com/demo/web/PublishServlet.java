package com.demo.web;

import com.demo.mq.RabbitManager;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/publish")
public class PublishServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final RabbitManager manager =
            new RabbitManager("127.0.0.1", 5672, "admin", "admin123", "/");

    private final String queueName = "demo.queue";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String message = req.getParameter("message");
            if (message == null || message.trim().isEmpty()) {
                resp.setStatus(400);
                resp.setContentType("text/plain; charset=UTF-8");
                resp.getWriter().write("message is required");
                return;
            }

            try (Connection conn = manager.newConnection("jsp-servlet-publisher");
                 Channel channel = conn.createChannel()) {

                channel.queueDeclare(queueName, true, false, false, null);
                channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
            }

            resp.setStatus(200);
            resp.setContentType("text/plain; charset=UTF-8");
            resp.getWriter().write("OK");

        } catch (Exception e) {
            // Log full stack trace in Tomcat console/log
            e.printStackTrace();

            try {
                resp.setStatus(500);
                resp.setContentType("text/plain; charset=UTF-8");

                PrintWriter out = resp.getWriter();
                out.println("Publish failed");
                out.println("Exception: " + e.getClass().getName());
                out.println("Message: " + e.getMessage());
                out.println("Cause chain:");

                Throwable t = e;
                int i = 0;
                while (t != null && i < 10) {
                    out.println("  [" + i + "] " + t.getClass().getName() + " : " + t.getMessage());
                    t = t.getCause();
                    i++;
                }
            } catch (Exception ignored) {
            }
        }
    }
}
