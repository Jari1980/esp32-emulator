import Card from "../Card/Card";
import "./MotionControlCard.css";

function MotionControlCard() {
  return (
    <Card title="Motion Sensor">

      <div className="motion-control">

        <div className="motion-control__state">
          No movement
        </div>

        <button className="motion-control__button">
          Trigger Motion
        </button>

      </div>

    </Card>
  );
}

export default MotionControlCard;