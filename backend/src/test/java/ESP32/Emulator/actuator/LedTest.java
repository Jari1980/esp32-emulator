package ESP32.Emulator.actuator;

import ESP32.Emulator.device.DeviceStateChangedEvent;
import ESP32.Emulator.event.EventBus;
import ESP32.Emulator.gpio.GpioPin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LedTest {
    @Test
    void shouldTurnLedOn() {
        EventBus eventBus = new EventBus();
        GpioPin pin = new GpioPin(2);

        Led led = new Led(
                "led-001",
                "LED",
                pin,
                eventBus
        );

        led.turnOn();

        assertTrue(led.isOn());
    }


    @Test
    void shouldTurnLedOff() {
        EventBus eventBus = new EventBus();
        GpioPin pin = new GpioPin(2);

        Led led = new Led(
                "led-001",
                "LED",
                pin,
                eventBus
        );

        led.turnOn();
        led.turnOff();

        assertFalse(led.isOn());
    }


    @Test
    void shouldPublishEventWhenStateChanges() {
        EventBus eventBus = new EventBus();
        final boolean[] eventReceived = {false};

        eventBus.subscribe(event -> {
            if (event instanceof DeviceStateChangedEvent) {
                eventReceived[0] = true;
            }
        });

        GpioPin pin = new GpioPin(2);

        Led led = new Led(
                "led-001",
                "LED",
                pin,
                eventBus
        );

        led.turnOn();

        assertTrue(eventReceived[0]);
    }
}
