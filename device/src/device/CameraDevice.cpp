#include "device/CameraDevice.h"
#include "esp_camera.h"
#include "camera_pins.h"

#include <Arduino.h>


static unsigned long lastCapture = 0;

CameraDevice::CameraDevice()
{
}


void CameraDevice::initialize()
{
    initCamera();

    Serial.println("Camera initialized");
}

void CameraDevice::update()
{
    if(millis() - lastCapture < 1000)
    {
        return;
    }
    lastCapture = millis();

    if(!online)
        return;


    camera_fb_t* fb = esp_camera_fb_get();


    if(!fb)
    {
        Serial.println("Camera capture failed");
        return;
    }


    frameCount++;
    lastFrameSize = fb->len;


    Serial.print("Camera frame: ");
    Serial.print(frameCount);

    Serial.print(" size: ");
    Serial.println(lastFrameSize);


    esp_camera_fb_return(fb);
}


void CameraDevice::initCamera()
{
    Serial.println("=== CAMERA INIT START ===");
    camera_config_t config = {};


    config.ledc_channel = LEDC_CHANNEL_0;
    config.ledc_timer = LEDC_TIMER_0;


    config.pin_d0 = Y2_GPIO_NUM;
    config.pin_d1 = Y3_GPIO_NUM;
    config.pin_d2 = Y4_GPIO_NUM;
    config.pin_d3 = Y5_GPIO_NUM;
    config.pin_d4 = Y6_GPIO_NUM;
    config.pin_d5 = Y7_GPIO_NUM;
    config.pin_d6 = Y8_GPIO_NUM;
    config.pin_d7 = Y9_GPIO_NUM;

    config.pin_xclk = XCLK_GPIO_NUM;
    config.pin_pclk = PCLK_GPIO_NUM;
    config.pin_vsync = VSYNC_GPIO_NUM;
    config.pin_href = HREF_GPIO_NUM;

    config.pin_sccb_sda = SIOD_GPIO_NUM;
    config.pin_sccb_scl = SIOC_GPIO_NUM;

    config.pin_pwdn = PWDN_GPIO_NUM;
    config.pin_reset = RESET_GPIO_NUM;


    config.xclk_freq_hz = 10000000;


    config.frame_size = FRAMESIZE_QVGA;

    config.pixel_format = PIXFORMAT_RGB565; //PIXFORMAT_JPEG;


    config.grab_mode = CAMERA_GRAB_WHEN_EMPTY;

    config.fb_location = CAMERA_FB_IN_PSRAM;

    config.jpeg_quality = 10;

    config.fb_count = 1;


    esp_err_t err = esp_camera_init(&config);
    if (err == ESP_OK)
    {
        sensor_t *s = esp_camera_sensor_get();

        Serial.print("Camera PID: 0x");
        Serial.println(s->id.PID, HEX);
    }


    if(err == ESP_OK)
    {
        online = true;
        Serial.println("Camera OK");
    }
    else
    {
        online = false;
        Serial.print("Camera failed: 0x");
        Serial.println(err, HEX);
    }
}

const char* CameraDevice::getId()
{
    return "camera-001";
}


const char* CameraDevice::getType()
{
    return "camera";
}


StateProvider* CameraDevice::getStateProvider()
{
    return this;
}


void CameraDevice::writeState(JsonObject state)
{
    state["online"] = online;
    state["frame"] = frameCount;
    state["size"] = lastFrameSize;
    state["url"] = "http://192.168.0.236/capture";
}


void CameraDevice::printState()
{
    Serial.print("{\"deviceId\":\"");
    Serial.print(getId());

    Serial.print("\",\"state\":{\"online\":");

    Serial.print(online ? "true" : "false");

    Serial.print(",\"frame\":");
    Serial.print(frameCount);

    Serial.println("}}");
}