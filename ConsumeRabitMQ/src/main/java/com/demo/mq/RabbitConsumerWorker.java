package com.demo.mq;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

public class RabbitConsumerWorker implements Runnable {

    private final RabbitManager rabbitManager;
    private final String queueName;

    private volatile boolean running = true;
    private Connection connection;
    private Channel channel;

    public RabbitConsumerWorker(RabbitManager rabbitManager, String queueName) {
        this.rabbitManager = rabbitManager;
        this.queueName = queueName;
    }

    @Override
    public void run() {
        while (running) {
            try {
                connection = rabbitManager.newConnection("jsp-servlet-consumer");
                channel = connection.createChannel();

                // Ensure queue exists (safe even if already exists)
                channel.queueDeclare(queueName, true, false, false, null);

                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String msg = new String(delivery.getBody(), StandardCharsets.UTF_8);
                    WebSocketBroadcaster.broadcast(msg);
                };

                // autoAck = true for demo simplicity
                channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});

                // Keep thread alive while connection is active
                while (running && connection.isOpen()) {
                    Thread.sleep(1000);
                }

            } catch (Exception ex) {
                // If RabbitMQ restarts etc, wait and retry
                try { Thread.sleep(3000); } catch (InterruptedException ignored) {}
            } finally {
                closeSilently();
            }
        }
    }

    public void shutdown() {
        running = false;
        closeSilently();
    }

    private void closeSilently() {
        try { if (channel != null && channel.isOpen()) channel.close(); } catch (Exception ignored) {}
        try { if (connection != null && connection.isOpen()) connection.close(); } catch (Exception ignored) {}
        channel = null;
        connection = null;
    }
}
