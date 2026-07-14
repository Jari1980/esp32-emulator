package ESP32.Emulator.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CommandEnvelope(
        CommandMessage payload
) implements WebSocketMessage {

    @Override
    public MessageType type() {
        return MessageType.COMMAND;
    }
}
