package ESP32.Emulator.device;

import ESP32.Emulator.actuator.Led;
import ESP32.Emulator.event.EventBus;
import ESP32.Emulator.gpio.GpioPin;
import ESP32.Emulator.sensor.MotionSensor;
import ESP32.Emulator.sensor.TemperatureSensor;
import ESP32.Emulator.config.DeviceConfig;
import ESP32.Emulator.config.Esp32Config;

public class Esp32Factory {
    private final EventBus eventBus;


    public Esp32Factory(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public Esp32 create(Esp32Config config) {
        Esp32 esp32 = new Esp32(
                        config.id(),
                        config.name()
        );

        for(DeviceConfig device : config.devices()) {
            switch(device.type()) {

                case "temperature" -> {
                    esp32.addDevice(
                            new TemperatureSensor(
                                    device.id(),
                                    device.name(),
                                    eventBus
                            )
                    );
                }

                case "led" -> {
                    GpioPin pin = esp32.getPin(device.gpio());
                    esp32.addDevice(new Led(device.id(), device.name(), pin, eventBus));
                }

                case "motion" -> {
                    esp32.addDevice(
                            new MotionSensor(
                                    device.id(),
                                    device.name(),
                                    eventBus
                            )
                    );
                }

                default ->
                        throw new IllegalArgumentException(
                                "Unknown device type: "
                                        + device.type()
                        );
            }
        }
        return esp32;
    }
}
