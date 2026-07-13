import Card from "../Card/Card";
import { useEsp32 } from "../../context/Esp32Context";

import "./TemperatureControlCard.css";

function TemperatureControlCard() {
  const { state } = useEsp32();
  const temperatureDevice = state.devices.find(
    (device) => device.deviceId === "temp-001",
  );

  const temperature = temperatureDevice?.state.temperature as
    | number
    | undefined;

  return (
    <Card title="Temperature Sensor">
      <div className="temperature-control">
        <div className="temperature-control__value">
          {temperature !== undefined ? `${temperature} °C` : "-"}
        </div>

        <div className="temperature-control__buttons">
          <button disabled>-</button>

          <button disabled>+</button>
        </div>
      </div>
    </Card>
  );
}

export default TemperatureControlCard;
