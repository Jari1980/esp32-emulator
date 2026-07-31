#pragma once

#include <Arduino.h>
#include <ArduinoJson.h>

#include "device/DeviceRegistry.h"


class Esp32State
{

public:

    static String createJson(
        DeviceRegistry& registry,
        unsigned long uptime
    );

};