package ESP32.Emulator.emulator;

import ESP32.Emulator.actuator.Led;
import ESP32.Emulator.command.TurnOnLedCommand;
import ESP32.Emulator.sensor.TemperatureSensor;

//Temporal test
public class EmulatorApplication {
    public static void main(String[] args) {

        Esp32Emulator emulator = new EmulatorBootstrap().create();

        emulator.getEventBus()
                .subscribe(event -> {
                    System.out.println("EVENT: " + event);
                });

        //System.out.println(emulator.getEsp32().getName());

        TemperatureSensor temperatureSensor =(TemperatureSensor) emulator.getEsp32().getDevice("temp-001");
        temperatureSensor.setTemperature(25.5);

        emulator.getCommandHandler().execute(new TurnOnLedCommand("led-001"));

        Led led = (Led) emulator.getEsp32().getDevice("led-001");
        //led.turnOn();
        //emulator.getCommandHandler().execute(new TurnOnLedCommand("led-001"));

        System.out.println("LED: " + led.isOn());
    }
}
