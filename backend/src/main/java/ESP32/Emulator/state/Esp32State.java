package ESP32.Emulator.state;

import java.util.Map;

public record Esp32State(
        String id,
        String name,
        long uptime,
        Map<String, Object> deviceStates
) {
}
