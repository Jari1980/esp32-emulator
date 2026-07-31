#include <Arduino.h>
#include "firmware/Firmware.h"


Firmware firmware;


void setup()
{
    Serial.begin(115200);

    delay(1000);

    Serial.println();
    Serial.println("====================================");
    Serial.println("ESP32 Smart Home Firmware");
    Serial.println("Version : 0.1.0");
    Serial.println("Device  : esp32-001");
    Serial.println("Status  : Boot successful");
    Serial.println("====================================");


    firmware.initialize();
}


void loop()
{
    firmware.update();
}