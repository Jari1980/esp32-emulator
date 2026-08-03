#include "firmware/Firmware.h"
#include <Arduino.h>
#include "device/command/CommandHandler.h"
#include "state/Esp32State.h"
#include <math.h>


void Firmware::initialize()
{
    Serial.println();
    Serial.println("------------------------------------");
    Serial.println("Firmware initialization");
    Serial.println("Device: esp32-001");
    Serial.println("------------------------------------");

    commandHandler = new CommandHandler(&registry);

    wifi.connect();
    cameraServer.start();
    mqtt.setCommandHandler(commandHandler);
    mqtt.connect();

    registry.add(&led);
    registry.add(&thermistor);
    registry.add(&camera);
    registry.initialize();

    Serial.println("LED initialized on GPIO 2");

    String json = Esp32State::createJson(
        registry,
        uptimeSeconds
    );

    mqtt.publishState(json);
}


void Firmware::update()
{
    mqtt.loop();

    cameraServer.loop();

    registry.update();

    bool stateChanged = false;


    if(led.hasStateChanged() || thermistor.hasChanged())
    {
        stateChanged = true;
    }


    if(millis() - lastStatePublish >= 10000)
    {
        stateChanged = true;
    }


    if(stateChanged)
    {
        lastStatePublish = millis();

        String json = Esp32State::createJson(
            registry,
            uptimeSeconds
        );

        Serial.println("Publishing state:");
        Serial.println(json);

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