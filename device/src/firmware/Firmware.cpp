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
    mqtt.setCommandHandler(commandHandler);
    mqtt.connect();

    registry.add(&led);
    registry.add(&thermistor);
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

    registry.update();

    // Testing thermistor
static unsigned long lastRead = 0;

if (millis() - lastRead >= 1000)
{
    lastRead = millis();

    // Average ADC readings
    long total = 0;

    for(int i = 0; i < 20; i++)
    {
        total += analogRead(4);
        delay(5);
    }

    int adc = total / 20;


    // Calculate thermistor resistance
    float resistance = 10000.0 * adc / (4095.0 - adc);


    // Thermistor constants
    float B = 3950.0;
    float R0 = 10000.0;
    float T0 = 25.0 + 273.15;


    // Calculate temperature
    float temperatureK =
        1.0 /
        (
            (1.0 / T0) +
            (log(resistance / R0) / B)
        );


    float temperatureC = temperatureK - 273.15;


    // Calibration adjustment
    temperatureC += 8.0;


    Serial.print("ADC: ");
    Serial.print(adc);

    Serial.print("  Resistance: ");
    Serial.print(resistance);

    Serial.print(" ohm  Temperature: ");
    Serial.print(temperatureC);

    Serial.println(" C");
}


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