#include "device/LedDevice.h"

#include <Arduino.h>


LedDevice::LedDevice(int pin)
    : pin(pin)
{
}


void LedDevice::initialize()
{
    pinMode(pin, OUTPUT);

    turnOff();
}


void LedDevice::update()
{
    // Nothing to update yet
}


void LedDevice::turnOn()
{
    digitalWrite(pin, HIGH);

    state = true;
}


void LedDevice::turnOff()
{
    digitalWrite(pin, LOW);

    state = false;
}


bool LedDevice::isOn() const
{
    return state;
}


const char* LedDevice::getId()
{
    return "led-001";
}


const char* LedDevice::getType()
{
    return "led";
}

void LedDevice::printState()
{
    Serial.print("{\"deviceId\":\"");
    Serial.print(getId());

    Serial.print("\",\"state\":{\"ledOn\":");

    if(state)
    {
        Serial.print("true");
    }
    else
    {
        Serial.print("false");
    }

    Serial.println("}}");
}

StateProvider* LedDevice::getStateProvider()
{
    return this;
}

void LedDevice::writeState(JsonObject state)
{
    state["ledOn"] = this->state;
}