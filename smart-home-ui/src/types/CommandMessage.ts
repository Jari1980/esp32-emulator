export interface CommandMessage {
  type: "COMMAND";
  payload: {
    command: string;
    deviceId: string;
  };
}
