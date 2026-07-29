import type { DeviceState } from "../../types/DeviceState";

interface Props {
  device: DeviceState;
}

export function TemperatureCard({ device }: Props) {
  const temperature = device.state.temperature as number;

  return (
    <section>
      <h2>Temperature</h2>

      <p>{temperature} °C</p>
    </section>
  );
}
