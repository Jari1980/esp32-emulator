import Card from "../Card/Card";
import useMotion from "../../hooks/useMotion";
import { useEvents } from "../../context/EventContext";
import "./MotionControlCard.css";

function MotionControlCard() {
  const { motionDetected, triggerMotion, resetMotion } = useMotion();

  const handleTriggerMotion = () => {
    triggerMotion();

    addEvent({
      deviceId: "motion-001",
      type: "MOTION_DETECTED",
      value: true,
      message: "Motion detected",
    });
  };

  const handleResetMotion = () => {
    resetMotion();

    addEvent({
      deviceId: "motion-001",
      type: "MOTION_RESET",
      value: false,
      message: "Motion reset",
    });
  };

  const { addEvent } = useEvents();
  return (
    <Card title="Motion Sensor">
      <div className="motion-control">
        <div className="motion-control__state">
          {motionDetected ? "Motion detected" : "No movement"}
        </div>

        {motionDetected ? (
          <button
            className="motion-control__button"
            onClick={handleResetMotion}
          >
            Reset Motion
          </button>
        ) : (
          <button
            className="motion-control__button"
            onClick={handleTriggerMotion}
          >
            Trigger Motion
          </button>
        )}
      </div>
    </Card>
  );
}

export default MotionControlCard;
