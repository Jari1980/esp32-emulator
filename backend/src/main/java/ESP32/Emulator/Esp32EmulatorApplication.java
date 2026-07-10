package ESP32.Emulator;

import ESP32.Emulator.emulator.EmulatorRunner;
import ESP32.Emulator.emulator.Esp32Emulator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Esp32EmulatorApplication {

	public static void main(String[] args) {

        Esp32Emulator emulator = new Esp32Emulator();

        emulator.getEventBus()
                .subscribe(event -> {
                    System.out.println("EVENT: " + event);
                });

        EmulatorRunner runner = new EmulatorRunner(emulator);

        System.out.println(emulator.getState());
        runner.start();

	}

}
