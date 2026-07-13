import { createContext, useContext, useState, type ReactNode } from "react";
import type { Esp32State } from "../types/Esp32State";

type Esp32ContextType = {
  state: Esp32State;

  setSnapshot: (state: Esp32State) => void;
};

const Esp32Context = createContext<Esp32ContextType | undefined>(undefined);

const initialState: Esp32State = {
  id: "esp32-001",
  name: "Virtual ESP32",
  uptime: 0,
  devices: [],
};

export function Esp32Provider({ children }: { children: ReactNode }) {
  const [state, updateState] = useState<Esp32State>(initialState);

  const setSnapshot = (newState: Esp32State) => {
    updateState(newState);
  };

  return (
    <Esp32Context.Provider
      value={{
        state,
        setSnapshot,
      }}
    >
      {children}
    </Esp32Context.Provider>
  );
}

export function useEsp32() {
  const context = useContext(Esp32Context);

  if (!context) {
    throw new Error("useEsp32 must be used inside Esp32Provider");
  }

  return context;
}
