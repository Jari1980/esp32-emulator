import type { Esp32Snapshot } from "../types/Esp32Snapshot";
import type { ConnectionStatus } from "../types/ConnectionStatus";
import { WEBSOCKET_URL } from "../config/websocket";

class WebSocketAdapter {
  private socket?: WebSocket;
  private status: ConnectionStatus = "DISCONNECTED";

  connect() {
    this.status = "CONNECTING";

    this.socket = new WebSocket(WEBSOCKET_URL);

    this.socket.onopen = () => {
      this.status = "CONNECTED";

      console.log("WebSocket connected");
    };

    this.socket.onclose = () => {
      this.status = "DISCONNECTED";

      console.log("WebSocket disconnected");
    };

    this.socket.onerror = (error) => {
      console.error("WebSocket error", error);
    };
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

    const message = {
      type: "STATE",
      payload: snapshot,
    };

    this.socket?.send(JSON.stringify(message));
  }
}

export default new WebSocketAdapter();
