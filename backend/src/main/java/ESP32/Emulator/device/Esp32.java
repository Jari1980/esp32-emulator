package ESP32.Emulator.device;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Esp32 {
    @Getter
    private final String id;
    @Getter
    private final String name;

    private final List<Device> devices = new ArrayList<>();

    public Esp32(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addDevice(Device device){
        devices.add(device);
    }

    public List<Device> getDevice() {
        return List.copyOf(devices);
    }

}
