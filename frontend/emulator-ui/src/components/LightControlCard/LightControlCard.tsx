import Card from "../Card/Card";
import { useEsp32 } from "../../context/Esp32Context";
import { useEvents } from "../../context/EventContext";
import "./LightControlCard.css";

function LightControlCard() {
  const { state, setLed } = useEsp32();

  const { addEvent } = useEvents();

  const handleToggle = () => {
    const newState = !state.ledOn;

    setLed(newState);

    addEvent({
      deviceId: "led-001",
      type: "LED_STATE_CHANGED",
      value: newState,
      message: newState ? "LED turned ON" : "LED turned OFF",
    });
  };

  return (
    <Card title="Light / LED">
      <div className="light-control">
        <div
          className={
            state.ledOn
              ? "light-control__state light-control__state--on"
              : "light-control__state"
          }
        >
          {state.ledOn ? "ON" : "OFF"}
        </div>

        <button className="light-control__button" onClick={handleToggle}>
          Toggle Light
        </button>
      </div>
    </Card>
  );
}

export default LightControlCard;
