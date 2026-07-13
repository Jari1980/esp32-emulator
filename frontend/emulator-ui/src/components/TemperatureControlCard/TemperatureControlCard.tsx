import Card from "../Card/Card";
import { useEsp32 } from "../../context/Esp32Context";
import useDeviceCommand from "../../hooks/useDeviceCommand";
import "./TemperatureControlCard.css";

function TemperatureControlCard() {
  const { state } = useEsp32();
  const { sendCommand } = useDeviceCommand();

  const changeTemperature = (direction: "UP" | "DOWN") => {
    sendCommand(
      direction === "UP" ? "INCREASE_TEMPERATURE" : "DECREASE_TEMPERATURE",
      "temp-001",
    );
  };

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
          <button onClick={() => changeTemperature("DOWN")}>-</button>

          <button onClick={() => changeTemperature("UP")}>+</button>
        </div>
      </div>
    </Card>
  );
}

export default TemperatureControlCard;
