package ESP32.Emulator.command;

import ESP32.Emulator.actuator.Led;
import ESP32.Emulator.device.Device;
import ESP32.Emulator.device.DeviceRegistry;
import ESP32.Emulator.device.Esp32;

public class CommandHandler {
    private final DeviceRegistry registry;


    public CommandHandler(DeviceRegistry registry) {
        this.registry = registry;
    }

    public void execute(Command command) {
        Device device = registry.get(command.getDeviceId());

        if (device == null) {
            throw new IllegalArgumentException("Unknown device: " + command.getDeviceId());
        }
        command.execute(device);
    }
}
