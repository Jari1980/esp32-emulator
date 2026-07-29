import { useSmartHome } from "../../context/SmartHomeContext";
import { DeviceCard } from "../DeviceCard/DeviceCard";

export function Dashboard() {
  const { state } = useSmartHome();

  if (!state) {
    return <p>Waiting for ESP32...</p>;
  }

  return (
    <main>
      <h1>{state.name}</h1>

      <p>Uptime: {state.uptime}s</p>

      {state.devices.map((device) => (
        <DeviceCard key={device.deviceId} device={device} />
      ))}
    </main>
  );
}
