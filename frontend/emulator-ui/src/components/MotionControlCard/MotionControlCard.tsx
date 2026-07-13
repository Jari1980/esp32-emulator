import Card from "../Card/Card";
import { useEsp32 } from "../../context/Esp32Context";
import { useEvents } from "../../context/EventContext";
import "./MotionControlCard.css";

function MotionControlCard() {
  const { state, setMotion } = useEsp32();

  const { addEvent } = useEvents();

  const handleTriggerMotion = () => {
    setMotion(true);

    addEvent({
      deviceId: "motion-001",
      type: "MOTION_DETECTED",
      value: true,
      message: "Motion detected",
    });
  };

  const handleResetMotion = () => {
    setMotion(false);

    addEvent({
      deviceId: "motion-001",
      type: "MOTION_RESET",
      value: false,
      message: "Motion reset",
    });
  };

  return (
    <Card title="Motion Sensor">
      <div className="motion-control">
        <div className="motion-control__state">
          {state.motionDetected ? "Motion detected" : "No movement"}
        </div>

        {state.motionDetected ? (
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
