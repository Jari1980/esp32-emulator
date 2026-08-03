import { Card } from "../Card/Card";
import "./CameraCard.css";
import type { DeviceState } from "../../types/DeviceState";
import type { CameraState } from "../../types/CameraState";
import { useEffect, useState } from "react";

type Props = {
  device: DeviceState;
};

export function CameraCard({ device }: Props) {
  const cameraState = device.state as unknown as CameraState;
  const [refresh, setRefresh] = useState(0);

  useEffect(() => {
    const timer = setInterval(() => {
      setRefresh((value) => value + 1);
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  return (
    <Card title="Camera">
      <div className="camera">
        <div className="camera__status">
          {cameraState.online ? "Online" : "Offline"}
        </div>

        {cameraState.online && cameraState.url && (
          <div className="camera__preview">
            <img src={`${cameraState.url}?t=${refresh}`} alt="ESP32 camera" />
          </div>
        )}

        <p>Frame: {cameraState.frame ?? 0}</p>
        <p>Size: {cameraState.size ?? 0} bytes</p>

        <button className="camera__button">Open fullscreen</button>
      </div>
    </Card>
  );
}
