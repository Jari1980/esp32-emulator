import Card from "../Card/Card";
import "./TemperatureControlCard.css";
import useTemperature from "../../hooks/useTemperature";
import { useEvents } from "../../context/EventContext";

function TemperatureControlCard() {
  const { addEvent } = useEvents();
  const { temperature, increase, decrease } = useTemperature();

  const increaseTemperature = () => {
    increase();

    addEvent({
      deviceId: "temp-001",
      type: "TEMPERATURE_CHANGED",
      value: temperature + 0.1,
      message: "Temperature increased",
    });
  };

  const decreaseTemperature = () => {
    decrease();

    addEvent({
      deviceId: "temp-001",
      type: "TEMPERATURE_CHANGED",
      value: temperature - 0.1,
      message: "Temperature decreased",
    });
  };
  return (
    <Card title="Temperature Sensor">
      <div className="temperature-control">
        <div className="temperature-control__value">{temperature} °C</div>

        <div className="temperature-control__buttons">
          <button onClick={decreaseTemperature}>-</button>

          <button onClick={increaseTemperature}>+</button>
        </div>
      </div>
    </Card>
  );
}

export default TemperatureControlCard;
