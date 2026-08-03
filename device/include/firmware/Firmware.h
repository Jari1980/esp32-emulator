#pragma once
#include "device/LedDevice.h"
#include "device/ThermistorDevice.h"
#include "device/DeviceRegistry.h"
#include "device/command/CommandHandler.h"
#include "network/WifiManager.h"
#include "network/MqttManager.h"
#include "device/CameraDevice.h"
#include "network/CameraServer.h"

class Firmware
{
public:
    void initialize();
    void update();
private:
    DeviceRegistry registry;
    LedDevice led{2};
    ThermistorDevice thermistor{4};
    CameraDevice camera;
    CommandHandler* commandHandler;
    unsigned long uptimeSeconds = 0;
    unsigned long lastUptimeUpdate = 0;
    unsigned long lastStatePublish = 0;
    WifiManager wifi;
    MqttManager mqtt;
    CameraServer cameraServer;
};