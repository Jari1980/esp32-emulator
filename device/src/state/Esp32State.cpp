#include "state/Esp32State.h"
#include "device/StateProvider.h"


String Esp32State::createJson(
    DeviceRegistry& registry,
    unsigned long uptime
)
{
    JsonDocument doc;


    doc["id"] = "esp32-001";
    doc["uptime"] = uptime;


    JsonArray devices = doc["devices"].to<JsonArray>();


    for(int i = 0; i < registry.getCount(); i++)
    {
        Device* device = registry.getDevice(i);


        if(device == nullptr)
            continue;


        JsonObject item = devices.add<JsonObject>();


        item["deviceId"] = device->getId();
        item["type"] = device->getType();


        StateProvider* provider = device->getStateProvider();


        if(provider)
        {
            JsonObject state =
                item["state"].to<JsonObject>();

            provider->writeState(state);
        }
    }


    String output;

    serializeJson(doc, output);


    return output;
}