package ESP32.Emulator.sensor;

import ESP32.Emulator.device.Device;
import lombok.Getter;
import lombok.Setter;


public class TemperatureSensor implements Device {
    private final String id;
    private final String name;

    @Setter
    @Getter
    private double temperature;

    public TemperatureSensor(String id, String name){
        this.id = id;
        this.name = name;
        this.temperature = 20.0;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
