#pragma once

#include "device/Device.h"


class DeviceRegistry
{
public:

    void add(Device* device);

    void initialize();

    void update();

    Device* find(const char* id);

    int getCount();

    Device* getDevice(int index);

private:

    Device* devices[10];

    int count = 0;
};

