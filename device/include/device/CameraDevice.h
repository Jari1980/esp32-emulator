#pragma once

#include "device/Device.h"
#include "device/StateProvider.h"


class CameraDevice : public Device, public StateProvider
{
public:

    CameraDevice();

    const char* getId() override;
    const char* getType() override;

    void initialize() override;
    void update() override;

    void printState() override;

    StateProvider* getStateProvider() override;

    void writeState(JsonObject state) override;


private:

    bool online = false;

    unsigned long frameCount = 0;

    size_t lastFrameSize = 0;

    void initCamera();
};