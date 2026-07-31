#include <Arduino.h>

void setup() {
    Serial.begin(115200);

    delay(1000);

    Serial.println();
    Serial.println("====================================");
    Serial.println("ESP32 Smart Home Firmware");
    Serial.println("Version : 0.1.0");
    Serial.println("Device  : esp32-001");
    Serial.println("Status  : Boot successful");
    Serial.println("====================================");
}

void loop() {
    Serial.println("Heartbeat");
    delay(1000);
}