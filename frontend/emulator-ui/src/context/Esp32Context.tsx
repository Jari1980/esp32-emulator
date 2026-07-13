import { createContext, useContext, useState, type ReactNode } from "react";

import type { Esp32State } from "../types/Esp32State";

type Esp32ContextType = {
  state: Esp32State;

  setTemperature: (value: number) => void;

  setMotion: (value: boolean) => void;

  setLed: (value: boolean) => void;
};

const Esp32Context = createContext<Esp32ContextType | undefined>(undefined);

export function Esp32Provider({ children }: { children: ReactNode }) {
  const [state, setState] = useState<Esp32State>({
    id: "esp32-001",

    temperature: 22.4,

    motionDetected: false,

    ledOn: false,
  });

  const setTemperature = (value: number) => {
    setState((current) => ({
      ...current,
      temperature: value,
    }));
  };

  const setMotion = (value: boolean) => {
    setState((current) => ({
      ...current,
      motionDetected: value,
    }));
  };

  const setLed = (value: boolean) => {
    setState((current) => ({
      ...current,
      ledOn: value,
    }));
  };

  return (
    <Esp32Context.Provider
      value={{
        state,

        setTemperature,

        setMotion,

        setLed,
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
