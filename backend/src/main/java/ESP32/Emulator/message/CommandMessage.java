package ESP32.Emulator.message;

public record CommandMessage(
        String command,
        String deviceId
) implements WebSocketMessage{
    @Override
    public MessageType type() {
        return MessageType.COMMAND;
    }
}
