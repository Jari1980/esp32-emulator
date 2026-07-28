package controlunit.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import tools.jackson.databind.ObjectMapper;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class WebSocketSessionManager {
    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }

    public Set<WebSocketSession> getSessions() {
        return sessions;
    }

    public void broadcast(String payload) {

        sessions.forEach(session -> {
            try {
                String message = """
                {
                  "type":"STATE",
                  "payload":%s
                }
                """.formatted(payload);

                session.sendMessage(new TextMessage(message));

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }
}
