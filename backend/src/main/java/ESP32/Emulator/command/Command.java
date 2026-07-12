package ESP32.Emulator.command;

import ESP32.Emulator.device.Device;

public interface Command {
    String getDeviceId();
    void execute(Device device);
}
