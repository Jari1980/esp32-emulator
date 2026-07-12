package ESP32.Emulator.mapper;

import ESP32.Emulator.dto.DeviceStateDto;
import ESP32.Emulator.dto.Esp32StateDto;
import ESP32.Emulator.state.Esp32State;

import java.util.List;

public class StateMapper {
    public Esp32StateDto map(Esp32State state) {
        List<DeviceStateDto> devices =
                state.devices()
                        .stream()
                        .map(device ->
                                new DeviceStateDto(
                                        device.deviceId(),
                                        device.deviceType(),
                                        device.state()
                                )
                        )
                        .toList();

        return new Esp32StateDto(
                state.id(),
                state.name(),
                state.uptime(),
                devices
        );
    }
}
