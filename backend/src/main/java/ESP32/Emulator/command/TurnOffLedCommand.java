package ESP32.Emulator.command;

import ESP32.Emulator.actuator.Led;
import ESP32.Emulator.device.Device;

public class TurnOffLedCommand implements Command{
    private final String deviceId;

    public TurnOffLedCommand(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public void execute(Device device) {
        if (!(device instanceof Led led)) {
            throw new IllegalArgumentException(
                    "Device is not an LED: " + device.getId()
            );
        }
        led.turnOff();
    }
}
