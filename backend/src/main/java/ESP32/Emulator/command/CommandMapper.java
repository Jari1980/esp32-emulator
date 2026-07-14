package ESP32.Emulator.command;

import ESP32.Emulator.message.CommandMessage;

public class CommandMapper {
    public Command map(CommandMessage message) {
        return switch (message.command()) {
            case "TURN_ON" ->
                    new TurnOnLedCommand(message.deviceId());
            case "TURN_OFF" ->
                    new TurnOffLedCommand(message.deviceId());
            case "TRIGGER_MOTION" ->
                    new TriggerMotionCommand(message.deviceId());
            case "RESET_MOTION" ->
                    new ResetMotionCommand(message.deviceId());
            case "INCREASE_TEMPERATURE" ->
                    new IncreaseTemperatureCommand(message.deviceId());

            case "DECREASE_TEMPERATURE" ->
                    new DecreaseTemperatureCommand(message.deviceId());
            default ->
                    throw new IllegalArgumentException(
                            "Unknown command: " + message.command()
                    );
        };
    }
}
