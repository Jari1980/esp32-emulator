package controlunit.websocket;

import controlunit.mqtt.MqttClientService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tools.jackson.databind.ObjectMapper;

@Component
public class StateWebSocketHandler extends TextWebSocketHandler {
    private final WebSocketSessionManager sessionManager;
    private final MqttClientService mqttClientService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public StateWebSocketHandler(WebSocketSessionManager sessionManager, MqttClientService mqttClientService) {
        this.sessionManager = sessionManager;
        this.mqttClientService = mqttClientService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessionManager.addSession(session);
        System.out.println("WebSocket client connected");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessionManager.removeSession(session);
        System.out.println("WebSocket client disconnected");
    }

    @Override
    protected void handleTextMessage(
            WebSocketSession session,
            TextMessage message
    ) {

        try {
            WebSocketMessage wsMessage = objectMapper.readValue(message.getPayload(), WebSocketMessage.class);

            System.out.println("WEBSOCKET MESSAGE TYPE:");
            System.out.println(wsMessage.getType());

            if ("COMMAND".equals(wsMessage.getType())) {
                String payload = objectMapper.writeValueAsString(wsMessage.getPayload());

                mqttClientService.publishCommand(payload);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
