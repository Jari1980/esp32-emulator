import Card from "../Card/Card";
import "./CameraCard.css";

function CameraCard() {
  return (
    <Card title="Camera">
      <div className="camera">

        <div className="camera__preview">
          <span>No signal</span>
        </div>

        <button className="camera__button">
          Open fullscreen
        </button>

      </div>
    </Card>
  );
}

export default CameraCard;