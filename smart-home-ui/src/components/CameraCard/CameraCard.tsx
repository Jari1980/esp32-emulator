import { Card } from "../Card/Card";
import "./CameraCard.css";
import type { DeviceState } from "../../types/DeviceState";
import type { CameraState } from "../../types/CameraState";

type Props = {
  device: DeviceState;
};

export function CameraCard({ device }: Props) {
  const cameraState = device.state as unknown as CameraState;

  return (
    <Card title="Camera">
      <div className="camera">
        <div className="camera__preview">
          <div className="camera__status">
            {cameraState.online ? "Online" : "Offline"}
          </div>
        </div>

        <p>Frame: {cameraState.frame ?? 0}</p>

        <button className="camera__button">Open fullscreen</button>
      </div>
    </Card>
  );
}
