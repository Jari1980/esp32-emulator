package ESP32.Emulator.camera;

import ESP32.Emulator.device.Device;
import ESP32.Emulator.device.StateProvider;
import ESP32.Emulator.device.Updatable;

import java.util.Map;

public class VirtualCamera implements Device, StateProvider, Updatable {
    private final String id;
    private final String name;
    private boolean online;
    private int frame = 0;

    public VirtualCamera(String id, String name) {
        this.id = id;
        this.name = name;
        this.online = true;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "camera";
    }

    @Override
    public void update() {
        frame++;
    }

    @Override
    public Map<String, Object> getState() {
        return Map.of(
                "online", online,
                "type", "virtual",
                "frame", frame
        );
    }
}
