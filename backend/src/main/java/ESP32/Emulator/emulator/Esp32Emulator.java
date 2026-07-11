package ESP32.Emulator.emulator;

import ESP32.Emulator.actuator.Led;
import ESP32.Emulator.device.Esp32;
import ESP32.Emulator.event.EventBus;
import ESP32.Emulator.gpio.GpioPin;
import ESP32.Emulator.sensor.TemperatureSensor;
import ESP32.Emulator.state.Esp32State;

public class Esp32Emulator implements EmulatorLifecycle{
    private final EventBus eventBus;
    private Esp32 esp32;
    private TemperatureSensor temperatureSensor;
    private Led led;
    private Esp32State currentState;
    private long uptime;


    @Override
    public void setup() {
        System.out.println("ESP32 starting...");
        System.out.println("Device: " + esp32.getName());
    }

    @Override
    public void loop() {
        uptime++;
        temperatureSensor.update();
        currentState = createState();
    }

    public Esp32Emulator() {
        this.eventBus = new EventBus();
        initialize();
        currentState = createState();
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

        esp32.addDevice(led);
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

    public Esp32State getCurrentState() {
        return currentState;
    }

    private Esp32State createState() {
        return new Esp32State(
                esp32.getId(),
                esp32.getName(),
                uptime,
                temperatureSensor.getTemperature(),
                led.isOn()
        );
    }
}
