package ESP32.Emulator.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class ConfigurationLoader {
    private final ObjectMapper mapper;


    public ConfigurationLoader() {
        this.mapper = new ObjectMapper();
    }

    public Esp32Config load() {
        try {
            InputStream input =
                    getClass()
                            .getClassLoader()
                            .getResourceAsStream(
                                    "esp32-config.json"
                            );

            return mapper.readValue(
                    input,
                    Esp32Config.class
            );

        } catch (Exception e) {
            throw new RuntimeException("Could not load ESP32 configuration", e);
        }
    }
}
