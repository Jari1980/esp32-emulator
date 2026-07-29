import type { DeviceState } from "../../types/DeviceState";
import { useSmartHome } from "../../context/SmartHomeContext";
import { Commands } from "../../commands/commands";
import { Card } from "../Card/Card";

interface Props {
  device: DeviceState;
}

export function LightCard({ device }: Props) {
  const { sendCommand } = useSmartHome();

  const ledOn = device.state.ledOn as boolean;

  return (
    <Card title="Light">
      <div className="status-value">
        <span
          className={
            ledOn ? "status-dot status-dot--on" : "status-dot status-dot--off"
          }
        />

        {ledOn ? "ON" : "OFF"}
      </div>

      <div className="card-actions">
        <button
          onClick={() => sendCommand(Commands.TURN_ON, device.deviceId)}
          disabled={ledOn}
        >
          Turn On
        </button>

        <button
          onClick={() => sendCommand(Commands.TURN_OFF, device.deviceId)}
          disabled={!ledOn}
        >
          Turn Off
        </button>
      </div>
    </Card>
  );
}
