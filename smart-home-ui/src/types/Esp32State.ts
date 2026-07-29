import type { DeviceState } from "./DeviceState";

export interface Esp32State {
  id: string;
  name: string;
  uptime: number;
  devices: DeviceState[];
}