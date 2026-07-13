import Card from "../Card/Card";
import "./TemperatureControlCard.css";
import useTemperature from "../../hooks/useTemperature";
import { useEvents } from "../../context/EventContext";

function TemperatureControlCard() {
  const { addEvent } = useEvents();
  const { temperature, increase, decrease } = useTemperature();

  const increaseTemperature = () => {
    const newTemperature = increase();

    addEvent({
      deviceId: "temp-001",
      type: "TEMPERATURE_CHANGED",
      value: newTemperature,
      message: `Temperature changed to ${newTemperature}°C`,
    });
  };

  const decreaseTemperature = () => {
    const newTemperature = decrease();

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
