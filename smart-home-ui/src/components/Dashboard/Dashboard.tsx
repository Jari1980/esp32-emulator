import { useSmartHome } from "../../context/SmartHomeContext";
import type { DeviceState } from "../../types/DeviceState";

import { DeviceCard } from "../DeviceCard/DeviceCard";
import { TemperatureCard } from "../TemperatureCard/TemperatureCard";
import { LightCard } from "../LightCard/LightCard";
import { MotionCard } from "../MotionCard/MotionCard";
import { CameraCard } from "../CameraCard/CameraCard";

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
  const camera = state.devices.find(
  device => device.type === "camera"
);


  if (!state) {
    return <p>Waiting for ESP32...</p>;
  }

  return (
    <main className="dashboard-page">
      <header className="dashboard-info">
        <p>System uptime: {state.uptime}s</p>
      </header>

      {camera && (
      <section className="dashboard-camera">
        <CameraCard device={camera} />
      </section>
    )}

      <section className="dashboard-devices">
        {state.devices.map(renderDevice)}
      </section>
    </main>
  );
}
