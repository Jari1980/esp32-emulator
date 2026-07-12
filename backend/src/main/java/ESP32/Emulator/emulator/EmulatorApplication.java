package ESP32.Emulator.emulator;

import ESP32.Emulator.actuator.Led;
import ESP32.Emulator.command.TurnOnLedCommand;
import ESP32.Emulator.mapper.StateMapper;
import ESP32.Emulator.publisher.ConsoleStatePublisher;
import ESP32.Emulator.publisher.WebSocketStatePublisher;
import ESP32.Emulator.sensor.TemperatureSensor;
import ESP32.Emulator.websocket.EmulatorWebSocketServer;
import com.fasterxml.jackson.databind.ObjectMapper;

//Temporal test
public class EmulatorApplication {
    public static void main(String[] args) {
        Esp32Emulator emulator = new EmulatorBootstrap().create();
        ObjectMapper mapper = new ObjectMapper();

        EmulatorWebSocketServer websocket = new EmulatorWebSocketServer(
                8080,
                () -> {
                    try {
                        return mapper.writeValueAsString(
                                new StateMapper()
                                        .map(
                                                emulator.getCurrentState()
                                        ));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                );
        websocket.start();

        EmulatorService service = new DefaultEmulatorService(
                emulator,
                new WebSocketStatePublisher(websocket)
        );

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