package ESP32.Emulator.command;

import ESP32.Emulator.message.CommandMessage;

public class CommandMapper {
    public Command map(CommandMessage message) {
        return switch (message.command()) {
            case "TURN_ON" ->
                    new TurnOnLedCommand(message.deviceId());
            default ->
                    throw new IllegalArgumentException(
                            "Unknown command: " + message.command()
                    );
        };
    }
}
