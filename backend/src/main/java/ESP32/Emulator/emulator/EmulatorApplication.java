package ESP32.Emulator.emulator;

import ESP32.Emulator.actuator.Led;
import ESP32.Emulator.command.TurnOnLedCommand;
import ESP32.Emulator.publisher.ConsoleStatePublisher;
import ESP32.Emulator.sensor.TemperatureSensor;
import ESP32.Emulator.websocket.EmulatorWebSocketServer;

//Temporal test
public class EmulatorApplication {
    public static void main(String[] args) {
        EmulatorWebSocketServer websocket = new EmulatorWebSocketServer(8080);
        websocket.start();

        Esp32Emulator emulator = new EmulatorBootstrap().create();
        EmulatorService service = new DefaultEmulatorService(emulator, new ConsoleStatePublisher());

        emulator.getEventBus()
                .subscribe(event -> {
                    System.out.println("EVENT: " + event);
                });

        //System.out.println(emulator.getEsp32().getName());

        TemperatureSensor temperatureSensor =(TemperatureSensor) emulator.getEsp32().getDevice("temp-001");
        temperatureSensor.setTemperature(25.5);

        //emulator.getCommandHandler().execute(new TurnOnLedCommand("led-001"));

        Led led = (Led) emulator.getEsp32().getDevice("led-001");
        //led.turnOn();
        //emulator.getCommandHandler().execute(new TurnOnLedCommand("led-001"));

        service.execute(new TurnOnLedCommand("led-001"));
        service.publishState();
        System.out.println(service.getCurrentState());
        System.out.println("LED: " + led.isOn());
    }
}