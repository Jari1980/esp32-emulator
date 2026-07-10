package ESP32.Emulator.emulator;

import ESP32.Emulator.actuator.Led;
import ESP32.Emulator.device.Esp32;
import ESP32.Emulator.event.EventBus;
import ESP32.Emulator.gpio.GpioPin;
import ESP32.Emulator.sensor.TemperatureSensor;

public class EmulatorApplication {
    public static void main(String[] args) {

        EventBus eventBus = new EventBus();

        eventBus.subscribe(event -> {
            System.out.println("EVENT: " + event);
        });

        Esp32 esp32 = new Esp32(
                "esp32-001",
                "My ESP32"
        );

        TemperatureSensor temperature =
                new TemperatureSensor(
                        "temp-001",
                        "Temperature Sensor",
                        eventBus
                );

        esp32.addDevice(temperature);

        System.out.println(esp32.getName());
        System.out.println(temperature.getTemperature());

        temperature.setTemperature(25.5);

        GpioPin gpio2 = esp32.getPin(2);
        Led led = new Led(
                "led-001",
                "Built-in LED",
                gpio2
        );
        led.turnOn();
        System.out.println("LED state: " + led.isOn());
    }
}
