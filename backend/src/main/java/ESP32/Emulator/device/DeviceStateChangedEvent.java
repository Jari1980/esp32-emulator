package ESP32.Emulator.device;

import ESP32.Emulator.event.EmulatorEvent;

import java.time.Instant;
import java.util.Map;

public record DeviceStateChangedEvent(
        String deviceId,
        Map<String, Object> state,
        Instant timestamp
) implements EmulatorEvent {
}
