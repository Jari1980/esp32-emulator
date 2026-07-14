package ESP32.Emulator.command;

import ESP32.Emulator.device.Device;
import ESP32.Emulator.sensor.MotionSensor;

public class TriggerMotionCommand implements Command{
    private final String deviceId;

    public TriggerMotionCommand(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public void execute(Device device) {
        if (!(device instanceof MotionSensor sensor)) {
            throw new IllegalArgumentException(
                    "Device is not an motion sensor: " + device.getId()
            );
        }
        sensor.triggerMotion();
    }
}
