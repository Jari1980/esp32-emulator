import Card from "../Card/Card";
import { useEsp32 } from "../../context/Esp32Context";
import useDeviceCommand from "../../hooks/useDeviceCommand";

import "./MotionControlCard.css";

function MotionControlCard() {
  const { state } = useEsp32();
  const { sendCommand } = useDeviceCommand();
  const motion = state.devices.find(
    (device) => device.deviceId === "motion-001",
  );
  const motionDetected = motion?.state.motionDetected === true;
  const handleTriggerMotion = () => {
    sendCommand("TRIGGER_MOTION", "motion-001");
  };
  const handleResetMotion = () => {
    sendCommand("RESET_MOTION", "motion-001");
  };

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
