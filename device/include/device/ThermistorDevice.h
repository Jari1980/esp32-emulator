#pragma once

#include "device/Device.h"
#include "device/StateProvider.h"


class ThermistorDevice : public Device, public StateProvider
{
public:

    explicit ThermistorDevice(int pin);


    const char* getId() override;

    const char* getType() override;


    void initialize() override;

    void update() override;


    void printState() override;


    StateProvider* getStateProvider() override;

    void writeState(JsonObject state) override;

    bool hasChanged();
    void clearChanged();


private:

    int pin;

    float temperature = 0.0;

    bool changed = false;

};