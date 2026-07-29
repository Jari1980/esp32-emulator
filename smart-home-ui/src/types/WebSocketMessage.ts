import type { Esp32State } from "./Esp32State";

export interface WebSocketMessage {
  type: string;
  payload: Esp32State;
}