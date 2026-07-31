#pragma once

#include "device/command/Command.h"
#include "device/DeviceRegistry.h"


class CommandHandler
{

public:

    CommandHandler(DeviceRegistry* registry);

    void handle(Command command);


private:

    DeviceRegistry* registry;

};