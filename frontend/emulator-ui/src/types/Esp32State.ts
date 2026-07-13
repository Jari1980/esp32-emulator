export type DeviceState = {
  deviceId: string;
  type: string;
  state: Record<string, unknown>;
};

export type Esp32State = {
  id: string;
  name: string;
  uptime: number;
  devices: DeviceState[];
};
