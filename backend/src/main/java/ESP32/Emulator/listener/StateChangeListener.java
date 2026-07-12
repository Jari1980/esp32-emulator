package ESP32.Emulator.listener;

import ESP32.Emulator.dto.Esp32StateDto;
import ESP32.Emulator.emulator.Esp32Emulator;
import ESP32.Emulator.event.EmulatorEvent;
import ESP32.Emulator.event.EventListener;
import ESP32.Emulator.mapper.StateMapper;
import ESP32.Emulator.publisher.StatePublisher;

public class StateChangeListener implements EventListener {
    private final Esp32Emulator emulator;
    private final StatePublisher publisher;
    private final StateMapper mapper;


    public StateChangeListener(Esp32Emulator emulator, StatePublisher publisher) {
        this.emulator = emulator;
        this.publisher = publisher;
        this.mapper = new StateMapper();
    }

    @Override
    public void onEvent(EmulatorEvent event) {
        emulator.refreshState();
        Esp32StateDto state = mapper.map(emulator.getCurrentState());
        publisher.publish(state);
    }
}
