package ESP32.Emulator.publisher;

import ESP32.Emulator.dto.Esp32StateDto;

public class ConsoleStatePublisher implements StatePublisher{
    @Override
    public void publish(Esp32StateDto state) {

        System.out.println(
                "STATE UPDATE:"
        );

        System.out.println(state);
    }
}
