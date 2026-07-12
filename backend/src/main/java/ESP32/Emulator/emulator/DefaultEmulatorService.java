package ESP32.Emulator.emulator;

import ESP32.Emulator.command.Command;
import ESP32.Emulator.dto.Esp32StateDto;
import ESP32.Emulator.event.EventListener;
import ESP32.Emulator.mapper.StateMapper;
import ESP32.Emulator.publisher.StatePublisher;
import ESP32.Emulator.state.Esp32State;

public class DefaultEmulatorService implements EmulatorService{
    private final Esp32Emulator emulator;
    private final StatePublisher publisher;
    private final StateMapper mapper;

    public DefaultEmulatorService(Esp32Emulator emulator, StatePublisher publisher) {
        this.emulator = emulator;
        this.publisher = publisher;
        this.mapper = new StateMapper();
    }

    public void publishState(){

        Esp32StateDto dto =
                mapper.map(
                        emulator.getCurrentState()
                );

        publisher.publish(dto);
    }

    @Override
    public Esp32State getCurrentState() {
        return emulator.getCurrentState();
    }

    @Override
    public void execute(Command command) {
        emulator.execute(command);
    }

    @Override
    public void subscribe(EventListener listener) {
        emulator.getEventBus()
                .subscribe(listener);
    }
}
