#pragma once
#include "device/LedDevice.h"
#include "device/DeviceRegistry.h"
#include "device/command/CommandHandler.h"

class Firmware
{
public:
    void initialize();
    void update();
private:
    DeviceRegistry registry;
    LedDevice led{2};
    CommandHandler* commandHandler;
    unsigned long uptimeSeconds = 0;
    unsigned long lastUptimeUpdate = 0;
    unsigned long lastStatePublish = 0;
};