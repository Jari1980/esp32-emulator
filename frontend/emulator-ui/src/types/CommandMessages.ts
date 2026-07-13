export type CommandMessage = {
  type: "COMMAND";

  payload: {
    command: string;
    deviceId: string;
  };
};
