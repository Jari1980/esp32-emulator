import type { Esp32State } from "../types/Esp32State";

export function getDevice(state: Esp32State, deviceId: string) {
  return state.devices.find((d) => d.deviceId === deviceId);
}
