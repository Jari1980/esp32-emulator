package ESP32.Emulator.mqtt;

import ESP32.Emulator.dto.Esp32StateDto;
import ESP32.Emulator.publisher.StatePublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.charset.StandardCharsets;

public class MqttStatePublisher implements StatePublisher {
    private final MqttClient client;
    private final ObjectMapper mapper;
    private final String topic;

    public MqttStatePublisher(String broker, String clientId, String deviceId) {

        try {
            this.client = new MqttClient(broker, clientId);
            this.mapper = new ObjectMapper();
            this.topic = "home/esp32/" + deviceId + "/state";
            client.connect();

        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void publish(Esp32StateDto state) {

        try {
            String json = mapper.writeValueAsString(state);

            client.publish(topic, new MqttMessage(json.getBytes(StandardCharsets.UTF_8)));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
