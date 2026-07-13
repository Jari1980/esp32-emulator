import Card from "../Card/Card";
import { useEsp32 } from "../../context/Esp32Context";
import { mapToSnapshot } from "../../mapper/stateMapper";
import useEsp32Publisher from "../../hooks/useEsp32Publisher";
import "./Esp32StateCard.css";

function Esp32StateCard() {
  const { state } = useEsp32();
  const snapshot = mapToSnapshot(state);
  const { publishState } = useEsp32Publisher();

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
        <button onClick={publishState}>Send WebSocket State</button>
        <pre className="esp32-state__json">
          {JSON.stringify(snapshot, null, 2)}
        </pre>
      </div>
    </Card>
  );
}

export default Esp32StateCard;
