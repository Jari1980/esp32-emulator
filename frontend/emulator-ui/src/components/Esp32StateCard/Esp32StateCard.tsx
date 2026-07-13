import Card from "../Card/Card";
import { useEsp32 } from "../../context/Esp32Context";
import { mapToSnapshot } from "../../mapper/stateMapper";
import "./Esp32StateCard.css";

function Esp32StateCard() {
  const { state } = useEsp32();
  const snapshot = mapToSnapshot(state);

  return (
    <Card title="ESP32 State">
      <div className="esp32-state">
        <div>
          <span>ID</span>
          <strong>{snapshot.id}</strong>
        </div>

        <div>
          <span>Temperature</span>
          <strong>{snapshot.temperature} °C</strong>
        </div>

        <div>
          <span>Motion</span>
          <strong>{snapshot.motionDetected ? "Detected" : "None"}</strong>
        </div>

        <div>
          <span>LED</span>
          <strong>{snapshot.ledOn ? "ON" : "OFF"}</strong>
        </div>
      </div>
      <pre className="esp32-state__json">
        {JSON.stringify(snapshot, null, 2)}
      </pre>
    </Card>
  );
}

export default Esp32StateCard;
