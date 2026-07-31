#pragma once

#include <ArduinoJson.h>


class StateProvider
{

public:

    virtual ~StateProvider() = default;


    virtual void printState()
    {
    }


    virtual void writeState(
        JsonObject state
    )
    {
    }

};