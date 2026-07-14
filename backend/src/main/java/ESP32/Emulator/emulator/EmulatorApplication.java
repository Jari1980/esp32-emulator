package ESP32.Emulator.emulator;

import ESP32.Emulator.command.CommandHandler;
import ESP32.Emulator.command.CommandMapper;
import ESP32.Emulator.command.TurnOnLedCommand;
import ESP32.Emulator.listener.StateChangeListener;
import ESP32.Emulator.mapper.StateMapper;
import ESP32.Emulator.message.StateMessage;
import ESP32.Emulator.mqtt.MqttCommandListener;
import ESP32.Emulator.mqtt.MqttConfiguration;
import ESP32.Emulator.mqtt.MqttConfigurationLoader;
import ESP32.Emulator.mqtt.MqttStatePublisher;
import ESP32.Emulator.publisher.WebSocketStatePublisher;
import ESP32.Emulator.sensor.TemperatureSensor;
import ESP32.Emulator.websocket.EmulatorWebSocketServer;
import com.fasterxml.jackson.databind.ObjectMapper;

//Temporal test
public class EmulatorApplication {
    public static void main(String[] args) {
        Esp32Emulator emulator = new EmulatorBootstrap().create();
        ObjectMapper mapper = new ObjectMapper();
        CommandMapper commandMapper = new CommandMapper();
        MqttConfiguration mqttConfiguration = new MqttConfigurationLoader().load();

        MqttCommandListener commandListener =
                new MqttCommandListener(
                        mqttConfiguration.broker(),
                        mqttConfiguration.clientId() + "-listener",
                        emulator.getEsp32().getId(),
                        commandMapper,
                        new CommandHandler(
                                emulator.getEsp32().getDeviceRegistry()
                        )
                );

        MqttStatePublisher mqttPublisher = new MqttStatePublisher(
                mqttConfiguration.broker(),
                mqttConfiguration.clientId(),
                emulator.getEsp32().getId()
        );

        EmulatorWebSocketServer websocket =
                new EmulatorWebSocketServer(
                        8080,
                        () -> {
                            try {
                                return mapper.writeValueAsString(
                                        new StateMessage(
                                                new StateMapper()
                                                        .map(emulator.getCurrentState())
                                        )
                                );
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        },
                        commandMapper,
                        emulator,
                        mapper
                );

        websocket.start();

        WebSocketStatePublisher publisher = new WebSocketStatePublisher(websocket);

        EmulatorService service = new DefaultEmulatorService(emulator, publisher);

        emulator.getEventBus()
                .subscribe(
                        new StateChangeListener(
                                emulator,
                                publisher
                        )
                );

        //Testing mqtt
        emulator.getEventBus()
                .subscribe(
                        new StateChangeListener(
                                emulator,
                                mqttPublisher
                        )
                );

    }
}