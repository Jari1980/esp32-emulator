package ESP32.Emulator.event;

import java.util.ArrayList;
import java.util.List;

public class EventBus {
    private final List<EventListener> listeners = new ArrayList<>();


    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    public void publish(EmulatorEvent event) {
        for (EventListener listener : listeners) {
            listener.onEvent(event);
        }
    }
}
