package ESP32.Emulator.device;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DeviceRegistry {
    private final Map<String, Device> devices = new HashMap<>();

    public void register(Device device) {
        devices.put(
                device.getId(),
                device
        );
    }

    public Device get(String id) {
        Device device = devices.get(id);
        if (device == null) {
            throw new IllegalArgumentException("Device not found: " + id);
        }
        return device;
    }

    public Collection<Device> getAll() {
        return devices.values();
    }
}
