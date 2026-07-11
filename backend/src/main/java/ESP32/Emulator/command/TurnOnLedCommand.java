package ESP32.Emulator.command;

public class TurnOnLedCommand implements Command{
    private final String deviceId;

    public TurnOnLedCommand(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }
}
