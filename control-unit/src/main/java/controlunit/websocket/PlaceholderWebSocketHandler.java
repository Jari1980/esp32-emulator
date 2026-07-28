package controlunit.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class PlaceholderWebSocketHandler extends TextWebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("WebSocket client connected");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        //Empty for now
    }
}
