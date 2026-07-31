#include <Arduino.h>
#include "device/DeviceRegistry.h"
#include "device/StateProvider.h"


void DeviceRegistry::add(Device* device)
{
    devices[count++] = device;
}


void DeviceRegistry::initialize()
{
    for(int i = 0; i < count; i++)
    {
        Serial.print("Initializing device: ");
        Serial.print(devices[i]->getId());
        Serial.print(" type=");
        Serial.println(devices[i]->getType());

        devices[i]->initialize();

        StateProvider* stateDevice = devices[i]->getStateProvider();

        if(stateDevice)
        {
            stateDevice->printState();
        }
    }
}


void DeviceRegistry::update()
{
    for(int i = 0; i < count; i++)
    {
        devices[i]->update();
    }
}

Device* DeviceRegistry::find(const char* id)
{
    for(int i = 0; i < count; i++)
    {
        if(strcmp(devices[i]->getId(), id) == 0)
        {
            return devices[i];
        }
    }

    return nullptr;
}

int DeviceRegistry::getCount()
{
    return count;
}


Device* DeviceRegistry::getDevice(int index)
{
    if(index < 0 || index >= count)
    {
        return nullptr;
    }

    return devices[index];
}