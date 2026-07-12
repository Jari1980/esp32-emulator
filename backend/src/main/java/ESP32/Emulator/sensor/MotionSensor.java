package ESP32.Emulator.sensor;

import ESP32.Emulator.device.Device;
import ESP32.Emulator.device.DeviceStateChangedEvent;
import ESP32.Emulator.device.StateProvider;
import ESP32.Emulator.device.Updatable;
import ESP32.Emulator.event.EventBus;

import java.time.Instant;
import java.util.Map;

public class MotionSensor implements Device, Updatable, StateProvider {
    private final String id;
    private final String name;

    private final EventBus eventBus;
    private boolean motionDetected;

    public MotionSensor(String id, String name, EventBus eventBus) {
        this.id = id;
        this.name = name;
        this.eventBus = eventBus;
        this.motionDetected = false;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isMotionDetected() {
        return motionDetected;
    }

    public void setMotionDetected(boolean detected) {

        if (this.motionDetected != detected) {

            this.motionDetected = detected;

            eventBus.publish(
                    new DeviceStateChangedEvent(
                            id,
                            getState(),
                            Instant.now()
                    )
            );
        }
    }

    public void triggerMotion() {
        setMotionDetected(true);
    }

    public void clearMotion() {
        setMotionDetected(false);
    }

    @Override
    public void update() {

        // Temporary simulation
        boolean detected = Math.random() > 0.7;

        setMotionDetected(detected);
    }

    @Override
    public Map<String, Object> getState() {
        return Map.of("motionDetected", motionDetected);
    }
}
