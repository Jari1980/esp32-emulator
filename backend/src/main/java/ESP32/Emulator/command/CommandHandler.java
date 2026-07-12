package ESP32.Emulator.command;

import ESP32.Emulator.actuator.Led;
import ESP32.Emulator.device.Device;
import ESP32.Emulator.device.Esp32;

public class CommandHandler {
    private final Esp32 esp32;


    public CommandHandler(Esp32 esp32) {
        this.esp32 = esp32;
    }

    public void execute(Command command) {
        Device device = esp32.getDevice(command.getDeviceId());
        command.execute(device);
    }
}
