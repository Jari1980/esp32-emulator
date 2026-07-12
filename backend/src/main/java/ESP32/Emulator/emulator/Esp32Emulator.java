package ESP32.Emulator.emulator;

import ESP32.Emulator.command.Command;
import ESP32.Emulator.command.CommandHandler;
import ESP32.Emulator.device.*;
import ESP32.Emulator.event.EventBus;
import ESP32.Emulator.state.Esp32State;
import ESP32.Emulator.config.ConfigurationLoader;
import ESP32.Emulator.config.Esp32Config;

import java.util.HashMap;
import java.util.Map;

public class Esp32Emulator implements EmulatorLifecycle{
    private final EventBus eventBus;
    private final Esp32Factory factory;

    private Esp32 esp32;
    private Esp32State currentState;
    private long uptime;
    private final CommandHandler commandHandler;



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

    public Esp32Emulator() {
        this.eventBus = new EventBus();
        this.factory = new Esp32Factory(eventBus);
        initialize();
        commandHandler = new CommandHandler(esp32);
        currentState = createState();
    }

    private void initialize() {
        ConfigurationLoader loader = new ConfigurationLoader();
        Esp32Config config = loader.load();
        esp32 = factory.create(config);
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
        Map<String,Object> states = new HashMap<>();

        for (Device device : esp32.getDevices()) {
            if (device instanceof StateProvider provider) {
                states.putAll(provider.getState());
            }
        }
        return new Esp32State(
                esp32.getId(),
                esp32.getName(),
                uptime,
                states
        );
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public void execute(Command command) {
        commandHandler.execute(command);
    }
}
