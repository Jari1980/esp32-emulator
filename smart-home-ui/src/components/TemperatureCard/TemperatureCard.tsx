import type { DeviceState } from "../../types/DeviceState";
import { Card } from "../Card/Card";

interface Props {
  device: DeviceState;
}

export function TemperatureCard({ device }: Props) {
  const temperature = device.state.temperature as number;

  return (
    <Card title="Temperature">
      <div className="status-value">{temperature.toFixed(1)}°C</div>

      <div className="card__metadata">Device ID: {device.deviceId}</div>
    </Card>
  );
}
