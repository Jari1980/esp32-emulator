import Card from "../Card/Card";
import { useEsp32 } from "../../context/Esp32Context";
import "./MotionControlCard.css";

function MotionControlCard() {
  const { state } = useEsp32();
  const motion = state.devices.find(
    (device) => device.deviceId === "motion-001",
  );

  const motionDetected = motion?.state.motionDetected === true;

  return (
    <Card title="Motion Sensor">
      <div className="motion-control">
        <div className="motion-control__state">
          {motionDetected ? "Motion detected" : "No movement"}
        </div>

        <button className="motion-control__button" disabled>
          {motionDetected ? "Reset Motion" : "Trigger Motion"}
        </button>
      </div>
    </Card>
  );
}

export default MotionControlCard;
