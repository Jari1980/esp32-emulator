package ESP32.Emulator.emulator;

import ESP32.Emulator.command.TurnOnLedCommand;
import ESP32.Emulator.device.DeviceState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Esp32EmulatorIntegrationTest {
    @Test
    void shouldCreateDevicesAndExecuteCommand() {
        Esp32Emulator emulator = new EmulatorBootstrap().create();

        EmulatorService service = new DefaultEmulatorService(emulator);

        service.execute(new TurnOnLedCommand("led-001"));

        DeviceState ledState =
                service.getCurrentState().devices()
                        .stream()
                        .filter(device ->
                                device.deviceId()
                                        .equals("led-001")
                        )
                        .findFirst()
                        .orElseThrow();

        assertEquals(true, ledState.state().get("ledOn"));
    }
}
