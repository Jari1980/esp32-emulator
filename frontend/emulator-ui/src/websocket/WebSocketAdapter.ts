import type { Esp32Snapshot } from "../types/Esp32Snapshot";
import type { ConnectionStatus } from "../types/ConnectionStatus";
import { WEBSOCKET_URL } from "../config/websocket";

class WebSocketAdapter {
  private socket?: WebSocket;
  private status: ConnectionStatus = "DISCONNECTED";
  private statusListener?: (status: ConnectionStatus) => void;
  private reconnectTimer?: number;
  private reconnectDelay = 3000;
  private messageListener?: (message: unknown) => void;

  setStatusListener(listener: (status: ConnectionStatus) => void) {
    this.statusListener = listener;
  }

  private updateStatus(status: ConnectionStatus) {
    this.status = status;
    this.statusListener?.(status);
  }

  private scheduleReconnect() {
    if (this.reconnectTimer) {
      return;
    }

    this.reconnectTimer = window.setTimeout(() => {
      this.reconnectTimer = undefined;

      console.log("Attempting WebSocket reconnect...");

      this.connect();
    }, this.reconnectDelay);
  }

  connect() {
    if (this.socket && this.status === "CONNECTED") {
      return;
    }
    this.updateStatus("CONNECTING");
    this.socket = new WebSocket(WEBSOCKET_URL);
    this.socket.onopen = () => {
      this.updateStatus("CONNECTED");
      console.log("WebSocket connected");
    };

    this.socket.onclose = () => {
      this.updateStatus("DISCONNECTED");
      console.log("WebSocket disconnected");
      this.scheduleReconnect();
    };

    this.socket.onerror = (error) => {
      console.error("WebSocket error", error);
    };

    this.socket.onmessage = (event) => {
      try {
        const message = JSON.parse(event.data);
        console.log("WebSocket message:", message);
        this.messageListener?.(message);
      } catch (error) {
        console.error("Invalid WebSocket message", error);
      }
    };
  }

  disconnect() {
    if (this.reconnectTimer) {
      clearTimeout(this.reconnectTimer);
      this.reconnectTimer = undefined;
    }
    this.socket?.close();
    this.socket = undefined;
    this.updateStatus("DISCONNECTED");
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

  setMessageListener(listener: (message: unknown) => void) {
    this.messageListener = listener;
  }

  sendCommand(command: string, deviceId: string) {
    if (this.status !== "CONNECTED") {
      console.log("Not connected, cannot send command");

      return;
    }

    const message = {
      type: "COMMAND",

      payload: {
        command,
        deviceId,
      },
    };

    this.socket?.send(JSON.stringify(message));
  }
}

export default new WebSocketAdapter();
