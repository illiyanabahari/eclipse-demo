package com.demo.mq;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import jakarta.websocket.Session;

public class WebSocketBroadcaster {

    private static final Set<Session> sessions = new CopyOnWriteArraySet<>();

    public static void add(Session session) {
        sessions.add(session);
    }

    public static void remove(Session session) {
        sessions.remove(session);
    }

    public static void broadcast(String message) {
        for (Session s : sessions) {
            if (s.isOpen()) {
                try {
                    s.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    try { s.close(); } catch (IOException ignored) {}
                    sessions.remove(s);
                }
            } else {
                sessions.remove(s);
            }
        }
    }
}
