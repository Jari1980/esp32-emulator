package ESP32.Emulator.mqtt;

import ESP32.Emulator.command.Command;
import ESP32.Emulator.command.CommandHandler;
import ESP32.Emulator.command.CommandMapper;
import ESP32.Emulator.message.CommandMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttCommandListener {
    private final MqttClient client;
    private final ObjectMapper mapper;
    private final CommandMapper commandMapper;
    private final CommandHandler commandHandler;
    private final String topic;


    public MqttCommandListener(
            String broker,
            String clientId,
            String deviceId,
            CommandMapper commandMapper,
            CommandHandler commandHandler
    ) {

        try {
            this.client = new MqttClient(
                    broker,
                    clientId
            );

            this.mapper = new ObjectMapper();
            this.commandMapper = commandMapper;
            this.commandHandler = commandHandler;
            this.topic = "home/esp32/" + deviceId + "/command";

            client.connect();
            client.subscribe(topic, this::handleMessage);

        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleMessage(
            String topic,
            MqttMessage message
    ) {

        try {
            String payload = new String(message.getPayload());

            System.out.println("MQTT COMMAND RECEIVED:");
            System.out.println(payload);
            CommandMessage commandMessage =
                    mapper.readValue(
                            message.getPayload(),
                            CommandMessage.class
                    );

            Command command = commandMapper.map(commandMessage);
            commandHandler.execute(command);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
