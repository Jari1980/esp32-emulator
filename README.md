# ESP32 Smart Home Platform

A small IoT platform for developing and testing communication between devices, applications, and services.

This project is built as a learning project to explore IoT architecture using **MQTT**, **WebSocket**, and ESP32 hardware.

The goal is to understand how device communication, real-time updates, and application control can be designed in a flexible IoT-style system.

The ESP32 layer can run either on real hardware or through an emulator. Both use the same communication model, allowing simulated devices to be replaced with physical devices.

## Architecture

```
+-------------------+        +--------------+        +----------------+
| ESP32 Emulator    | <----> | MQTT Broker  | <----> | Control Unit   |
|                   |        |              |        |                |
| Hardware Layer    |        |              |        | Backend        |
| Device Emulator   |        |              |        | Frontend UI    |
|                   |        |              |        |                |
+-------------------+        +--------------+        +----------------+
```

## ESP32 Layer

The ESP32 layer represents the hardware side of the system.

It supports:

- Real ESP32 hardware
- Device emulator for development and testing
- Device state management
- Hardware event simulation

Supported devices:

- LED GPIO output
- Temperature sensor
- Motion sensor simulation
- Camera module

The emulator and hardware layer are used to:

- View device state
- Control devices
- Test MQTT communication
- Develop features without requiring all physical hardware

## Communication

The project uses two communication methods:

### MQTT

Used as the communication layer between devices and applications.

Examples:

- Device state publishing
- Sending commands
- Connecting simulated hardware with real hardware

### WebSocket

Used for real-time communication between backend services and frontend applications.

Examples:

- Live state updates
- Immediate UI changes
- Device monitoring

## Current Devices

### Temperature Sensor

- Generates temperature values
- Supports temperature adjustment commands

Commands:

```
INCREASE_TEMPERATURE
DECREASE_TEMPERATURE
```

### LED

- Simulated GPIO output
- Supports on/off control

Commands:

```
TURN_ON
TURN_OFF
```

### Motion Sensor

- Simulated motion detection
- Supports trigger/reset

Commands:

```
TRIGGER_MOTION
RESET_MOTION
```

### Camera

- ESP32 camera module support
- JPEG snapshot capture
- HTTP image endpoint
- MQTT status reporting

## MQTT Topics

MQTT acts as the communication contract between systems.

### State Topic

```
home/esp32/{deviceId}/state
```

### Command Topic

```
home/esp32/{deviceId}/command
```

Example command:

```json
{
  "command": "TURN_ON",
  "deviceId": "led-001"
}
```


