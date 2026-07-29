import type { DeviceState } from "../../types/DeviceState";
import { Card } from "../Card/Card";

interface Props {
  device: DeviceState;
}

export function MotionCard({ device }: Props) {
  const detected = device.state.motionDetected as boolean;

  return (
    <Card title="Motion">
      <div className="status-value">
        <span
          className={
            detected
              ? "status-dot status-dot--warning"
              : "status-dot status-dot--off"
          }
        />

        {detected ? "Movement detected" : "No movement"}
      </div>
    </Card>
  );
}
