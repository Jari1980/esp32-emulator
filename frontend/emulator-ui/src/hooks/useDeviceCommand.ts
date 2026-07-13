import websocketAdapter from "../websocket/WebSocketAdapter";

function useDeviceCommand() {
  const sendCommand = (command: string, deviceId: string) => {
    websocketAdapter.sendCommand(command, deviceId);
  };

  return {
    sendCommand,
  };
}

export default useDeviceCommand;
