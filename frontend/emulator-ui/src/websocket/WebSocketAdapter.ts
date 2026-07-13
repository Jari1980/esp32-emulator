import type { Esp32Snapshot } from "../types/Esp32Snapshot";

class WebSocketAdapter {
  sendState(snapshot: Esp32Snapshot) {
    console.log("WebSocket STATE:", {
      type: "STATE",
      payload: snapshot,
    });
  }
}

export default new WebSocketAdapter();
