package ESP32.Emulator.device;

import ESP32.Emulator.gpio.GpioPin;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Esp32 {
    @Getter
    private final String id;
    @Getter
    private final String name;

    private final List<Device> devices = new ArrayList<>();
    private final Map<Integer, GpioPin> pins = new HashMap<>();

    public Esp32(String id, String name) {
        this.id = id;
        this.name = name;
        for (int i = 0; i <= 39; i++) {
            pins.put(i, new GpioPin(i));
        }
    }

    public void addDevice(Device device){
        devices.add(device);
    }

    public List<Device> getDevice() {
        return List.copyOf(devices);
    }

    public GpioPin getPin(int number) {
        return pins.get(number);
    }

    public Map<Integer, GpioPin> getPins() {
        return Map.copyOf(pins);
    }
}
