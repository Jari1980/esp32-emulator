package ESP32.Emulator.device;

import java.util.Map;

public interface StateProvider {
    Map<String, Object> getState();
}
