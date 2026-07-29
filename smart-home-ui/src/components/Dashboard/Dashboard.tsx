import { useSmartHome } from "../../context/SmartHomeContext";
import type { DeviceState } from "../../types/DeviceState";

import { DeviceCard } from "../DeviceCard/DeviceCard";
import { TemperatureCard } from "../TemperatureCard/TemperatureCard";
import { LightCard } from "../LightCard/LightCard";
import { MotionCard } from "../MotionCard/MotionCard";
import "./Dashboard.css";

function renderDevice(device: DeviceState) {
  switch (device.type) {
    case "temperature":
      return <TemperatureCard key={device.deviceId} device={device} />;

    case "led":
      return <LightCard key={device.deviceId} device={device} />;

    case "motion":
      return <MotionCard key={device.deviceId} device={device} />;

    default:
      return <DeviceCard key={device.deviceId} device={device} />;
  }
}

export function Dashboard() {
  const { state } = useSmartHome();

  if (!state) {
    return <p>Waiting for ESP32...</p>;
  }

  return (
    <main>
      <h2>{state.name}</h2>

      <p>System uptime: {state.uptime}s</p>

      <div className="devices">{state.devices.map(renderDevice)}</div>
    </main>
  );
}
