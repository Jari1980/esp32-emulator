package ESP32.Emulator.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface WebSocketMessage {
    @JsonProperty("type")
    MessageType type();
}
