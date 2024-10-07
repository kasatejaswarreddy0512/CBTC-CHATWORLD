package com.cbt.chatworld.handler;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler{
	private static Set<WebSocketSession> sessions=new HashSet<>();
	
	@Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("New connection established: " + session.getId());
    }

	 @Override
	    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	        String receivedMessage = message.getPayload();
	        System.out.println("Received message: " + receivedMessage);

	        // Broadcast the message to all connected sessions
	        for (WebSocketSession webSocketSession : sessions) {
	            if (webSocketSession.isOpen()) {
	                try {
	                    webSocketSession.sendMessage(new TextMessage(receivedMessage));
	                } catch (Exception e) {
	                    System.out.println("Error sending message to session " + webSocketSession.getId() + ": " + e.getMessage());
	                }
	            }
	        }
	    }
	
	
	
	@Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("Connection closed: " + session.getId());
    }
	
	
}
