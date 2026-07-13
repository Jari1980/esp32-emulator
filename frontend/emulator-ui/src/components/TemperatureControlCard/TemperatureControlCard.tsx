import Card from "../Card/Card";
import "./TemperatureControlCard.css";
import useTemperature from "../../hooks/useTemperature";

function TemperatureControlCard() {
  const { temperature, increase, decrease } = useTemperature();
  return (
    <Card title="Temperature Sensor">
      <div className="temperature-control">
        <div className="temperature-control__value">{temperature} °C</div>

        <div className="temperature-control__buttons">
          <button onClick={decrease}>-</button>

          <button onClick={increase}>+</button>
        </div>
      </div>
    </Card>
  );
}

export default TemperatureControlCard;
