package ESP32.Emulator.emulator;

import ESP32.Emulator.actuator.Led;
import ESP32.Emulator.device.Esp32;
import ESP32.Emulator.event.EventBus;
import ESP32.Emulator.gpio.GpioPin;
import ESP32.Emulator.sensor.TemperatureSensor;

public class EmulatorApplication {
    public static void main(String[] args) {

        Esp32Emulator emulator = new Esp32Emulator();

        emulator.getEventBus()
                .subscribe(event -> {
                    System.out.println(
                            "EVENT: " + event
                    );
                });

        System.out.println(emulator.getEsp32().getName());

        emulator.getTemperatureSensor()
                .setTemperature(25.5);

        emulator.getLed()
                .turnOn();

        System.out.println("LED: " + emulator.getLed().isOn());
    }
}
