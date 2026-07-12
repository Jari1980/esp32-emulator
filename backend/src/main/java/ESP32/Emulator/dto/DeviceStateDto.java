package ESP32.Emulator.dto;

import java.util.Map;

public record DeviceStateDto(
        String deviceId,
        String type,
        Map<String,Object> state
) {
}
