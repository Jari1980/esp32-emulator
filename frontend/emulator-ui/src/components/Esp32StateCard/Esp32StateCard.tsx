import Card from "../Card/Card";
import { useEsp32 } from "../../context/Esp32Context";
import { getDevice } from "../../utils/deviceState";

import "./Esp32StateCard.css";

function Esp32StateCard() {
  const { state } = useEsp32();
  const temperature = getDevice(state, "temp-001");
  const motion = getDevice(state, "motion-001");
  const led = getDevice(state, "led-001");

  return (
    <Card title="ESP32 State">
      <div className="esp32-state">
        <div>
          <span>ID</span>
          <strong>{state.id}</strong>
        </div>

        <div>
          <span>Name</span>
          <strong>{state.name}</strong>
        </div>

        <div>
          <span>Temperature</span>
          <strong>
            {(temperature?.state.temperature as number) ?? "-"}
            °C
          </strong>
        </div>

        <div>
          <span>Motion</span>
          <strong>{motion?.state.motionDetected ? "Detected" : "None"}</strong>
        </div>

        <div>
          <span>LED</span>
          <strong>{led?.state.ledOn ? "ON" : "OFF"}</strong>
        </div>

        <pre className="esp32-state__json">
          {JSON.stringify(state, null, 2)}
        </pre>
      </div>
    </Card>
  );
}

export default Esp32StateCard;
