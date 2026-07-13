import { useState } from "react";
import type { EmulatorEvent } from "../types/Event";

function useEventLog() {

  const [events, setEvents] = useState<EmulatorEvent[]>([]);

  const addEvent = (message: string) => {
    const event: EmulatorEvent = {
      id: Date.now(),
      timestamp: new Date().toLocaleTimeString(),
      message,
    };

    setEvents((current) => [
      event,
      ...current,
    ]);
  };

  return {
    events,
    addEvent,
  };
}

export default useEventLog;