package ESP32.Emulator.publisher;

import ESP32.Emulator.dto.Esp32StateDto;
import ESP32.Emulator.websocket.EmulatorWebSocketServer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebSocketStatePublisher implements StatePublisher{
    private final EmulatorWebSocketServer server;
    private final ObjectMapper mapper;

    public WebSocketStatePublisher(EmulatorWebSocketServer server) {
        this.server = server;
        this.mapper = new ObjectMapper();
    }

    @Override
    public void publish(Esp32StateDto state) {
        try {
            String json = mapper.writeValueAsString(state);
            server.broadcast(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
