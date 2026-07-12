package ESP32.Emulator.publisher;

import ESP32.Emulator.dto.Esp32StateDto;

public interface StatePublisher {
    void publish(Esp32StateDto state);
}
