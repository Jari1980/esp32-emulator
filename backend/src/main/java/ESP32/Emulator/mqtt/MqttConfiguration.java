package ESP32.Emulator.mqtt;

public record MqttConfiguration(
        String broker,
        String clientId
) {
}
