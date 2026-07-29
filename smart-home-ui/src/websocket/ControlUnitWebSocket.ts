import { config } from "../config";
import type { WebSocketMessage } from "../types/WebSocketMessage";

export class ControlUnitWebSocket {
  private socket?: WebSocket;

  private messageHandler?: (message: WebSocketMessage) => void;

  connect(): void {
    this.socket = new WebSocket(config.websocketUrl);

    this.socket.onopen = () => {
      console.log("Connected");
    };

    this.socket.onclose = () => {
      console.log("Disconnected");
    };

    this.socket.onmessage = (event) => {
      const message = JSON.parse(event.data) as WebSocketMessage;

      this.messageHandler?.(message);
    };
  }

  onMessage(handler: (message: WebSocketMessage) => void): void {
    this.messageHandler = handler;
  }

  send(message: unknown): void {
    if (!this.socket) {
      console.error("WebSocket not connected");
      return;
    }

    this.socket.send(JSON.stringify(message));
  }
}
