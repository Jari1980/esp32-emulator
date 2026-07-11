package ESP32.Emulator.config;

public record DeviceConfig(
        String type,
        String id,
        String name,
        Integer gpio
) {
}
