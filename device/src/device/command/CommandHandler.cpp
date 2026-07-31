#include <Arduino.h>

#include "device/command/CommandHandler.h"
#include "device/LedDevice.h"


CommandHandler::CommandHandler(DeviceRegistry* registry)
{
    this->registry = registry;
}


void CommandHandler::handle(Command command)
{
    Serial.println("Command received");
    Serial.print("Device: ");
    Serial.println(command.deviceId);

    Device* device = registry->find(
        command.deviceId.c_str()
    );


    if(device == nullptr)
    {
        Serial.println("Device not found");
        return;
    }


    if(command.action == "TURN_ON")
    {
        LedDevice* led = (LedDevice*) device;

        led->turnOn();

        Serial.println("LED ON");

        led->printState();
    }


    if(command.action == "TURN_OFF")
    {
        LedDevice* led = (LedDevice*) device;

        led->turnOff();

        Serial.println("LED OFF");

        led->printState();
    }
}