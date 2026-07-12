package ESP32.Emulator.device;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeviceRegistryTest {
    @Test
    void shouldRegisterAndFindDevice() {
        DeviceRegistry registry = new DeviceRegistry();

        Device device = new TestDevice(
                "device-001",
                "Test Device"
        );

        registry.register(device);

        Device result = registry.get("device-001");

        assertEquals(device, result);
    }


    @Test
    void shouldThrowWhenDeviceDoesNotExist() {
        DeviceRegistry registry = new DeviceRegistry();

        assertThrows(
                IllegalArgumentException.class,
                () -> registry.get("missing-device")
        );
    }

    private static class TestDevice implements Device {
        private final String id;
        private final String name;

        public TestDevice(String id, String name) {
            this.id = id;
            this.name = name;
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
            return "test";
        }
    }
}
