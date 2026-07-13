import Card from "../Card/Card";
import useMotion from "../../hooks/useMotion";
import "./MotionControlCard.css";

function MotionControlCard() {
  const { motionDetected, triggerMotion, resetMotion } = useMotion();
  return (
    <Card title="Motion Sensor">
      <div className="motion-control">
        <div className="motion-control__state">
          {motionDetected ? "Motion detected" : "No movement"}
        </div>

        {motionDetected ? (
          <button className="motion-control__button" onClick={resetMotion}>
            Reset Motion
          </button>
        ) : (
          <button className="motion-control__button" onClick={triggerMotion}>
            Trigger Motion
          </button>
        )}
      </div>
    </Card>
  );
}

export default MotionControlCard;
