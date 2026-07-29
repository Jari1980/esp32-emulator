import { config } from "../config";

export class ControlUnitWebSocket {
  private socket?: WebSocket;

  connect(): void {
    this.socket = new WebSocket(config.websocketUrl);

    this.socket.onopen = () => {
      console.log("Connected");
    };

    this.socket.onclose = () => {
      console.log("Disconnected");
    };

    this.socket.onmessage = (event) => {
      console.log("Message received:", event.data);
    };
  }
}
