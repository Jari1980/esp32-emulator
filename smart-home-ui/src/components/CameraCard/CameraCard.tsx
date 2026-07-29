import { Card } from "../Card/Card";
import "./CameraCard.css";

export function CameraCard() {
  return (
    <Card title="Camera">
      <div className="camera">
        <div className="camera__preview">
          <div className="camera__status">No signal</div>
        </div>

        <button className="camera__button">Open fullscreen</button>
      </div>
    </Card>
  );
}
