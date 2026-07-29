import {
  createContext,
  useContext,
  useEffect,
  useRef,
  useState,
  type ReactNode,
} from "react";

import { ControlUnitWebSocket } from "../websocket";
import type { Esp32State } from "../types/Esp32State";

interface SmartHomeContextValue {
  state?: Esp32State;

  connected: boolean;

  sendCommand: (command: string, deviceId: string) => void;
}

const SmartHomeContext = createContext<SmartHomeContextValue | undefined>(
  undefined,
);

interface Props {
  children: ReactNode;
}

export function SmartHomeProvider({ children }: Props) {
  const [state, setState] = useState<Esp32State>();

  const [connected, setConnected] = useState(false);

  const websocket = useRef<ControlUnitWebSocket | null>(null);

  useEffect(() => {
    const connection = new ControlUnitWebSocket();

    connection.onOpen(() => {
      setConnected(true);
    });

    connection.onClose(() => {
      setConnected(false);
    });

    connection.onMessage((message) => {
      setState(message.payload);
    });

    connection.connect();

    websocket.current = connection;

    return () => {
      connection.disconnect?.();
    };
  }, []);

  function sendCommand(command: string, deviceId: string) {
    websocket.current?.send({
      type: "COMMAND",
      payload: {
        command,
        deviceId,
      },
    });
  }

  return (
    <SmartHomeContext.Provider
      value={{
        state,
        connected,
        sendCommand,
      }}
    >
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
