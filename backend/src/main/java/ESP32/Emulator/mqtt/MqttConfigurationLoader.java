package ESP32.Emulator.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class MqttConfigurationLoader {
    private final ObjectMapper mapper;

    public MqttConfigurationLoader() {
        this.mapper = new ObjectMapper();
    }

    public MqttConfiguration load() {
        try {
            InputStream input =
                    getClass()
                            .getClassLoader()
                            .getResourceAsStream(
                                    "mqtt-config.json"
                            );

            return mapper.readValue(
                    input,
                    MqttConfiguration.class
            );

        } catch (Exception e) {
            throw new RuntimeException("Could not load MQTT configuration", e);
        }
    }
}
