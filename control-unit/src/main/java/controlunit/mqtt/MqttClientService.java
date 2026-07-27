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

            System.out.println("Connected to MQTT broker");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
