#pragma once

#include <Arduino.h>
#include "device/command/CommandHandler.h"

class MqttManager
{
public:

    void connect();

    void loop();

    void publishState(String payload);

    void setCommandHandler(CommandHandler* handler);

    static CommandHandler* getCommandHandler();

private:

    static CommandHandler* commandHandler;

};