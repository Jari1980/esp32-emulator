package ESP32.Emulator.emulator;

import ESP32.Emulator.command.Command;
import ESP32.Emulator.command.CommandHandler;
import ESP32.Emulator.device.*;
import ESP32.Emulator.event.EventBus;
import ESP32.Emulator.state.Esp32State;
import ESP32.Emulator.config.ConfigurationLoader;
import ESP32.Emulator.config.Esp32Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Esp32Emulator implements EmulatorLifecycle{
    private final EventBus eventBus;
    private final Esp32 esp32;
    private final CommandHandler commandHandler;

    private Esp32State currentState;
    private long uptime;


    @Override
    public void setup() {
        System.out.println("ESP32 starting...");
        System.out.println("Device: " + esp32.getName());
    }

    @Override
    public void loop() {
        uptime++;
        for (Device device : esp32.getDevices()) {
            if (device instanceof Updatable updatable) {
                updatable.update();
            }
        }
        currentState = createState();
    }

    public Esp32Emulator(Esp32 esp32, EventBus eventBus, CommandHandler commandHandler) {
        this.esp32 = esp32;
        this.eventBus = eventBus;
        this.commandHandler = commandHandler;
        this.currentState = createState();
        eventBus.subscribe(event -> {
            currentState = createState();
        });
    }

    public void refreshState() {
        currentState = createState();
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public Esp32 getEsp32() {
        return esp32;
    }

    public Esp32State getCurrentState() {
        return currentState;
    }

    private Esp32State createState() {
        List<DeviceState> devices = new ArrayList<>();

        for(Device device : esp32.getDevices()) {
            if(device instanceof StateProvider provider) {
                devices.add(
                        new DeviceState(
                                device.getId(),
                                device.getType(),
                                provider.getState()
                        )
                );
            }
        }
        return new Esp32State(
                esp32.getId(),
                esp32.getName(),
                uptime,
                devices
        );
    }

    public void execute(Command command) {
        commandHandler.execute(command);
    }
}
