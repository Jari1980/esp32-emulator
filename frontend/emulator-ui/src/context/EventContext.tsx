import {
  createContext,
  useContext,
  useState,
  type ReactNode,
} from "react";

import type { EmulatorEvent } from "../types/Event";

type EventContextType = {
  events: EmulatorEvent[];
  addEvent: (message: string) => void;
};

const EventContext = createContext<EventContextType | undefined>(
  undefined
);

export function EventProvider({
  children,
}: {
  children: ReactNode;
}) {

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

  return (
    <EventContext.Provider
      value={{
        events,
        addEvent,
      }}
    >
      {children}
    </EventContext.Provider>
  );
}


export function useEvents() {

  const context = useContext(EventContext);

  if (!context) {
    throw new Error(
      "useEvents must be used inside EventProvider"
    );
  }

  return context;
}