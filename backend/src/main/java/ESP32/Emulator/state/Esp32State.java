package ESP32.Emulator.state;

import ESP32.Emulator.device.DeviceState;
import java.util.List;


public record Esp32State(
        String id,
        String name,
        long uptime,
        List<DeviceState> devices
) {
}
