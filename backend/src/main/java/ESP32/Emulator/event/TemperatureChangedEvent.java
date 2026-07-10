package ESP32.Emulator.event;

import java.time.Instant;

public record TemperatureChangedEvent(
        double oldValue,
        double newValue,
        Instant timestamp
) implements EmulatorEvent {

    public TemperatureChangedEvent(double oldValue, double newValue
    ) {
        this(oldValue, newValue, Instant.now());
    }
}
