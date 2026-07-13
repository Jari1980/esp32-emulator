import type { Esp32State } from "../types/Esp32State";
import type { Esp32Snapshot } from "../types/Esp32Snapshot";

export function mapToSnapshot(state: Esp32State): Esp32Snapshot {
  return {
    id: state.id,

    temperature: state.temperature,

    motionDetected: state.motionDetected,

    ledOn: state.ledOn,
  };
}
