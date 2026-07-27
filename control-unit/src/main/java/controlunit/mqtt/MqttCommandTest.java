package controlunit.mqtt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


//This is for testing that I can send command, working as intended
@Component
public class MqttCommandTest implements CommandLineRunner {
    private final MqttClientService mqttClientService;

    public MqttCommandTest(MqttClientService mqttClientService) {
        this.mqttClientService = mqttClientService;
    }

    @Override
    public void run(String... args) {

        mqttClientService.sendCommand(
                "led-001",
                "TURN_ON"
        );
    }
}
