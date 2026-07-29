import { Card } from "../Card/Card";

export function CameraCard() {
  return (
    <Card title="Surveillance Camera">
      <div className="camera-placeholder">
        <p>Camera not connected</p>

        <span>No signal</span>
      </div>
    </Card>
  );
}
