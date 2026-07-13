import Card from "../Card/Card";
import "./TemperatureControlCard.css";

function TemperatureControlCard() {
  return (
    <Card title="Temperature Sensor">

      <div className="temperature-control">

        <div className="temperature-control__value">
          22.4 °C
        </div>

        <div className="temperature-control__buttons">
          <button>-</button>
          <button>+</button>
        </div>

      </div>

    </Card>
  );
}

export default TemperatureControlCard;