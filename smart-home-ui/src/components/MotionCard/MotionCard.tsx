import type { DeviceState } from "../../types/DeviceState";
import { Card } from "../Card/Card";

interface Props {
  device: DeviceState;
}

export function MotionCard({ device }: Props) {
  const detected = device.state.motionDetected as boolean;

  return (
    <Card title="Motion">
      <strong>{detected ? "Detected" : "Clear"}</strong>

      <div className="card__metadata">Device ID: {device.deviceId}</div>
    </Card>
  );
}
