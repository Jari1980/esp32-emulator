package ESP32.Emulator.sensor;

import ESP32.Emulator.event.EventBus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MotionSensorTest {
    @Test
    void shouldDetectMotion() {

        EventBus eventBus = new EventBus();

        MotionSensor sensor = new MotionSensor(
                        "motion-001",
                        "Motion Sensor",
                        eventBus
        );

        sensor.triggerMotion();

        assertTrue(sensor.isMotionDetected());
    }
}
