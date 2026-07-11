package ESP32.Emulator.actuator;

import ESP32.Emulator.device.Device;
import ESP32.Emulator.gpio.GpioPin;
import ESP32.Emulator.gpio.PinMode;
import ESP32.Emulator.gpio.PinState;
import lombok.Getter;

public class Led implements Device {
    @Getter
    private final String id;
    @Getter
    private final String name;

    private final GpioPin pin;

    public Led(String id, String name, GpioPin pin){
        this.id = id;
        this.name = name;
        this.pin = pin;

        pin.setMode(PinMode.OUTPUT);
    }

    public void turnOn(){
        pin.setState(PinState.HIGH);
    }

    public void turnOff() {
        pin.setState(PinState.LOW);
    }

    public boolean isOn() {
        return pin.getState() == PinState.HIGH;
    }

}
