package ESP32.Emulator.event;

import java.time.Instant;

public record LedStateChangedEvent(
        String deviceId,
        boolean oldState,
        boolean newState,
        Instant timestamp
) implements EmulatorEvent{
}
