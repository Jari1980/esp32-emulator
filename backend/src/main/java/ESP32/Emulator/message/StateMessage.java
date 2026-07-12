package ESP32.Emulator.message;

import ESP32.Emulator.dto.Esp32StateDto;

public record StateMessage(
        Esp32StateDto payload
) implements WebSocketMessage{
    @Override
    public MessageType type() {
        return MessageType.STATE;
    }
}
