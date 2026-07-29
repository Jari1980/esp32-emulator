import type { DeviceState } from "../../types/DeviceState";
import { useSmartHome } from "../../context/SmartHomeContext";
import { Commands } from "../../commands/commands";

interface Props {
  device: DeviceState;
}

export function LightCard({ device }: Props) {
  const { sendCommand } = useSmartHome();

  const ledOn = device.state.ledOn as boolean;

  return (
    <section>
      <h2>Light</h2>

      <p>{ledOn ? "ON" : "OFF"}</p>

      <button onClick={() => sendCommand(Commands.TURN_ON, device.deviceId)}>
        Turn On
      </button>

      <button onClick={() => sendCommand(Commands.TURN_OFF, device.deviceId)}>
        Turn Off
      </button>
    </section>
  );
}
