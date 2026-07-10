package ESP32.Emulator.emulator;

import ESP32.Emulator.actuator.Led;
import ESP32.Emulator.device.Esp32;
import ESP32.Emulator.event.EventBus;
import ESP32.Emulator.gpio.GpioPin;
import ESP32.Emulator.sensor.TemperatureSensor;

public class Esp32Emulator {
    private final EventBus eventBus;
    private Esp32 esp32;
    private TemperatureSensor temperatureSensor;
    private Led led;


    public Esp32Emulator() {
        this.eventBus = new EventBus();
        initialize();
    }

    private void initialize() {

        esp32 = new Esp32(
                "esp32-001",
                "Virtual ESP32"
        );

        temperatureSensor =
                new TemperatureSensor(
                        "temp-001",
                        "Temperature Sensor",
                        eventBus
                );

        esp32.addDevice(temperatureSensor);

        GpioPin gpio2 = esp32.getPin(2);

        led = new Led(
                "led-001",
                "Built-in LED",
                gpio2
        );
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public Esp32 getEsp32() {
        return esp32;
    }

    public TemperatureSensor getTemperatureSensor() {
        return temperatureSensor;
    }

    public Led getLed() {
        return led;
    }
}
