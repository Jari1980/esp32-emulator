export type EmulatorEvent = {
  id: number;
  timestamp: string;

  deviceId: string;
  type: string;

  value?: string | number | boolean;

  message: string;
};