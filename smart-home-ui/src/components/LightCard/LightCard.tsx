import type { DeviceState } from "../../types/DeviceState";

interface Props {
  device: DeviceState;
}

export function LightCard({ device }: Props) {
  const ledOn = device.state.ledOn as boolean;

  return (
    <section>
      <h2>Light</h2>

      <p>{ledOn ? "ON" : "OFF"}</p>
    </section>
  );
}
