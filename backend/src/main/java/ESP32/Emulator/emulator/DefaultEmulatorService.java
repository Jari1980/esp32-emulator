package ESP32.Emulator.emulator;

import ESP32.Emulator.command.Command;
import ESP32.Emulator.state.Esp32State;

public class DefaultEmulatorService implements EmulatorService{
    private final Esp32Emulator emulator;

    public DefaultEmulatorService(Esp32Emulator emulator) {
        this.emulator = emulator;
    }

    @Override
    public Esp32State getCurrentState() {
        return emulator.getCurrentState();
    }

    @Override
    public void execute(Command command) {
        emulator.execute(command);
    }
}
