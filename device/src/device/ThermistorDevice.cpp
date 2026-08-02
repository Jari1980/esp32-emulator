#include "device/ThermistorDevice.h"

#include <Arduino.h>
#include <math.h>


ThermistorDevice::ThermistorDevice(int pin)
    : pin(pin)
{
}


void ThermistorDevice::initialize()
{
    pinMode(pin, INPUT);

    Serial.println("Thermistor initialized on GPIO 4");
}


void ThermistorDevice::update()
{
    static unsigned long lastRead = 0;


    if(millis() - lastRead < 1000)
    {
        return;
    }


    lastRead = millis();


    long total = 0;


    for(int i = 0; i < 20; i++)
    {
        total += analogRead(pin);
        delay(5);
    }


    int adc = total / 20;


    float resistance = 10000.0 * adc / (4095.0 - adc);


    float B = 3950.0;

    float R0 = 10000.0;

    float T0 = 25.0 + 273.15;


    float temperatureK = 1.0 / ((1.0 / T0) + (log(resistance / R0) / B));

    temperature = temperatureK - 273.15;


    // Calibration from physical test
    temperature += 8.0;


    Serial.print("Thermistor: ");

    Serial.print(temperature);

    Serial.println(" C");
}



const char* ThermistorDevice::getId()
{
    return "thermistor-001";
}



const char* ThermistorDevice::getType()
{
    return "thermistor";
}



void ThermistorDevice::printState()
{
    Serial.print("{\"deviceId\":\"");
    Serial.print(getId());

    Serial.print("\",\"state\":{\"temperature\":");

    Serial.print(temperature);

    Serial.println("}}");
}



StateProvider* ThermistorDevice::getStateProvider()
{
    return this;
}



void ThermistorDevice::writeState(JsonObject state)
{
    state["temperature"] = temperature;
}