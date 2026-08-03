#include "network/MqttManager.h"

#include <Arduino.h>
#include <WiFi.h>
#include <PubSubClient.h>

#include "config/MqttSecrets.h"
#include "device/command/Command.h"


CommandHandler* MqttManager::commandHandler = nullptr;


void mqttCallback(char* topic, uint8_t* payload, unsigned int length)
{
    Serial.println("******** MQTT CALLBACK FIRED ********");

    Serial.print("MQTT message: ");
    Serial.println(topic);


    String message;

    for(unsigned int i = 0; i < length; i++)
    {
        message += (char)payload[i];
    }


    Serial.println(message);


    Command command;


    if(message.indexOf("TURN_ON") >= 0)
    {
        command.action = "TURN_ON";
        command.deviceId = "led-001";
    }
    else if(message.indexOf("TURN_OFF") >= 0)
    {
        command.action = "TURN_OFF";
        command.deviceId = "led-001";
    }
    else
    {
        Serial.println("Unknown command");
        return;
    }



    CommandHandler* handler = MqttManager::getCommandHandler();


    if(handler == nullptr)
    {
        Serial.println("No command handler");
        return;
    }


    handler->handle(command);
}



WiFiClient wifiClient;
PubSubClient mqttClient(wifiClient);



void MqttManager::connect()
{
    Serial.println();
    Serial.println("------------------------------------");
    Serial.println("Connecting MQTT...");
    Serial.println("------------------------------------");

    mqttClient.setBufferSize(1024);

    Serial.print("MQTT server: ");
    Serial.println(MQTT_SERVER);

    Serial.print("MQTT port: ");
    Serial.println(MQTT_PORT);


    mqttClient.setServer(
        MQTT_SERVER,
        MQTT_PORT
    );


    mqttClient.setCallback(
        [](char* topic, uint8_t* payload, unsigned int length)
        {
            mqttCallback(topic, payload, length);
        }
    );


    int attempts = 0;


    while(!mqttClient.connected() && attempts < 10)
    {
        Serial.print(".");


        if(mqttClient.connect("esp32-001"))
        {
            Serial.println();
            Serial.println("MQTT connected");


            mqttClient.subscribe(
                "home/esp32/esp32-001/command"
            );


            Serial.println("Subscribed: home/esp32/esp32-001/command");

            return;
        }
        else
        {
            Serial.print("MQTT failed, state=");
            Serial.println(mqttClient.state());
        }


        attempts++;

        delay(1000);
    }


    Serial.println();

    if(!mqttClient.connected())
    {
        Serial.println("MQTT connection failed");
    }
}


void MqttManager::loop()
{
    mqttClient.loop();
}


void MqttManager::publishState(String payload)
{
    Serial.print("Payload size: ");
    Serial.println(payload.length());

    bool result = mqttClient.publish(
        "home/esp32/esp32-001/state",
        payload.c_str()
    );

    Serial.println(result ? "SUCCESS" : "FAILED");
    
    
}


void MqttManager::setCommandHandler(CommandHandler* handler)
{
    commandHandler = handler;
}

CommandHandler* MqttManager::getCommandHandler()
{
    return commandHandler;
}