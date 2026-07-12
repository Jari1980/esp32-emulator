package ESP32.Emulator.emulator;

import ESP32.Emulator.command.Command;
import ESP32.Emulator.state.Esp32State;

public interface EmulatorService {
    Esp32State getCurrentState();
    void execute(Command command);
}
