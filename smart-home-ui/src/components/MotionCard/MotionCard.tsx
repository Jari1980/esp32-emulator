import type { DeviceState } from "../../types/DeviceState";

interface Props {
  device: DeviceState;
}

export function MotionCard({ device }: Props) {
  const detected = device.state.motionDetected as boolean;

  return (
    <section>
      <h2>Motion</h2>

      <p>{detected ? "Movement detected" : "No movement"}</p>
    </section>
  );
}
