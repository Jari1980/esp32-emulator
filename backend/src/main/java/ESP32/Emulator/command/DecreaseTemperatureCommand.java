package ESP32.Emulator.command;

import ESP32.Emulator.device.Device;
import ESP32.Emulator.sensor.TemperatureSensor;

public class DecreaseTemperatureCommand implements Command{
    private final String deviceId;

    public DecreaseTemperatureCommand(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public void execute(Device device) {
        TemperatureSensor sensor = (TemperatureSensor) device;

        sensor.setTemperature(
                sensor.getTemperature() - 0.5
        );
    }
}
