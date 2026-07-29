import {
  createContext,
  useContext,
  useEffect,
  useState,
  type ReactNode,
} from "react";

import { ControlUnitWebSocket } from "../websocket";
import type { Esp32State } from "../types/Esp32State";

interface SmartHomeContextValue {
  state?: Esp32State;
}

const SmartHomeContext = createContext<SmartHomeContextValue | undefined>(
  undefined,
);

interface Props {
  children: ReactNode;
}

export function SmartHomeProvider({ children }: Props) {
  const [state, setState] = useState<Esp32State>();

  useEffect(() => {
    const websocket = new ControlUnitWebSocket();

    websocket.onMessage((message) => {
      setState(message.payload);
    });

    websocket.connect();
  }, []);

  return (
    <SmartHomeContext.Provider value={{ state }}>
      {children}
    </SmartHomeContext.Provider>
  );
}

export function useSmartHome() {
  const context = useContext(SmartHomeContext);

  if (!context) {
    throw new Error("useSmartHome must be used inside SmartHomeProvider");
  }

  return context;
}
