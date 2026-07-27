package controlunit.mqtt;

import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.stereotype.Component;

@Component
public class MqttClientService {
    private static final String BROKER = "tcp://localhost:1883";
    private MqttClient client;

    @PostConstruct
    public void connect() {

        try {
            client = new MqttClient(BROKER, MqttClient.generateClientId());

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);

            client.connect(options);

            client.subscribe("home/esp32/+/state", (topic, message) -> {

                String payload = new String(message.getPayload());

                System.out.println("MQTT MESSAGE");
                System.out.println("Topic: " + topic);
                System.out.println("Payload: " + payload);

            });

            System.out.println("Connected to MQTT broker");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendCommand(String deviceId, String command) {

        try {
            String payload = """
                {
                  "command": "%s",
                  "deviceId": "%s"
                }
                """.formatted(command, deviceId);

            client.publish(
                    "home/esp32/esp32-001/command",
                    payload.getBytes(),
                    0,
                    false
            );

            System.out.println("Command sent:");
            System.out.println(payload);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
