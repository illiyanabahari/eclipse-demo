package com.demo.mq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitManager {

    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final String virtualHost;

    public RabbitManager(String host, int port, String username, String password) {
        this(host, port, username, password, "/"); // default vhost
    }

    public RabbitManager(String host, int port, String username, String password, String virtualHost) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.virtualHost = virtualHost;
    }

    public Connection newConnection(String connectionName) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);

        // Make failures show up fast and clearly
        factory.setConnectionTimeout(5000);  // 5s
        factory.setHandshakeTimeout(5000);   // 5s
        factory.setShutdownTimeout(5000);    // 5s

        // Auto-recover if Rabbit restarts
        factory.setAutomaticRecoveryEnabled(true);
        factory.setNetworkRecoveryInterval(3000);

        return factory.newConnection(connectionName);
    }
}

