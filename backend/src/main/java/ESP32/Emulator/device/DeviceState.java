package ESP32.Emulator.device;

import java.util.Map;

public record DeviceState(
        String deviceId,
        String deviceType,
        Map<String,Object> state
) {
}
