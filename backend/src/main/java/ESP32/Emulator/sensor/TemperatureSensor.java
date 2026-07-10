package ESP32.Emulator.sensor;

import ESP32.Emulator.device.Device;
import ESP32.Emulator.event.EventBus;
import ESP32.Emulator.event.TemperatureChangedEvent;
import lombok.Getter;
import lombok.Setter;


public class TemperatureSensor implements Device {
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
                new TemperatureChangedEvent(
                        oldTemperature,
                        temperature
                )
        );
    }
}
