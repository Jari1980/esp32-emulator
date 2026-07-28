package controlunit.websocket;

public class WebSocketMessage {
    private String type;
    private Object payload;

    public String getType() {
        return type;
    }

    public Object getPayload() {
        return payload;
    }
}
