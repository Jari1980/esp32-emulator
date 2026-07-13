import Card from "../Card/Card";
import { useEsp32 } from "../../context/Esp32Context";
import "./LightControlCard.css";

function LightControlCard() {
  const { state } = useEsp32();
  const led = state.devices.find((device) => device.deviceId === "led-001");
  const ledOn = led?.state.ledOn === true;

  return (
    <Card title="Light / LED">
      <div className="light-control">
        <div
          className={
            ledOn
              ? "light-control__state light-control__state--on"
              : "light-control__state"
          }
        >
          {ledOn ? "ON" : "OFF"}
        </div>

        <button className="light-control__button" disabled>
          Toggle Light
        </button>
      </div>
    </Card>
  );
}

export default LightControlCard;
