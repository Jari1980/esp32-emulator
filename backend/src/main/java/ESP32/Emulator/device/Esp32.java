package ESP32.Emulator.device;

import ESP32.Emulator.gpio.GpioPin;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Esp32 {
    @Getter
    private final String id;
    @Getter
    private final String name;

    private final DeviceRegistry deviceRegistry = new DeviceRegistry();
    private final Map<Integer, GpioPin> pins = new HashMap<>();

    public Esp32(String id, String name) {
        this.id = id;
        this.name = name;
        for (int i = 0; i <= 39; i++) {
            pins.put(i, new GpioPin(i));
        }
    }

    public void addDevice(Device device){
        deviceRegistry.register(device);
    }

    public Collection<Device> getDevices() {
        return deviceRegistry.getAll();
    }

    public GpioPin getPin(int number) {
        return pins.get(number);
    }

    public Map<Integer, GpioPin> getPins() {
        return Map.copyOf(pins);
    }

    public Device getDevice(String id) {
        return deviceRegistry.get(id);
    }
}
