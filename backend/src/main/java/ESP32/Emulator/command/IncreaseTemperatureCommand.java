package ESP32.Emulator.command;

import ESP32.Emulator.device.Device;
import ESP32.Emulator.sensor.MotionSensor;
import ESP32.Emulator.sensor.TemperatureSensor;

public class IncreaseTemperatureCommand implements Command{
    private final String deviceId;

    public IncreaseTemperatureCommand(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public void execute(Device device) {
        if (!(device instanceof TemperatureSensor sensor)) {
            throw new IllegalArgumentException(
                    "Device is not an temperature sensor: " + device.getId()
            );
        }

        sensor.setTemperature(sensor.getTemperature() + 0.5);
    }
}
