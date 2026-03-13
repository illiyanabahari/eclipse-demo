package com.demo.bootstrap;

import com.demo.mq.RabbitConsumerWorker;
import com.demo.mq.RabbitManager;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppBootstrapListener implements ServletContextListener {

    private Thread consumerThread;
    private RabbitConsumerWorker worker;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // RabbitMQ connection settings
        RabbitManager manager = new RabbitManager("localhost", 5672, "admin", "admin123");

        worker = new RabbitConsumerWorker(manager, "demo.queue");
        consumerThread = new Thread(worker, "rabbit-consumer-thread");
        consumerThread.setDaemon(true);
        consumerThread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (worker != null) worker.shutdown();
        if (consumerThread != null) {
            try { consumerThread.interrupt(); } catch (Exception ignored) {}
        }
    }
}
