package ESP32.Emulator.state;

public record Esp32State(
        String id,
        String name,
        long uptime,
        double temperature,
        boolean ledOn
) {
}
