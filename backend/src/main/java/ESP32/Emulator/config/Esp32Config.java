package ESP32.Emulator.config;

import java.util.List;

public record Esp32Config(
        String id,
        String name,
        List<DeviceConfig> devices
) {
}
