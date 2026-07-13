import type { Esp32Snapshot } from "../types/Esp32Snapshot";
import type { ConnectionStatus } from "../types/ConnectionStatus";

class WebSocketAdapter {
  private status: ConnectionStatus = "DISCONNECTED";

  connect() {
    this.status = "CONNECTED";

    console.log("WebSocket connected");
  }

  disconnect() {
    this.status = "DISCONNECTED";

    console.log("WebSocket disconnected");
  }

  getStatus() {
    return this.status;
  }

  sendState(snapshot: Esp32Snapshot) {
    if (this.status !== "CONNECTED") {
      console.log("Not connected, skipping state");

      return;
    }

    console.log("WebSocket STATE:", {
      type: "STATE",
      payload: snapshot,
    });
  }
}

export default new WebSocketAdapter();
