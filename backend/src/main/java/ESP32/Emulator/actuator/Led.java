package ESP32.Emulator.actuator;

import ESP32.Emulator.device.Device;
import ESP32.Emulator.device.DeviceStateChangedEvent;
import ESP32.Emulator.device.StateProvider;
import ESP32.Emulator.event.EventBus;
import ESP32.Emulator.gpio.GpioPin;
import ESP32.Emulator.gpio.PinMode;
import ESP32.Emulator.gpio.PinState;
import lombok.Getter;

import java.time.Instant;
import java.util.Map;

public class Led implements Device, StateProvider {
    @Getter
    private final String id;
    @Getter
    private final String name;
    private final GpioPin pin;
    private final EventBus eventBus;

    public Led(String id, String name, GpioPin pin, EventBus eventBus){
        this.id = id;
        this.name = name;
        this.pin = pin;
        this.eventBus = eventBus;

        pin.setMode(PinMode.OUTPUT);
    }

    public void turnOn() {
        boolean oldState = isOn();
        pin.high();
        boolean newState = isOn();

        if (oldState != newState) {
            eventBus.publish(
                    new DeviceStateChangedEvent(
                            id,
                            getState(),
                            Instant.now()
                    )
            );
        }
    }

    public void turnOff() {
        boolean oldState = isOn();
        pin.low();
        boolean newState = isOn();

        if (oldState != newState) {
            eventBus.publish(
                    new DeviceStateChangedEvent(
                            id,
                            getState(),
                            Instant.now()
                    )
            );
        }
    }

    public boolean isOn() {
        return pin.getState() == PinState.HIGH;
    }

    @Override
    public Map<String, Object> getState() {
        return Map.of(
                "ledOn",
                isOn()
        );
    }

}
