#pragma once

class StateProvider;


class Device
{
public:

    virtual ~Device() = default;


    virtual const char* getId() = 0;

    virtual const char* getType() = 0;


    virtual void initialize() = 0;

    virtual void update() = 0;


    virtual StateProvider* getStateProvider()
    {
        return nullptr;
    }
};