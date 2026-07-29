import { ControlUnitWebSocket } from "../websocket";

const websocket = new ControlUnitWebSocket();

websocket.connect();

export function useDeviceCommand() {
  function sendCommand(command: string, deviceId: string) {
    websocket.send({
      type: "COMMAND",
      payload: {
        command,
        deviceId,
      },
    });
  }

  return {
    sendCommand,
  };
}
