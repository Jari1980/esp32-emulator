import { config } from "../config";

export class ControlUnitWebSocket {
  private socket?: WebSocket;

  private openHandler?: () => void;

  private closeHandler?: () => void;

  private messageHandler?: (message: any) => void;

  connect(): void {
    this.socket = new WebSocket(config.websocketUrl);

    this.socket.onopen = () => {
      console.log("Connected to Control Unit");

      this.openHandler?.();
    };

    this.socket.onclose = () => {
      console.log("Disconnected from Control Unit");

      this.closeHandler?.();
    };

    this.socket.onmessage = (event) => {
      const message = JSON.parse(event.data);

      this.messageHandler?.(message);
    };
  }

  onOpen(callback: () => void) {
    this.openHandler = callback;
  }

  onClose(callback: () => void) {
    this.closeHandler = callback;
  }

  onMessage(callback: (message: any) => void) {
    this.messageHandler = callback;
  }

  send(message: unknown) {
    this.socket?.send(JSON.stringify(message));
  }

  disconnect() {
    this.socket?.close();
  }
}
