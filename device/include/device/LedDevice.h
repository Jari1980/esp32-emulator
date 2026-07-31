#pragma once

#include "device/Device.h"
#include "device/StateProvider.h"


class LedDevice : public Device, public StateProvider
{
public:

    explicit LedDevice(int pin);


    const char* getId() override;

    const char* getType() override;

    void initialize() override;

    void update() override;

    void printState() override;


    void turnOn();

    void turnOff();


    bool isOn() const;

    bool hasStateChanged();

    StateProvider* getStateProvider() override;

    void writeState(JsonObject state) override;


private:

    int pin;

    bool state = false;

    bool stateChanged = false;
};