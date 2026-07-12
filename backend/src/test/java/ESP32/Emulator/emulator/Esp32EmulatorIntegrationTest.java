package ESP32.Emulator.emulator;

import ESP32.Emulator.command.TurnOnLedCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Esp32EmulatorIntegrationTest {
    @Test
    void shouldCreateDevicesAndExecuteCommand() {
        Esp32Emulator emulator = new EmulatorBootstrap().create();

        EmulatorService service = new DefaultEmulatorService(emulator);

        service.execute(new TurnOnLedCommand("led-001"));

        Object ledState = service.getCurrentState()
                .deviceStates()
                .get("ledOn");

        assertEquals(true, ledState);
    }
}
