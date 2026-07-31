#include "firmware/Firmware.h"
#include <Arduino.h>
#include "device/command/CommandHandler.h"
#include "state/Esp32State.h"


void Firmware::initialize()
{
    Serial.println();
    Serial.println("------------------------------------");
    Serial.println("Firmware initialization");
    Serial.println("Device: esp32-001");
    Serial.println("------------------------------------");

    commandHandler = new CommandHandler(&registry);

    wifi.connect();
    mqtt.setCommandHandler(commandHandler);
    mqtt.connect();

    registry.add(&led);
    registry.initialize();

    Serial.println("LED initialized on GPIO 2");
}


void Firmware::update()
{
    mqtt.loop();

    registry.update();

    if(led.hasStateChanged())
    {
        String json = Esp32State::createJson(
        registry,
        uptimeSeconds
    );

        mqtt.publishState(json);
    }


    if(Serial.available())
    {
        String input = Serial.readStringUntil('\n');
        input.trim();

        Serial.print("Input received: ");
        Serial.println(input);

        Command command;


        if(input == "on")
        {
            command.action = "TURN_ON";
            command.deviceId = "led-001";

            commandHandler->handle(command);
        }


        if(input == "off")
        {
            command.action = "TURN_OFF";
            command.deviceId = "led-001";

            commandHandler->handle(command);
        }
    }


    if(millis() - lastUptimeUpdate >= 1000)
    {
        lastUptimeUpdate = millis();

        uptimeSeconds++;

        Serial.print("Firmware uptime: ");
        Serial.print(uptimeSeconds);
        Serial.println(" seconds");
    }
}