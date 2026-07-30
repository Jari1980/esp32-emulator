import Card from "../Card/Card";
import { useEsp32 } from "../../context/Esp32Context";
import type { CameraState } from "../../types/CameraState";

import "./CameraCard.css";

function CameraCard() {
  const { state } = useEsp32();

  const camera = state.devices.find(
    (device) => device.deviceId === "camera-001",
  );

  const cameraState = camera?.state as unknown as CameraState;

  const online = cameraState?.online === true;
  const mode = cameraState?.mode ?? "unknown";

  return (
    <Card title="Camera">
      <div className="camera">
        <div className="camera__preview">
          <span>{online ? "Online" : "Offline"}</span>
        </div>

        <p>Mode: {mode}</p>

        <button className="camera__button">Open fullscreen</button>
      </div>
    </Card>
  );
}

export default CameraCard;
