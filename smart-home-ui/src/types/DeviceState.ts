export interface DeviceState {
  deviceId: string;
  type: string;
  state: Record<string, unknown>;
}