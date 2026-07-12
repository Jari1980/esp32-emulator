package ESP32.Emulator.emulator;

import ESP32.Emulator.command.CommandHandler;
import ESP32.Emulator.config.ConfigurationLoader;
import ESP32.Emulator.config.Esp32Config;
import ESP32.Emulator.device.Esp32;
import ESP32.Emulator.device.Esp32Factory;
import ESP32.Emulator.event.EventBus;

public class EmulatorBootstrap {
    public Esp32Emulator create() {
        EventBus eventBus = new EventBus();
        Esp32Factory factory = new Esp32Factory(eventBus);
        ConfigurationLoader loader = new ConfigurationLoader();
        Esp32Config config = loader.load();
        Esp32 esp32 = factory.create(config);
        CommandHandler commandHandler =
                new CommandHandler(
                        esp32.getDeviceRegistry()
                );

        return new Esp32Emulator(
                esp32,
                eventBus,
                commandHandler
        );
    }
}
