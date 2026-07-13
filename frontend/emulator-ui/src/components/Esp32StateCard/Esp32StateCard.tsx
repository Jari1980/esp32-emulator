import Card from "../Card/Card";
import { useEsp32 } from "../../context/Esp32Context";
import "./Esp32StateCard.css";

function Esp32StateCard() {
  const { state } = useEsp32();

  return (
    <Card title="ESP32 State">
      <div className="esp32-state">
        <div>
          <span>ID</span>
          <strong>{state.id}</strong>
        </div>

        <div>
          <span>Temperature</span>
          <strong>{state.temperature} °C</strong>
        </div>

        <div>
          <span>Motion</span>
          <strong>{state.motionDetected ? "Detected" : "None"}</strong>
        </div>

        <div>
          <span>LED</span>
          <strong>{state.ledOn ? "ON" : "OFF"}</strong>
        </div>
      </div>
    </Card>
  );
}

export default Esp32StateCard;
