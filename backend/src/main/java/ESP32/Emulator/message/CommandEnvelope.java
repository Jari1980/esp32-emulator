package ESP32.Emulator.message;

public record CommandEnvelope(
        CommandMessage payload
) implements WebSocketMessage {

    @Override
    public MessageType type() {
        return MessageType.COMMAND;
    }
}
