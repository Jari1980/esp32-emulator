package ESP32.Emulator.command;

import ESP32.Emulator.actuator.Led;
import ESP32.Emulator.device.Device;

public class TurnOnLedCommand implements Command{
    private final String deviceId;

    public TurnOnLedCommand(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public void execute(Device device) {
        Led led = (Led) device;
        led.turnOn();
    }
}
