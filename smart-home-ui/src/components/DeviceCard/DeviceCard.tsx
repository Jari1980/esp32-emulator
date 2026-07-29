import type { DeviceState } from "../../types/DeviceState";

interface Props {
  device: DeviceState;
}

export function DeviceCard({ device }: Props) {
  return (
    <div>
      <h3>{device.type}</h3>

      <p>Device ID: {device.deviceId}</p>

      <pre>{JSON.stringify(device.state, null, 2)}</pre>
    </div>
  );
}
