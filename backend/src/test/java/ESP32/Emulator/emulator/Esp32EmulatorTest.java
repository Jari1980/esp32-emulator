package ESP32.Emulator.emulator;

import ESP32.Emulator.command.TurnOnLedCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Esp32EmulatorTest {
    @Test
    void shouldTurnLedOnThroughCommand() {
        Esp32Emulator emulator = new EmulatorBootstrap().create();
        EmulatorService service = new DefaultEmulatorService(emulator);

        service.execute(new TurnOnLedCommand("led-001"));

        assertTrue(
                service.getCurrentState()
                        .deviceStates()
                        .get("ledOn")
                        .equals(true)
        );
    }
}
