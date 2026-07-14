package ESP32.Emulator.command;

import ESP32.Emulator.device.Device;
import ESP32.Emulator.sensor.MotionSensor;

public class ResetMotionCommand implements Command{
    private final String deviceId;

    public ResetMotionCommand(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public void execute(Device device) {
        MotionSensor sensor = (MotionSensor) device;
        sensor.clearMotion();
    }
}
