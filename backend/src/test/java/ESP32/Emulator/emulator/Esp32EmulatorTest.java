package ESP32.Emulator.emulator;

import ESP32.Emulator.command.TurnOnLedCommand;
import ESP32.Emulator.device.DeviceState;
import ESP32.Emulator.publisher.ConsoleStatePublisher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Esp32EmulatorTest {
    @Test
    void shouldTurnLedOnThroughCommand() {
        Esp32Emulator emulator = new EmulatorBootstrap().create();
        EmulatorService service = new DefaultEmulatorService(emulator, new ConsoleStatePublisher());

        service.execute(new TurnOnLedCommand("led-001"));

        DeviceState ledState = service.getCurrentState()
                .devices()
                .stream()
                .filter(device -> device.deviceId().equals("led-001"))
                .findFirst()
                .orElseThrow();

        assertTrue((Boolean) ledState.state().get("ledOn"));
    }
}
