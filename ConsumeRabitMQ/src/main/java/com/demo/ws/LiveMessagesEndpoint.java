package com.demo.ws;

import com.demo.mq.WebSocketBroadcaster;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/live")
public class LiveMessagesEndpoint {

    @OnOpen
    public void onOpen(Session session) {
        WebSocketBroadcaster.add(session);
        // Optional greeting
        WebSocketBroadcaster.broadcast("[System] A client connected. Total: " + session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        WebSocketBroadcaster.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable t) {
        WebSocketBroadcaster.remove(session);
    }
}


