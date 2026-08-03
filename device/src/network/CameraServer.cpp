#include <WiFi.h>
#include "network/CameraServer.h"

#include <Arduino.h>
#include <WebServer.h>

#include "device/CameraDevice.h"
#include "esp_camera.h"
#include "img_converters.h"


WebServer server(80);


void handleCapture()
{
    Serial.println("HTTP capture requested");

    camera_fb_t* fb = esp_camera_fb_get();

    if(!fb)
    {
        server.send(
            500,
            "text/plain",
            "Camera capture failed"
        );
        return;
    }


    uint8_t* jpg_buf = nullptr;
    size_t jpg_len = 0;


    bool converted = frame2jpg(
        fb,
        80,
        &jpg_buf,
        &jpg_len
    );


    esp_camera_fb_return(fb);


    if(!converted)
    {
        Serial.println("JPEG conversion failed");

        server.send(
            500,
            "text/plain",
            "JPEG conversion failed"
        );

        return;
    }


    Serial.print("JPEG size: ");
    Serial.println(jpg_len);


    server.send_P(
        200,
        "image/jpeg",
        (const char*)jpg_buf,
        jpg_len
    );


    free(jpg_buf);

    Serial.println("Image sent");
}



void CameraServer::start()
{
    server.on(
        "/capture",
        HTTP_GET,
        handleCapture
    );

    server.on(
    "/",
    HTTP_GET,
    []()
    {
        server.send(
            200,
            "text/html",
            "<html><body><h1>ESP32 Camera</h1><img src='/capture'></body></html>"
        );
    }
);

    server.begin();

    Serial.println("Camera HTTP server started");
}

void CameraServer::loop()
{
    server.handleClient();
}