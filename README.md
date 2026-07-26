# ESP32 Smart Home Simulator

A simulated smart home environment for developing and testing IoT communication.

This project is mainly built as a learning project to explore communication between systems using **MQTT** and **WebSocket** technologies.

The goal is to understand how device communication, real-time updates, and application control can be designed in an IoT-style architecture.

The project simulates an ESP32 device and communicates through MQTT. In theory, the same MQTT interface could be used with real ESP32 hardware.

## Architecture

```
+-------------------+        +--------------+        +----------------+
| ESP32 Emulator    | <----> | MQTT Broker  | <----> | Control Unit   |
|                   |        |              |        |                |
| Backend           |        |              |        | Backend        |
| Frontend Console  |        |              |        | Frontend UI    |
|                   |        |              |        | (Todo)         |
+-------------------+        +--------------+        +----------------+
```

## ESP32 Emulator

The emulator represents the hardware layer.

It simulates:

- ESP32 device behaviour
- Sensors
- GPIO devices
- Device state
- Hardware events

The Emulator Console is used for development and testing:

- View device state
- Control simulated devices
- Test MQTT communication

## Communication

The project uses two communication methods:

### MQTT

Used as the communication layer between devices and applications.

Examples:

- Device state publishing
- Sending commands
- Connecting simulated hardware with future real hardware

### WebSocket

Used for real-time communication between backend services and web frontends.

Examples:

- Live state updates
- Immediate UI changes
- Monitoring device events

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

## MQTT Topics

MQTT is the communication contract between systems.

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

## Future Development

Next step is building the Control Unit.

The Control Unit will provide:

- User interface
- Automation logic
- Device control
- User management

In theory the ESP32 Emulator can later be replaced by real ESP32 hardware without changing the Control Unit communication model.

## Goal

The goal is to separate the hardware layer from the application layer.

The MQTT interface allows simulated hardware, real hardware, and applications to communicate using the same protocol.
