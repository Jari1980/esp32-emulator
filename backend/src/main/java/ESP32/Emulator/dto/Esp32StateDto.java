package ESP32.Emulator.dto;

import java.util.List;

public record Esp32StateDto(
        String id,
        String name,
        long uptime,
        List<DeviceStateDto> devices
) {
}
