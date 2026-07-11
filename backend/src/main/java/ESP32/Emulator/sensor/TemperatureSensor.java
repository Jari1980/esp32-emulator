package ESP32.Emulator.sensor;

import ESP32.Emulator.device.Device;
import ESP32.Emulator.device.DeviceStateChangedEvent;
import ESP32.Emulator.device.StateProvider;
import ESP32.Emulator.device.Updatable;
import ESP32.Emulator.event.EventBus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;


public class TemperatureSensor implements Device, Updatable, StateProvider {
    private final String id;
    private final String name;

    private final EventBus eventBus;

    @Getter
    private double temperature;

    public TemperatureSensor(String id, String name, EventBus eventBus){
        this.id = id;
        this.name = name;
        this.eventBus = eventBus;
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

    public void setTemperature(double temperature) {

        double oldTemperature = this.temperature;

        this.temperature = temperature;

        eventBus.publish(
                new DeviceStateChangedEvent(
                        id,
                        getState(),
                        Instant.now()
                )
        );
    }

    @Override
    public void update() {
        double variation = (Math.random() - 0.5);
        setTemperature(temperature + variation);
    }

    @Override
    public Map<String, Object> getState() {
        return Map.of(
                "temperature",
                temperature
        );
    }
}
