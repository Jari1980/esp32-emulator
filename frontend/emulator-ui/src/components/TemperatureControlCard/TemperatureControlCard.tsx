import Card from "../Card/Card";
import "./TemperatureControlCard.css";
import { useEvents } from "../../context/EventContext";
import { useEsp32 } from "../../context/Esp32Context";

function TemperatureControlCard() {
  const { addEvent } = useEvents();
  const { state, setTemperature } = useEsp32();

  const increaseTemperature = () => {
    const newTemperature = Number((state.temperature + 0.1).toFixed(1));

    setTemperature(newTemperature);

    addEvent({
      deviceId: "temp-001",
      type: "TEMPERATURE_CHANGED",
      value: newTemperature,
      message: `Temperature changed to ${newTemperature}°C`,
    });
  };

  const decreaseTemperature = () => {
    const newTemperature = Number((state.temperature - 0.1).toFixed(1));

    setTemperature(newTemperature);

    addEvent({
      deviceId: "temp-001",
      type: "TEMPERATURE_CHANGED",
      value: newTemperature,
      message: `Temperature changed to ${newTemperature}°C`,
    });
  };
  return (
    <Card title="Temperature Sensor">
      <div className="temperature-control">
        <div className="temperature-control__value">{state.temperature} °C</div>

        <div className="temperature-control__buttons">
          <button onClick={decreaseTemperature}>-</button>

          <button onClick={increaseTemperature}>+</button>
        </div>
      </div>
    </Card>
  );
}

export default TemperatureControlCard;
