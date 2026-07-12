package ESP32.Emulator.sensor;

import ESP32.Emulator.device.DeviceStateChangedEvent;
import ESP32.Emulator.event.EventBus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemperatureSensorTest {
    @Test
    void shouldUpdateTemperature() {
        EventBus eventBus = new EventBus();

        TemperatureSensor sensor = new TemperatureSensor(
                        "temp-001",
                        "Temperature Sensor",
                        eventBus
        );

        sensor.setTemperature(25.5);

        assertEquals(25.5, sensor.getTemperature());
    }


    @Test
    void shouldPublishEventWhenTemperatureChanges() {

        EventBus eventBus = new EventBus();

        final boolean[] eventReceived = {false};


        eventBus.subscribe(event -> {
            if (event instanceof DeviceStateChangedEvent) {
                eventReceived[0] = true;
            }
        });


        TemperatureSensor sensor = new TemperatureSensor(
                        "temp-001",
                        "Temperature Sensor",
                        eventBus
        );

        sensor.setTemperature(25.5);

        assertTrue(eventReceived[0]);
    }
}
